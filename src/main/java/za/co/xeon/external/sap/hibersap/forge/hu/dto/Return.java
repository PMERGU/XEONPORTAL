package za.co.xeon.external.sap.hibersap.forge.hu.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;
import java.lang.Override;

@BapiStructure
public class Return {

	@Parameter("TYPE")
	String _type;
	@Parameter("MESSAGE")
	String _message;
	@Parameter("LOG_MSG_NO")
	String _logMsgNo;
	@Parameter("ROW")
	Integer _row;
	@Parameter("ID")
	String _id;
	@Parameter("SYSTEM")
	String _system;
	@Parameter("MESSAGE_V1")
	String _messageV1;
	@Parameter("MESSAGE_V2")
	String _messageV2;
	@Parameter("MESSAGE_V3")
	String _messageV3;
	@Parameter("MESSAGE_V4")
	String _messageV4;
	@Parameter("NUMBER")
	String _number;
	@Parameter("LOG_NO")
	String _logNo;
	@Parameter("PARAMETER")
	String _parameter;
	@Parameter("FIELD")
	String _field;

	public String get_type() {
		return this._type;
	}

	public void set_type(final String _type) {
		this._type = _type;
	}

	public String get_message() {
		return this._message;
	}

	public void set_message(final String _message) {
		this._message = _message;
	}

	public String get_logMsgNo() {
		return this._logMsgNo;
	}

	public void set_logMsgNo(final String _logMsgNo) {
		this._logMsgNo = _logMsgNo;
	}

	public Integer get_row() {
		return this._row;
	}

	public void set_row(final Integer _row) {
		this._row = _row;
	}

	public String get_id() {
		return this._id;
	}

	public void set_id(final String _id) {
		this._id = _id;
	}

	public String get_system() {
		return this._system;
	}

	public void set_system(final String _system) {
		this._system = _system;
	}

	public String get_messageV1() {
		return this._messageV1;
	}

	public void set_messageV1(final String _messageV1) {
		this._messageV1 = _messageV1;
	}

	public String get_messageV2() {
		return this._messageV2;
	}

	public void set_messageV2(final String _messageV2) {
		this._messageV2 = _messageV2;
	}

	public String get_messageV3() {
		return this._messageV3;
	}

	public void set_messageV3(final String _messageV3) {
		this._messageV3 = _messageV3;
	}

	public String get_messageV4() {
		return this._messageV4;
	}

	public void set_messageV4(final String _messageV4) {
		this._messageV4 = _messageV4;
	}

	public String get_number() {
		return this._number;
	}

	public void set_number(final String _number) {
		this._number = _number;
	}

	public String get_logNo() {
		return this._logNo;
	}

	public void set_logNo(final String _logNo) {
		this._logNo = _logNo;
	}

	public String get_parameter() {
		return this._parameter;
	}

	public void set_parameter(final String _parameter) {
		this._parameter = _parameter;
	}

	public String get_field() {
		return this._field;
	}

	public void set_field(final String _field) {
		this._field = _field;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (_type != null && !_type.trim().isEmpty())
			result += "_type: " + _type;
		if (_message != null && !_message.trim().isEmpty())
			result += ", _message: " + _message;
		if (_logMsgNo != null && !_logMsgNo.trim().isEmpty())
			result += ", _logMsgNo: " + _logMsgNo;
		if (_row != null)
			result += ", _row: " + _row;
		if (_id != null && !_id.trim().isEmpty())
			result += ", _id: " + _id;
		if (_system != null && !_system.trim().isEmpty())
			result += ", _system: " + _system;
		if (_messageV1 != null && !_messageV1.trim().isEmpty())
			result += ", _messageV1: " + _messageV1;
		if (_messageV2 != null && !_messageV2.trim().isEmpty())
			result += ", _messageV2: " + _messageV2;
		if (_messageV3 != null && !_messageV3.trim().isEmpty())
			result += ", _messageV3: " + _messageV3;
		if (_messageV4 != null && !_messageV4.trim().isEmpty())
			result += ", _messageV4: " + _messageV4;
		if (_number != null && !_number.trim().isEmpty())
			result += ", _number: " + _number;
		if (_logNo != null && !_logNo.trim().isEmpty())
			result += ", _logNo: " + _logNo;
		if (_parameter != null && !_parameter.trim().isEmpty())
			result += ", _parameter: " + _parameter;
		if (_field != null && !_field.trim().isEmpty())
			result += ", _field: " + _field;
		return result;
	}
}