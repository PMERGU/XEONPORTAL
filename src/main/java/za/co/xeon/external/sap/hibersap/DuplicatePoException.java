package za.co.xeon.external.sap.hibersap;

/**
 * Created by Derick on 9/17/2016.
 */
public class DuplicatePoException extends Exception {
    private static final long serialVersionUID = 1997753363232807009L;

    public DuplicatePoException(){
    }

    public DuplicatePoException(String message){
        super(message);
    }

    public DuplicatePoException(Throwable cause){
        super(cause);
    }

    public DuplicatePoException(String message, Throwable cause){
        super(message, cause);
    }

    public DuplicatePoException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
