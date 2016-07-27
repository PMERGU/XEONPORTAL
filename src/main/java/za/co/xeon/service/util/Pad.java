package za.co.xeon.service.util;

/**
 * Created by Derick on 7/27/2016.
 */
public class Pad {
    public static String left(String padMePlease, int length){
        return org.apache.commons.lang.StringUtils.leftPad(padMePlease, length, "0");
    }
}
