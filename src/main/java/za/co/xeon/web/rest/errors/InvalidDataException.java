package za.co.xeon.web.rest.errors;

/**
 * Created by derick on 2016/09/28.
 */
public class InvalidDataException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5592717457757829822L;

	public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
