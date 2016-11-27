package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by Derick on 7/26/2016.
 */
@BapiStructure
public class ExReturn {
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

	/**
	 * "Parameter Name"
	 */
	@Parameter("PARAMETER")
	private String parameter;

	/**
	 * "Lines in parameter"
	 */
	@Parameter("ROW")
	private Integer row;

	/**
	 * "Field in parameter"
	 */
	@Parameter("FIELD")
	private String field;

	/**
	 * "Logical system from which message originates"
	 */
	@Parameter("SYSTEM")
	private String system;

	public ExReturn(String type, String id, String number, String message, String logNo, String logMsgNo, String messageV1, String messageV2, String messageV3, String messageV4, String parameter, Integer row, String field, String system) {
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
		this.parameter = parameter;
		this.row = row;
		this.field = field;
		this.system = system;
	}

	public ExReturn() {
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

	/**
	 * @return "Parameter" - "Parameter Name"
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * @return "Row" - "Lines in parameter"
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * @return "Field" - "Field in parameter"
	 */
	public String getField() {
		return field;
	}

	/**
	 * @return "System" - "Logical system from which message originates"
	 */
	public String getSystem() {
		return system;
	}

	@Override
	public String toString() {
		return "ExReturn{" + "type='" + type + '\'' + ", id='" + id + '\'' + ", number='" + number + '\'' + ", message='" + message + '\'' + ", logNo='" + logNo + '\'' + ", logMsgNo='" + logMsgNo + '\'' + ", messageV1='" + messageV1 + '\'' + ", messageV2='" + messageV2 + '\'' + ", messageV3='" + messageV3 + '\'' + ", messageV4='" + messageV4 + '\'' + ", parameter='" + parameter + '\'' + ", row=" + row + ", field='" + field + '\'' + ", system='" + system + '\'' + '}';
	}
}
