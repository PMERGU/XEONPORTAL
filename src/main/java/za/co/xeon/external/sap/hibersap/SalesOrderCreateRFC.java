package za.co.xeon.external.sap.hibersap;

import java.lang.String;
import java.util.Date;
import java.util.List;
import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.ParameterType;
import org.hibersap.annotations.Table;
import za.co.xeon.external.sap.hibersap.dto.ExReturn;
import za.co.xeon.external.sap.hibersap.dto.ImItemDetail;
import za.co.xeon.external.sap.hibersap.dto.ImOtcAdrCol;
import za.co.xeon.external.sap.hibersap.dto.ImOtcAdrShpto;

@Bapi("ZSALESORDER_CREATE")
public class SalesOrderCreateRFC {
    /**
     * "Reference Doc No " TESTDOC" */
    @Import
    @Parameter("IM_ACC_REF")
    private final String imAccRef;

    /**
     * "Sales Document Type " YCO" */
    @Import
    @Parameter("IM_AUART")
    private final String imAuart;

    /**
     * "Bill to party " 213" */
    @Import
    @Parameter("IM_BILL_TO")
    private final String imBillTo;

    /**
     * "Collective number (SD) " TEST" */
    @Import
    @Parameter("IM_COLL_NUM")
    private final String imCollNum;

    /**
     * "Collection Party " 213" */
    @Import
    @Parameter("IM_COL_PARTY")
    private final String imColParty;

    /**
     * "Commodity" */
    @Import
    @Parameter("IM_COMMOD")
    private final String imCommod;

    /**
     * "Consol" */
    @Import
    @Parameter("IM_CONSOL")
    private final String imConsol;

    /**
     * "Containers" */
    @Import
    @Parameter("IM_CONTNR")
    private final String imContnr;

    /**
     * "Requested delivery date " 26.07.2016" */
    @Import
    @Parameter("IM_DELDT")
    private final Date imDeldt;

    /**
     * "Destination" */
    @Import
    @Parameter("IM_DESTIN")
    private final String imDestin;

    /**
     * "ETA - Container" */
    @Import
    @Parameter("IM_ETA")
    private final Date imEta;

    /**
     * "ETD - Container" */
    @Import
    @Parameter("IM_ETD")
    private final Date imEtd;

    /**
     * "Finance Controller "6043" */
    @Import
    @Parameter("IM_FIN_CON")
    private final String imFinCon;

    /**
     * "House Ocean/ Air Way Bill" */
    @Import
    @Parameter("IM_HBIOLA")
    private final String imHbiola;

    /**
     * "House Ocean/ Air Way Bill Issue" */
    @Import
    @Parameter("IM_HBIOLI")
    private final Date imHbioli;

    /**
     * "Table type Sales order item details" */
    @Import
    @Parameter(
        value = "IM_ITEM_DETAIL",
        type = ParameterType.TABLE_STRUCTURE
    )
    private final List
        <ImItemDetail> imItemDetail;

    /**
     * "Mode of transport " 01" */
    @Import
    @Parameter("IM_MOD_OF_TRANS")
    private final String imModOfTrans;

    /**
     * "Master Ocean/ Air Way Bill" */
    @Import
    @Parameter("IM_OBIOLA")
    private final String imObiola;

    /**
     * "Master Ocean/ Air Way Bill Issue" */
    @Import
    @Parameter("IM_OBIOLI")
    private final Date imObioli;

    /**
     * "Message Text" */
    @Import
    @Parameter("IM_ORDER_TEXT")
    private final String imOrderText;

    /**
     * "Origin" */
    @Import
    @Parameter("IM_ORIGIN")
    private final String imOrigin;

    /**
     * "One time Customer Address Details Collecting Party" */
    @Import
    @Parameter(
        value = "IM_OTC_ADR_COL",
        type = ParameterType.STRUCTURE
    )
    private final ImOtcAdrCol imOtcAdrCol;

    /**
     * "One time Customer Address Details Ship to Party" */
    @Import
    @Parameter(
        value = "IM_OTC_ADR_SHPTO",
        type = ParameterType.STRUCTURE
    )
    private final ImOtcAdrShpto imOtcAdrShpto;

    /**
     * "Carrier Reference" */
    @Import
    @Parameter("IM_OWNREF")
    private final String imOwnref;

    /**
     * "Customer PO date " 25.07.2016" */
    @Import
    @Parameter("IM_PO_DATE")
    private final Date imPoDate;

    /**
     * "Customer PO num " TESTPO" */
    @Import
    @Parameter("IM_PO_NUM")
    private final String imPoNum;

    /**
     * "Receiving Hub " CPT-HUB" */
    @Import
    @Parameter("IM_RECHUB")
    private final String imRechub;

    /**
     * "Service level "L1" */
    @Import
    @Parameter("IM_SERLVL")
    private final String imSerlvl;

    /**
     * "Service type " YSL1" */
    @Import
    @Parameter("IM_SERTYPE")
    private final String imSertype;

