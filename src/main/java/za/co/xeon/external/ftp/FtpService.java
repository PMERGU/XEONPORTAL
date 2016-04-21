package za.co.xeon.external.ftp;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.NodeList;
import za.co.xeon.external.ocr.Base64;
import za.co.xeon.external.ocr.OcrSettings;
import za.co.xeon.external.ocr.dto.Result;
import za.co.xeon.external.ocr.dto.xsd.result.Document;
import org.apache.commons.net.ftp.FTPClient;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by derick on 2016/02/07.
 */
@Service
public class FtpService {
    private final static Logger log = LoggerFactory.getLogger(FtpService.class);

    @Autowired
    private FtpSettings ftpSettings;
    FTPClient ftpClient = new FTPClient();

    @PostConstruct
    public void init(){
        log.debug("initializing FtpService for " + ftpSettings.getHost());
    }

    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                log.debug("FTP SERVER: " + aReply);
            }
        }
    }

    public Result scanDocument(String deliveryNo){
        try {
            ftpClient.connect(ftpSettings.getHost(), 21);
            showServerReply(ftpClient);
            boolean success = ftpClient.login(ftpSettings.getUser(), ftpSettings.getPass());
            showServerReply(ftpClient);

            if (!success) {
                log.error("Could not authenticate to ftp server : ");
                showServerReply(ftpClient);
                throw new RuntimeException("Could not authenticate to FTP server");
            }

            FileOutputStream fos = new FileOutputStream(File.createTempFile(deliveryNo, ".pdf"));
            boolean download = ftpClient.retrieveFile(ftpSettings.getFolder() + deliveryNo + ".pdf", fos);
            if (download) {
                System.out.println("File downloaded successfully !");
            } else {
                System.out.println("Error in downloading file !");
            }

            // Changes working directory


            success = ftpClient.changeWorkingDirectory(ftpSettings.getFolder());
            showServerReply(ftpClient);

            if (success) {
                System.out.println("Successfully changed working directory.");
            } else {
                System.out.println("Failed to change working directory. See server's reply.");
            }

            // logs out
            ftpClient.logout();
            ftpClient.disconnect();
            return null;
        } catch (IOException ex) {
            System.out.println("Oops! Something wrong happened");
            ex.printStackTrace();
            return null;
        }
    }

    public byte[] download(String deliveryNo){
        try {
            ftpClient.connect(ftpSettings.getHost(), 21);
            boolean success = ftpClient.login(ftpSettings.getUser(), ftpSettings.getPass());

            if (!success) {
                log.error("Could not authenticate to ftp server : ");
                showServerReply(ftpClient);
                throw new RuntimeException("Could not authenticate to FTP server");
            }
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileTransferMode(FTP.BLOCK_TRANSFER_MODE);

            InputStream is = ftpClient.retrieveFileStream(ftpSettings.getFolder() + deliveryNo + ".pdf");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //Using org.apache.commons.io.IOUtils
            if(is != null) {
                IOUtils.copy(is, bos);
                bos.flush();
                IOUtils.closeQuietly(bos);
                IOUtils.closeQuietly(is);

                ftpClient.completePendingCommand();
                ftpClient.logout();
                ftpClient.disconnect();
                log.debug("Bos size : " + bos.size());
                return bos.toByteArray();
            }else{
                log.debug("No invoice found for " + deliveryNo);
                return null;
            }
        } catch (IOException ex) {
            System.out.println("Oops! Something wrong happened");
            ex.printStackTrace();
            return null;
        }
    }

}
