package za.co.xeon.external.sap.hibersap;

import java.lang.String;
import java.util.List;
import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.ParameterType;
import org.hibersap.bapi.BapiRet2;

@Bapi("Z_GET_CUSTOMER_ORDERS_BY_DATE")
public class CustomerOrdersByDateRFC {
    /**
     * "Customer Number 1" */
    @Import
    @Parameter("IM_CUSTOMER")
    private final String imCustomer;

    /**
     * "Range Table for Data Element SYDATUM" */
    @Import
    @Parameter("IM_DATE_R")
    private final List<ImDateR> imDateR;

    /**
     * "POD status on header level" */
    @Import
    @Parameter("IM_STATUS_POD")
    private final String imStatusPod;

    /**
     * "Trace tool table type" */
    @Export
    @Parameter("EV_RESULT")
    private List<EvResult> evResult;

    /**
     * "Return Parameter"@return "EvReturn" - "Return Parameter" */
    @Export
    @Parameter(
        value = "EV_RETURN",
        type = ParameterType.STRUCTURE
    )
    private BapiRet2 evReturnType;

    /**
     * @param "imCustomer" - "Customer Number 1"@param "imStatusPod" - "POD status on header level" */
    public CustomerOrdersByDateRFC(String imCustomer, List<ImDateR> imDateR, String imStatusPod) {
        this.imCustomer = imCustomer;
        this.imDateR = imDateR;
        this.imStatusPod = imStatusPod;
    }

    public List<EvResult> getEvResult() {
        return evResult;
    }

    public BapiRet2 getEvReturn() {
        return evReturnType;
    }

    @BapiStructure
    public class ImDateR {
        /**
         * "Type of SIGN component in row type of a Ranges type" */
        @Parameter("SIGN")
        private java.lang.String sign;

        /**
         * "Type of OPTION component in row type of a Ranges type" */
        @Parameter("OPTION")
        private java.lang.String option;

        /**
         * "Date and Time, Current (Application Server) Date" */
        @Parameter("LOW")
        private java.util.Date low;

        /**
         * "Date and Time, Current (Application Server) Date" */
        @Parameter("HIGH")
        private java.util.Date high;

        public ImDateR(java.lang.String sign, java.lang.String option, java.util.Date low, java.util.Date high) {
            this.sign = sign;
            this.option = option;
            this.low = low;
            this.high = high;
        }

        /**
         * @return "Sign" - "Type of SIGN component in row type of a Ranges type" */
        public java.lang.String getSign() {
            return sign;
        }

        /**
         * @return "Option" - "Type of OPTION component in row type of a Ranges type" */
        public java.lang.String getOption() {
            return option;
        }

        /**
         * @return "Low" - "Date and Time, Current (Application Server) Date" */
        public java.util.Date getLow() {
            return low;
        }

        /**
         * @return "High" - "Date and Time, Current (Application Server) Date" */
        public java.util.Date getHigh() {
            return high;
        }
    }

    @BapiStructure
    public class EvResult {
        /**
         * "Customer Number 1" */
        @Parameter("SKUNNR")
        private java.lang.String skunnr;

        /**
         * "Name" */
        @Parameter("SNAME1")
        private java.lang.String sname1;

        /**
         * "Customer purchase order number" */
        @Parameter("BSTKD")
        private java.lang.String bstkd;

        /**
         * "Customer's or vendor's internal reference" */
        @Parameter("IHREZ")
        private java.lang.String ihrez;

        /**
         * "Sales and Distribution Document Number" */
        @Parameter("VBELN")
        private java.lang.String vbeln;

        /**
         * "Name of the controlling area" */
        @Parameter("BEZEI")
        private java.lang.String bezei;

        /**
         * "Partner function" */
        @Parameter("PARVW")
        private java.lang.String parvw;

        /**
         * "Document date (date received/sent)" */
        @Parameter("AUDAT")
        private java.util.Date audat;

        /**
         * "Entry time" */
        @Parameter("ERZET")
        private java.util.Date erzet;

        /**
         * "Name of Person who Created the Object" */
        @Parameter("ERNAM")
        private java.lang.String ernam;

