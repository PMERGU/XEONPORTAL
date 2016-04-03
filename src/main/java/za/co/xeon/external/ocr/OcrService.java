package za.co.xeon.external.ocr;

import za.co.xeon.external.ocr.dto.Result;
import za.co.xeon.external.ocr.dto.xsd.result.Document;
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

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by derick on 2016/02/07.
 */
@Service
public class OcrService {
    private final static Logger log = LoggerFactory.getLogger(OcrService.class);
    private XPath xPath = XPathFactory.newInstance().newXPath();

    @Autowired
    private OcrSettings ocrSettings;

    @PostConstruct
    public void init(){
        log.debug("initializing OcrService for " + ocrSettings.getApplication() + " on url " + ocrSettings.getUri());
    }

    public Result scanDocument(String filePath){
        String url = "http://cloud.ocrsdk.com/processImage?language=english&profile=barcodeRecognition&exportformat=xml";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.TEXT_XML);
        headers.set("Authorization", "Basic U0FQIEludGVncmF0b3I6ajZ0eEJzekZmNTVkZ3M5dFNnSkxpOWZO");

        PathResource pathResource = new PathResource(filePath);

        ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(pathResource, headers), Result.class);
        log.debug(response.getBody().toString());
        return response.getBody();
    }



    public Result getStatus(String id){
        String url = "http://cloud.ocrsdk.com/getTaskStatus?taskId=" + id;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic U0FQIEludGVncmF0b3I6ajZ0eEJzekZmNTVkZ3M5dFNnSkxpOWZO");

        ResponseEntity<Result> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Result.class);
        log.debug(response.getBody().toString());
        return response.getBody();
    }

    public Document getCompletedResult(String resultUrl) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
//        headers.set("Authorization", "Basic U0FQIEludGVncmF0b3I6ajZ0eEJzekZmNTVkZ3M5dFNnSkxpOWZO");

//        HttpEntity<String> entity = new HttpEntity<String>(headers);

//        ResponseEntity<String> response = restTemplate.exchange(resultUrl, HttpMethod.GET, new HttpEntity<>(headers), byte[].class);
        ResponseEntity<Document> response = restTemplate.getForEntity(new URI(resultUrl), Document.class);

        return response.getBody();
    }

    public String getCompletedBarcodeResult(String resultUrl) throws Exception{
        String xpathExpression = "//formatting/text()";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuild = dbf.newDocumentBuilder();
        org.w3c.dom.Document doc = docBuild.parse(resultUrl);

        XPathExpression expr = xPath.compile(xpathExpression);
        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants. NODESET);
        try {
            return nl.item(0).getNodeValue();
        }catch(NullPointerException npe){
            return null;
        }
    }

    public String getCompletedResult2(String resultUrl) throws Exception{
        URL url = new URL(resultUrl);
		URLConnection connection = url.openConnection(); // do not use
															// authenticated
															// connection

		BufferedInputStream reader = new BufferedInputStream(
				connection.getInputStream());

		FileOutputStream out = new FileOutputStream("/Users/derick/code/sap/test.xml");

		try {
			byte[] data = new byte[1024];
			int count;
			while ((count = reader.read(data, 0, data.length)) != -1) {
				out.write(data, 0, count);
			}
		} finally {
			out.close();
		}
        return "success";
    }

    private String encodeUserPassword(String applicationId, String password) {
		String toEncode = applicationId + ":" + password;
		return Base64.encode(toEncode);
	}
}
