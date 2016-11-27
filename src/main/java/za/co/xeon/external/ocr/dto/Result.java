package za.co.xeon.external.ocr.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by derick on 2016/02/16.
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.NONE)
public class Result {
	@XmlElement(name = "task")
	private Task task;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "Result{" + "task=" + task + '}';
	}
}