        /**
         * "Cumulative order quantity in sales units" */
        @Parameter("KWMENG")
        private java.math.BigDecimal kwmeng;

        /**
         * "Shipment Number" */
        @Parameter("TKNUM")
        private java.lang.String tknum;

        /**
         * "Current date for start of shipment" */
        @Parameter("DATBG")
        private java.util.Date datbg;

        /**
         * "Actual transport start time" */
        @Parameter("UATBG")
        private java.util.Date uatbg;

        /**
         * "Actual Date for End of Shipment" */
        @Parameter("DATEN")
        private java.util.Date daten;

        /**
         * "Actual shipment end time" */
        @Parameter("UATEN")
        private java.util.Date uaten;

        /**
         * "Sales and Distribution Document Number" */
        @Parameter("DBELN")
        private java.lang.String dbeln;

        /**
         * "Status of pick confirmation" */
        @Parameter("KOQUK")
        private java.lang.String koquk;

        /**
         * "Overall status of warehouse management activities" */
        @Parameter("LVSTK")
        private java.lang.String lvstk;

        /**
         * "Total goods movement status" */
        @Parameter("WBSTK")
        private java.lang.String wbstk;

        /**
         * "Transportation planning status" */
        @Parameter("TRSTA")
        private java.lang.String trsta;

        /**
         * "POD status on header level" */
        @Parameter("PDSTK")
        private java.lang.String pdstk;

        /**
         * "Billing status" */
        @Parameter("FKSTK")
        private java.lang.String fkstk;

        /**
         * "Actual quantity delivered (in sales units)" */
        @Parameter("LFIMG")
        private java.math.BigDecimal lfimg;

        /**
         * "Sales and Distribution Document Number" */
        @Parameter("BELNR")
        private java.lang.String belnr;

        /**
         * "Amount in document currency" */
        @Parameter("WRBTR")
        private java.math.BigDecimal wrbtr;

        /**
         * "Customer Number 1" */
        @Parameter("PKUNNR")
        private java.lang.String pkunnr;

        /**
         * "Name" */
        @Parameter("PNAME1")
        private java.lang.String pname1;

        /**
         * "Customer Number 1" */
        @Parameter("BKUNNR")
        private java.lang.String bkunnr;

        /**
         * "Name" */
        @Parameter("BNAME1")
        private java.lang.String bname1;

        public EvResult(java.lang.String skunnr, java.lang.String sname1, java.lang.String bstkd, java.lang.String ihrez, java.lang.String vbeln, java.lang.String bezei, java.lang.String parvw, java.util.Date audat, java.util.Date erzet, java.lang.String ernam, java.math.BigDecimal kwmeng, java.lang.String tknum, java.util.Date datbg, java.util.Date uatbg, java.util.Date daten, java.util.Date uaten, java.lang.String dbeln, java.lang.String koquk, java.lang.String lvstk, java.lang.String wbstk, java.lang.String trsta, java.lang.String pdstk, java.lang.String fkstk, java.math.BigDecimal lfimg, java.lang.String belnr, java.math.BigDecimal wrbtr, java.lang.String pkunnr, java.lang.String pname1, java.lang.String bkunnr, java.lang.String bname1) {
            this.skunnr = skunnr;
            this.sname1 = sname1;
            this.bstkd = bstkd;
            this.ihrez = ihrez;
            this.vbeln = vbeln;
            this.bezei = bezei;
            this.parvw = parvw;
            this.audat = audat;
            this.erzet = erzet;
            this.ernam = ernam;
            this.kwmeng = kwmeng;
            this.tknum = tknum;
            this.datbg = datbg;
            this.uatbg = uatbg;
            this.daten = daten;
            this.uaten = uaten;
            this.dbeln = dbeln;
            this.koquk = koquk;
            this.lvstk = lvstk;
            this.wbstk = wbstk;
            this.trsta = trsta;
            this.pdstk = pdstk;
            this.fkstk = fkstk;
            this.lfimg = lfimg;
            this.belnr = belnr;
            this.wrbtr = wrbtr;
            this.pkunnr = pkunnr;
            this.pname1 = pname1;
            this.bkunnr = bkunnr;
            this.bname1 = bname1;
        }

