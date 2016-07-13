package za.co.xeon.external.as3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

import za.co.xeon.config.MobileConfiguration;

/**
 * Created by derick on 2016/02/07.
 */
@Service
public class S3Service {
    private final static Logger log = LoggerFactory.getLogger(S3Service.class);

    BasicAWSCredentials awsCreds;
    AmazonS3 s3client;

    @Autowired
    private S3Settings s3Settings;

    @Autowired
    private MobileConfiguration mobileConfiguration;

    @PostConstruct
    public void init(){
        awsCreds = new BasicAWSCredentials(s3Settings.getAccessKeyId(), s3Settings.getSecretKey());
        s3client = new AmazonS3Client(awsCreds);
    }

    public String uploadFile(String fileName, InputStream inputStream){
        try {
            log.debug("Uploading a new object to S3 from a file\n");
            PutObjectResult por = s3client.putObject(new PutObjectRequest(s3Settings.getBucketName(), fileName,
                    inputStream, new ObjectMetadata()));
            return s3Settings.getBucketName() + " / " + fileName;
        } catch (AmazonServiceException ase) {
            log.error("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason. \n"
            + "Error Message:    " + ase.getMessage() + "\n"
            + "HTTP Status Code: " + ase.getStatusCode() + "\n"
            + "AWS Error Code:   " + ase.getErrorCode() + "\n"
            + "Error Type:       " + ase.getErrorType() + "\n"
            + "Request ID:       " + ase.getRequestId());
            throw ase;
        } catch (AmazonClientException ace) {
            log.error("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network. \n"
            + "Error Message: " + ace.getMessage());
            throw ace;
        }
    }

    public String uploadFile(String fileName, File file) {
        return uploadFile(fileName, file, "api/mobile/pods/" + fileName);
    }

    public String uploadFile(String fileName, File file, String readUrl) {
        try {
            log.debug("Uploading a new object to S3 from a file\n");
            s3client.putObject(new PutObjectRequest(s3Settings.getBucketName(), fileName, file));
            return mobileConfiguration.getHttpServerName() + readUrl;
        } catch (AmazonServiceException ase) {
            log.error("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason. \n"
                    + "Error Message:    " + ase.getMessage() + "\n"
                    + "HTTP Status Code: " + ase.getStatusCode() + "\n"
                    + "AWS Error Code:   " + ase.getErrorCode() + "\n"
                    + "Error Type:       " + ase.getErrorType() + "\n"
                    + "Request ID:       " + ase.getRequestId());
            throw ase;
        } catch (AmazonClientException ace) {
            log.error("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network. \n"
                    + "Error Message: " + ace.getMessage());
            throw ace;
        }
    }

    public byte[] retrieveFile(String fileName) throws AmazonS3Exception, IOException {
        log.debug(String.format("Trying to retrieve file from S3 : %s", fileName));
        if(s3client.doesObjectExist(s3Settings.getBucketName(), fileName)) {
            S3Object s3Object = s3client.getObject(new GetObjectRequest(s3Settings.getBucketName(), fileName));
            return IOUtils.toByteArray(s3Object.getObjectContent());
        }else{
            return null;
        }
    }

    public ObjectListing listFolder(String folder){
        log.debug(String.format("Trying to list folder contents from S3 : %s", folder));
        ObjectListing listing = s3client.listObjects(new ListObjectsRequest()
            .withBucketName(s3Settings.getBucketName())
            .withPrefix(folder)
        );
        return listing;
    }
}