    /**
     * "Shipper" */
    @Import
    @Parameter("IM_SHIPER")
    private final String imShiper;

    /**
     * "Shipment Reference " TESTREF" */
    @Import
    @Parameter("IM_SHIP_REF")
    private final String imShipRef;

    /**
     * "Ship to party " 213" */
    @Import
    @Parameter("IM_SHIP_TO")
    private final String imShipTo;

    /**
     * "Sold to party  Reference "TESTSTP" */
    @Import
    @Parameter("IM_SOLD_REF")
    private final String imSoldRef;

    /**
     * "Sold to party " 213" */
    @Import
    @Parameter("IM_SOLD_TO")
    private final String imSoldTo;

    /**
     * "Division " L1" */
    @Import
    @Parameter("IM_SPART")
    private final String imSpart;

    /**
     * "Status Domestic" */
    @Import
    @Parameter("IM_STATDOM")
    private final String imStatdom;

    /**
     * "Status Export" */
    @Import
    @Parameter("IM_STATEXP")
    private final String imStatexp;

    /**
     * "Status Import" */
    @Import
    @Parameter("IM_STATIMP")
    private final String imStatimp;

    /**
     * "Telephone number "011 253 4940" */
    @Import
    @Parameter("IM_TEL")
    private final String imTel;

    /**
     * "Vessel" */
    @Import
    @Parameter("IM_VESSEL")
    private final String imVessel;

    /**
     * "Vessel Voyage  / Flight" */
    @Import
    @Parameter("IM_VESVOY")
    private final String imVesvoy;

    /**
     * "Sales Organization "3000" */
    @Import
    @Parameter("IM_VKORG")
    private final String imVkorg;

    /**
     * "Distribution Channel " L1" */
    @Import
    @Parameter("IM_VTWEG")
    private final String imVtweg;

    /**
     * "Sales and Distribution Document Number" */
    @Parameter("EX_SALESORDER")
    @Export
    private String exSalesorder;

    /**
     * "Return Parameter" */
    @Parameter("EX_RETURN")
    @Table
    private List
        <ExReturn> exReturn;

    /**
     * @param "imAccRef" - "Reference Doc No " TESTDOC"@param "imAuart" - "Sales Document Type " YCO"@param "imBillTo" - "Bill to party " 213"@param "imCollNum" - "Collective number (SD) " TEST"@param "imColParty" - "Collection Party " 213"@param "imCommod" - "Commodity"@param "imConsol" - "Consol"@param "imContnr" - "Containers"@param "imDeldt" - "Requested delivery date " 26.07.2016"@param "imDeltype" - "Delivery type " Y1"@param "imDestin" - "Destination"@param "imEta" - "ETA - Container"@param "imEtd" - "ETD - Container"@param "imFinCon" - "Finance Controller "6043"@param "imHbiola" - "House Ocean/ Air Way Bill"@param "imHbioli" - "House Ocean/ Air Way Bill Issue"@param "imModOfTrans" - "Mode of transport " 01"@param "imObiola" - "Master Ocean/ Air Way Bill"@param "imObioli" - "Master Ocean/ Air Way Bill Issue"@param "imOrderText" - "Message Text"@param "imOrigin" - "Origin"@param "imOwnref" - "Carrier Reference"@param "imPickupDeltyp" - "Pick up delivery type " Y1"@param "imPoDate" - "Customer PO date " 25.07.2016"@param "imPoNum" - "Customer PO num " TESTPO"@param "imRechub" - "Receiving Hub " CPT-HUB"@param "imSerlvl" - "Service level "L1"@param "imSertype" - "Service type " YSL1"@param "imShiper" - "Shipper"@param "imShipRef" - "Shipment Reference " TESTREF"@param "imShipTo" - "Ship to party " 213"@param "imSoldRef" - "Sold to party  Reference "TESTSTP"@param "imSoldTo" - "Sold to party " 213"@param "imSpart" - "Division " L1"@param "imStatdom" - "Status Domestic"@param "imStatexp" - "Status Export"@param "imStatimp" - "Status Import"@param "imTel" - "Telephone number "011 253 4940"@param "imVessel" - "Vessel"@param "imVesvoy" - "Vessel Voyage  / Flight"@param "imVkorg" - "Sales Organization "3000"@param "imVtweg" - "Distribution Channel " L1" */
    public SalesOrderCreateRFC(String imAccRef, String imAuart, String imBillTo, String imCollNum, String imColParty, String imCommod, String imConsol, String imContnr, Date imDeldt, String imDestin, Date imEta, Date imEtd, String imFinCon, String imHbiola, Date imHbioli, List
        <ImItemDetail> imItemDetail, String imModOfTrans, String imObiola, Date imObioli, String imOrderText, String imOrigin, String imOwnref, Date imPoDate, String imPoNum, String imRechub, String imSerlvl, String imSertype, String imShiper, String imShipRef, String imShipTo, String imSoldRef, String imSoldTo, String imSpart, String imStatdom, String imStatexp, String imStatimp, String imTel, String imVessel, String imVesvoy, String imVkorg, String imVtweg,
                                ImOtcAdrCol imOtcAdrCol, ImOtcAdrShpto imOtcAdrShpto) {
        this.imAccRef = imAccRef;
        this.imAuart = imAuart;
        this.imBillTo = imBillTo;
        this.imCollNum = imCollNum;
        this.imColParty = imColParty;
        this.imCommod = imCommod;
        this.imConsol = imConsol;
        this.imContnr = imContnr;
        this.imDeldt = imDeldt;
        this.imDestin = imDestin;
        this.imEta = imEta;
        this.imEtd = imEtd;
        this.imFinCon = imFinCon;
        this.imHbiola = imHbiola;
        this.imHbioli = imHbioli;
        this.imItemDetail = imItemDetail;
        this.imModOfTrans = imModOfTrans;
        this.imObiola = imObiola;
        this.imObioli = imObioli;
        this.imOrderText = imOrderText;
        this.imOrigin = imOrigin;
        this.imOwnref = imOwnref;
        this.imPoDate = imPoDate;
        this.imPoNum = imPoNum;
        this.imRechub = imRechub;
        this.imSerlvl = imSerlvl;
        this.imSertype = imSertype;
        this.imShiper = imShiper;
        this.imShipRef = imShipRef;
        this.imShipTo = imShipTo;
        this.imSoldRef = imSoldRef;
        this.imSoldTo = imSoldTo;
        this.imSpart = imSpart;
        this.imStatdom = imStatdom;
        this.imStatexp = imStatexp;
        this.imStatimp = imStatimp;
        this.imTel = imTel;
        this.imVessel = imVessel;
        this.imVesvoy = imVesvoy;
        this.imVkorg = imVkorg;
        this.imVtweg = imVtweg;
        this.imOtcAdrCol = imOtcAdrCol;
        this.imOtcAdrShpto = imOtcAdrShpto;
    }