        /**
         * @return "Skunnr" - "Customer Number 1" */
        public java.lang.String getSkunnr() {
            return skunnr;
        }

        /**
         * @return "Sname1" - "Name" */
        public java.lang.String getSname1() {
            return sname1;
        }

        /**
         * @return "Bstkd" - "Customer purchase order number" */
        public java.lang.String getBstkd() {
            return bstkd;
        }

        /**
         * @return "Ihrez" - "Customer's or vendor's internal reference" */
        public java.lang.String getIhrez() {
            return ihrez;
        }

        /**
         * @return "Vbeln" - "Sales and Distribution Document Number" */
        public java.lang.String getVbeln() {
            return vbeln;
        }

        /**
         * @return "Bezei" - "Name of the controlling area" */
        public java.lang.String getBezei() {
            return bezei;
        }

        /**
         * @return "Parvw" - "Partner function" */
        public java.lang.String getParvw() {
            return parvw;
        }

        /**
         * @return "Audat" - "Document date (date received/sent)" */
        public java.util.Date getAudat() {
            return audat;
        }

        /**
         * @return "Erzet" - "Entry time" */
        public java.util.Date getErzet() {
            return erzet;
        }

        /**
         * @return "Ernam" - "Name of Person who Created the Object" */
        public java.lang.String getErnam() {
            return ernam;
        }

        /**
         * @return "Kwmeng" - "Cumulative order quantity in sales units" */
        public java.math.BigDecimal getKwmeng() {
            return kwmeng;
        }

        /**
         * @return "Tknum" - "Shipment Number" */
        public java.lang.String getTknum() {
            return tknum;
        }

        /**
         * @return "Datbg" - "Current date for start of shipment" */
        public java.util.Date getDatbg() {
            return datbg;
        }

        /**
         * @return "Uatbg" - "Actual transport start time" */
        public java.util.Date getUatbg() {
            return uatbg;
        }

        /**
         * @return "Daten" - "Actual Date for End of Shipment" */
        public java.util.Date getDaten() {
            return daten;
        }

        /**
         * @return "Uaten" - "Actual shipment end time" */
        public java.util.Date getUaten() {
            return uaten;
        }

        /**
         * @return "Dbeln" - "Sales and Distribution Document Number" */
        public java.lang.String getDbeln() {
            return dbeln;
        }

        /**
         * @return "Koquk" - "Status of pick confirmation" */
        public java.lang.String getKoquk() {
            return koquk;
        }

        /**
         * @return "Lvstk" - "Overall status of warehouse management activities" */
        public java.lang.String getLvstk() {
            return lvstk;
        }

        /**
         * @return "Wbstk" - "Total goods movement status" */
        public java.lang.String getWbstk() {
            return wbstk;
        }

        /**
         * @return "Trsta" - "Transportation planning status" */
        public java.lang.String getTrsta() {
            return trsta;
        }

        /**
         * @return "Pdstk" - "POD status on header level" */
        public java.lang.String getPdstk() {
            return pdstk;
        }

        /**
         * @return "Fkstk" - "Billing status" */
        public java.lang.String getFkstk() {
            return fkstk;
        }

        /**
         * @return "Lfimg" - "Actual quantity delivered (in sales units)" */
        public java.math.BigDecimal getLfimg() {
            return lfimg;
        }

        /**
         * @return "Belnr" - "Sales and Distribution Document Number" */
        public java.lang.String getBelnr() {
            return belnr;
        }

        /**
         * @return "Wrbtr" - "Amount in document currency" */
        public java.math.BigDecimal getWrbtr() {
            return wrbtr;
        }

        /**
         * @return "Pkunnr" - "Customer Number 1" */
        public java.lang.String getPkunnr() {
            return pkunnr;
        }

        /**
         * @return "Pname1" - "Name" */
        public java.lang.String getPname1() {
            return pname1;
        }

        /**
         * @return "Bkunnr" - "Customer Number 1" */
        public java.lang.String getBkunnr() {
            return bkunnr;
        }

        /**
         * @return "Bname1" - "Name" */
        public java.lang.String getBname1() {
            return bname1;
        }
    }
}
