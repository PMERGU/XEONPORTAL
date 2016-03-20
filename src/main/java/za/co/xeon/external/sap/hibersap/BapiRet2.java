package za.co.xeon.external.sap.hibersap;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

@BapiStructure
public class BapiRet2 {

    @Parameter("TYPE")
//    @Convert(converter = CharConverter.class)
    private String type;

    @Parameter("ID")
    private String id;

    @Parameter("NUMBER")
    private String number;

    @Parameter("MESSAGE")
    private String message;

    @Parameter("LOG_NO")
    private String logNo;

    @Parameter("LOG_MSG_NO")
    private String logMsgNo;

    @Parameter("MESSAGE_V1")
    private String messaveV1;

    @Parameter("MESSAGE_V2")
    private String messaveV2;

    @Parameter("MESSAGE_V3")
    private String messaveV3;

    @Parameter("MESSAGE_V4")
    private String messaveV4;

    public String getType() { return this.type; }

    public String getId() { return this.id; }

    public String getNumber() { return this.number; }

    public String getMessage() { return this.message; }

    public String getLogNo() {
        return logNo;
    }

    public String getLogMsgNo() {
        return logMsgNo;
    }

    public String getMessaveV1() {
        return messaveV1;
    }

    public String getMessaveV2() {
        return messaveV2;
    }

    public String getMessaveV3() {
        return messaveV3;
    }

    public String getMessaveV4() {
        return messaveV4;
    }
}