    public String getExSalesorder() {
        return exSalesorder;
    }

    public List
        <ExReturn> getExReturn() {
        return exReturn;
    }

    @Override
    public String toString() {
        return "SalesOrderCreateRFC{" +
            "imAccRef='" + imAccRef + '\'' +
            ",  imBillTo='" + imBillTo + '\'' +
            ",  imFinCon='" + imFinCon + '\'' +
            ",  imPoDate='" + imPoDate + '\'' +
            ",  imPoNum='" + imPoNum + '\'' +
            '}';
    }

    public String toStringFull() {
        return "SalesOrderCreateRFC{" +
            "\nimAccRef='" + imAccRef + '\'' +
            ",  imAuart='" + imAuart + '\'' +
            ",\n imBillTo='" + imBillTo + '\'' +
            ",\n imCollNum='" + imCollNum + '\'' +
            ",\n imColParty='" + imColParty + '\'' +
            ",\n imCommod='" + imCommod + '\'' +
            ",\n imConsol='" + imConsol + '\'' +
            ",\n imContnr='" + imContnr + '\'' +
            ",\n imDeldt='" + imDeldt + '\'' +
            ",\n imDestin='" + imDestin + '\'' +
            ",\n imEta='" + imEta + '\'' +
            ",\n imEtd='" + imEtd + '\'' +
            ",\n imFinCon='" + imFinCon + '\'' +
            ",\n imHbiola='" + imHbiola + '\'' +
            ",\n imHbioli='" + imHbioli + '\'' +
            ",\n imItemDetail=" + imItemDetail +
            ",\n imModOfTrans='" + imModOfTrans + '\'' +
            ",\n imObiola='" + imObiola + '\'' +
            ",\n imObioli='" + imObioli + '\'' +
            ",\n imOrigin='" + imOrigin + '\'' +
            ",\n imOwnref='" + imOwnref + '\'' +
            ",\n imPoDate='" + imPoDate + '\'' +
            ",\n imPoNum='" + imPoNum + '\'' +
            ",\n imRechub='" + imRechub + '\'' +
            ",\n imSerlvl='" + imSerlvl + '\'' +
            ",\n imSertype='" + imSertype + '\'' +
            ",\n imShiper='" + imShiper + '\'' +
            ",\n imShipRef='" + imShipRef + '\'' +
            ",\n imShipTo='" + imShipTo + '\'' +
            ",\n imSoldRef='" + imSoldRef + '\'' +
            ",\n imSoldTo='" + imSoldTo + '\'' +
            ",\n imSpart='" + imSpart + '\'' +
            ",\n imStatexp='" + imStatexp + '\'' +
            ",\n imStatimp='" + imStatimp + '\'' +
            ",\n imTel='" + imTel + '\'' +
            ",\n imVessel='" + imVessel + '\'' +
            ",\n imVesvoy='" + imVesvoy + '\'' +
            ",\n imVkorg='" + imVkorg + '\'' +
            ",\n imVtweg='" + imVtweg + '\'' +
            ",\n exSalesorder='" + exSalesorder + '\'' +
            ",\n exReturn=" + exReturn +
            '}';
    }
}

