package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by Derick on 7/25/2016.
 */
@BapiStructure
public class GtReturn {
	/**
	 * "Message type: S Success, E Error, W Warning, I Info, A Abort"
	 */
	@Parameter("TYPE")
	private String type;

	/**
	 * "Messages, Message Class"
	 */
	@Parameter("ID")
	private String id;

	/**
	 * "Messages, Message Number"
	 */
	@Parameter("NUMBER")
	private String number;

	/**
	 * "Message Text"
	 */
	@Parameter("MESSAGE")
	private String message;

	/**
	 * "Application log: log number"
	 */
	@Parameter("LOG_NO")
	private String logNo;

	/**
	 * "Application log: Internal message serial number"
	 */
	@Parameter("LOG_MSG_NO")
	private String logMsgNo;

	/**
	 * "Messages, message variables"
	 */
	@Parameter("MESSAGE_V1")
	private String messageV1;

	/**
	 * "Messages, message variables"
	 */
	@Parameter("MESSAGE_V2")
	private String messageV2;

	/**
	 * "Messages, message variables"
	 */
	@Parameter("MESSAGE_V3")
	private String messageV3;

	/**
	 * "Messages, message variables"
	 */
	@Parameter("MESSAGE_V4")
	private String messageV4;

	public GtReturn(String type, String id, String number, String message, String logNo, String logMsgNo, String messageV1, String messageV2, String messageV3, String messageV4) {
		this.type = type;
		this.id = id;
		this.number = number;
		this.message = message;
		this.logNo = logNo;
		this.logMsgNo = logMsgNo;
		this.messageV1 = messageV1;
		this.messageV2 = messageV2;
		this.messageV3 = messageV3;
		this.messageV4 = messageV4;
	}

	public GtReturn() {
	}

	/**
	 * @return "Type" -
	 *         "Message type: S Success, E Error, W Warning, I Info, A Abort"
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return "Id" - "Messages, Message Class"
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return "Number" - "Messages, Message Number"
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return "Message" - "Message Text"
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return "LogNo" - "Application log: log number"
	 */
	public String getLogNo() {
		return logNo;
	}

	/**
	 * @return "LogMsgNo" - "Application log: Internal message serial number"
	 */
	public String getLogMsgNo() {
		return logMsgNo;
	}

	/**
	 * @return "MessageV1" - "Messages, message variables"
	 */
	public String getMessageV1() {
		return messageV1;
	}

	/**
	 * @return "MessageV2" - "Messages, message variables"
	 */
	public String getMessageV2() {
		return messageV2;
	}

	/**
	 * @return "MessageV3" - "Messages, message variables"
	 */
	public String getMessageV3() {
		return messageV3;
	}

	/**
	 * @return "MessageV4" - "Messages, message variables"
	 */
	public String getMessageV4() {
		return messageV4;
	}
}
