package za.co.xeon.external.sap.hibersap;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by derick on 2016/03/21.
 */
@BapiStructure
public class ImDateR {
    /**
     * "Type of SIGN component in row type of a Ranges type"
     */
    @Parameter("SIGN")
    private String sign;

    /**
     * "Type of OPTION component in row type of a Ranges type"
     */
    @Parameter("OPTION")
    private String option;

    /**
     * "Date and Time, Current (Application Server) Date"
     */
    @Parameter("LOW")
    private java.util.Date low;

    /**
     * "Date and Time, Current (Application Server) Date"
     */
    @Parameter("HIGH")
    private java.util.Date high;

    public ImDateR(String sign, String option, java.util.Date low, java.util.Date high) {
        this.sign = sign;
        this.option = option;
        this.low = low;
        this.high = high;
    }

    public ImDateR() {
    }

    /**
     * @return "Sign" - "Type of SIGN component in row type of a Ranges type"
     */
    public String getSign() {
        return sign;
    }

    /**
     * @return "Option" - "Type of OPTION component in row type of a Ranges type"
     */
    public String getOption() {
        return option;
    }

    /**
     * @return "Low" - "Date and Time, Current (Application Server) Date"
     */
    public java.util.Date getLow() {
        return low;
    }

    /**
     * @return "High" - "Date and Time, Current (Application Server) Date"
     */
    public java.util.Date getHigh() {
        return high;
    }
}
