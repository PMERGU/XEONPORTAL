package za.co.xeon.external.ocr.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import za.co.xeon.external.ocr.OcrSettings;

/**
 * Created by derick on 2016/02/16.
 */
@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.NONE)
public class Task {
	@XmlAttribute(name = "id")
	private String id;

	@XmlAttribute(name = "registrationTime")
	private String registrationTime;

	@XmlAttribute(name = "statusChangeTime")
	private String statusChangeTime;

	@XmlAttribute(name = "status")
	private OcrSettings.TaskStatus status;

	@XmlAttribute(name = "filesCount")
	private String filesCount;

	@XmlAttribute(name = "credits")
	private String credits;

	@XmlAttribute(name = "estimatedProcessingTime")
	private String estimatedProcessingTime;

	@XmlAttribute(name = "resultUrl")
	private String resultUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}

	public String getStatusChangeTime() {
		return statusChangeTime;
	}

	public void setStatusChangeTime(String statusChangeTime) {
		this.statusChangeTime = statusChangeTime;
	}

	public OcrSettings.TaskStatus getStatus() {
		return status;
	}

	public void setStatus(OcrSettings.TaskStatus status) {
		this.status = status;
	}

	public String getFilesCount() {
		return filesCount;
	}

	public void setFilesCount(String filesCount) {
		this.filesCount = filesCount;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getEstimatedProcessingTime() {
		return estimatedProcessingTime;
	}

	public void setEstimatedProcessingTime(String estimatedProcessingTime) {
		this.estimatedProcessingTime = estimatedProcessingTime;
	}

	public String getResultUrl() {
		return resultUrl;
	}

	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}

	@Override
	public String toString() {
		return "Task{" + "id='" + id + '\'' + ", registrationTime='" + registrationTime + '\'' + ", statusChangeTime='" + statusChangeTime + '\'' + ", status='" + status + '\'' + ", filesCount='" + filesCount + '\'' + ", credits='" + credits + '\'' + ", estimatedProcessingTime='" + estimatedProcessingTime + '\'' + ", resultUrl='" + resultUrl + '\'' + '}';
	}
}
