package za.co.xeon.external.as3;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by derick on 2016/02/07.
 */
@Configuration
@ConfigurationProperties(prefix = "amazon.s3", locations = { "application.yml" })
public class S3Settings {
	private String bucketName;
	private String keyName;
	private String accessKeyId;
	private String secretKey;
	private String folderPod;
	private String folderAttachment;

	public S3Settings() {
	}

	public String getAttachmentPath(String name) {
		return getFolderAttachment() + "/" + name;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getFolderPod() {
		return folderPod;
	}

	public void setFolderPod(String folderPod) {
		this.folderPod = folderPod;
	}

	public String getFolderAttachment() {
		return folderAttachment;
	}

	public void setFolderAttachment(final String folderAttachment) {
		this.folderAttachment = folderAttachment;
	}
}
