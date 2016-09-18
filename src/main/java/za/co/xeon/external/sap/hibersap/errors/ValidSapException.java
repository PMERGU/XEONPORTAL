package za.co.xeon.external.sap.hibersap.errors;

import za.co.xeon.external.sap.hibersap.dto.ExReturn;

/**
 * Created by Derick on 9/17/2016.
 */
public class ValidSapException extends Exception {
    private static final long serialVersionUID = 1997753363232807009L;
    private String type;
    private String id;
    private String number;
    private String sapMessage;


    public ValidSapException(String message, ExReturn exReturn) {
        super(message);
        this.type = exReturn.getType();
        this.id = exReturn.getId();
        this.number = exReturn.getNumber();
        this.sapMessage = exReturn.getMessage();
    }

    public ValidSapException(String message, Throwable cause, ExReturn exReturn) {
        super(message, cause);
        this.type = exReturn.getType();
        this.id = exReturn.getId();
        this.number = exReturn.getNumber();
        this.sapMessage = exReturn.getMessage();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSapMessage() {
        return sapMessage;
    }

    public void setSapMessage(String message) {
        this.sapMessage = message;
    }
}
