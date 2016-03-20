package za.co.xeon.external.sap;

import com.sap.conn.jco.*;
import com.sap.conn.jco.ext.DataProviderException;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.squareup.javapoet.*;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibersap.annotations.Bapi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.lang.model.element.Modifier;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by derick on 2016/02/07.
 */
@Service
public class SapService {
    private final static Logger log = LoggerFactory.getLogger(SapService.class);

    @Autowired
    private SapSettings s3Settings;
    @Autowired
    private PropertyDestinationDataProvider propertyDestinationDataProvider;

    //    private static final String BAPI = "Z_GET_HANDELING_UNITS";
    //private static final String BAPI = "Z_GET_CUSTOMER_ORDERS_BY_DATE";

    private static String DESTINATION_NAME = "DP_ABAP_AS_WITH_POOL";
    private static JCoDestination destination;
    Properties connectProperties = new Properties();

    @PostConstruct
    public void init() {
        log.debug("Loading properties for sap host : " + s3Settings.getAshost());
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, s3Settings.getAshost());
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, s3Settings.getSysnr());
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, s3Settings.getClient());
        connectProperties.setProperty(DestinationDataProvider.JCO_USER, s3Settings.getUser());
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, s3Settings.getPasswd());
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, s3Settings.getLang());
        connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, s3Settings.getPoolCapacity());
        connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, s3Settings.getPeakLimit());

        try {
            log.debug("Registering new custom sap data provider: " + PropertyDestinationDataProvider.class);
            propertyDestinationDataProvider.addDestination(DESTINATION_NAME, connectProperties);
            com.sap.conn.jco.ext.Environment.registerDestinationDataProvider(propertyDestinationDataProvider);
        } catch (IllegalStateException providerAlreadyRegisteredException) {
            log.error(PropertyDestinationDataProvider.class + " already registered");
            throw new Error(providerAlreadyRegisteredException);
        }
        try {
            destination = JCoDestinationManager.getDestination(DESTINATION_NAME);
        } catch (JCoException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    static void createDestinationDataFile(String destinationName, Properties connectProperties) {
        File destCfg = new File(destinationName);
        try {
            FileOutputStream fos = new FileOutputStream(destCfg, false);
            connectProperties.store(fos, "for tests only !");
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to create the destination files", e);
        }
    }

    public String generateJavaSourceFromRFC(String bapiName) throws JCoException {
        JCoFunction function = destination.getRepository().getFunction(bapiName);
        log.debug("Generating java source for structure: \n{}", prettyFormat(function.toXML()));
        if (function == null) {
            throw new RuntimeException(bapiName + " not found in SAP.");
        }

        TypeSpec.Builder builder = TypeSpec.classBuilder(StringUtils.remove(WordUtils.capitalizeFully(
            bapiName.toUpperCase().startsWith("Z_GET") ? bapiName.substring(5) : bapiName,
            new char[]{'_'}), "_") + "RFC")
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(AnnotationSpec.builder(Bapi.class).addMember("value", "$S", bapiName).build());

        JCoParameterList importParameters = function.getImportParameterList();
        if (importParameters != null) {
            JCoParameterFieldIterator it = importParameters.getParameterFieldIterator();
            while (it.hasNextField()) {
                JCoField test = it.nextParameterField();

                builder.addField(FieldSpec.builder(String.class, test.getName())
                    .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                    .addJavadoc("Field detail : $S", test.getDescription() + "\n")
                    .build());

//                log.debug(test.getClassNameOfValue());
                if (test.getTypeAsString().equals("TABLE")) {
                    printFieldDebugInfo(test.getTable().getRecordFieldIterator());
                }else if (test.getTypeAsString().equals("STRUCTURE")) {
                    printFieldDebugInfo(test.getStructure().getRecordFieldIterator());
                }
            }
        }

        TypeSpec helloWorld = builder.build();

        JavaFile javaFile = JavaFile.builder("za.co.xeon.external.sap.hibersap", helloWorld)
            .build();

        log.debug("Class file for RFC auto generation: \n{}", javaFile.toString());

        return javaFile.toString();
    }


    public String diagnoseBapi(String bapiName) throws JCoException {
        JCoFunction function = destination.getRepository().getFunction(bapiName);
        if (function == null) {
            throw new RuntimeException(bapiName + " not found in SAP.");
        }

        log.debug("RFC: \n{}", prettyFormat(function.toXML()));

        JCoParameterFieldIterator it;
        JCoParameterList importParameters = function.getImportParameterList();
        if (importParameters != null) {
            it = importParameters.getParameterFieldIterator();
            log.debug("=============================== IMPORT =========================================");
            while (it.hasNextField()) {
                log.debug("---------------------------------------------");
                JCoField test = it.nextParameterField();
                log.debug(test.getName());
                log.debug(test.getDescription());
                log.debug(test.getClassNameOfValue());
                log.debug(test.getTypeAsString());
                if (test.getTypeAsString().equals("TABLE")) {
                    printFieldDebugInfo(test.getTable().getRecordFieldIterator());
                }
                if (test.getTypeAsString().equals("STRUCTURE")) {
                    printFieldDebugInfo(test.getStructure().getRecordFieldIterator());
                }
                log.debug("---------------------------------------------");
            }
        }

        JCoParameterList exportParameters = function.getExportParameterList();
        if (exportParameters != null) {
            it = exportParameters.getParameterFieldIterator();
            log.debug("================================ EXPORT ========================================");
            while (it.hasNextField()) {
                log.debug("---------------------------------------------");
                JCoField test = it.nextParameterField();
                log.debug(test.getName());
                log.debug(test.getDescription());
                log.debug(test.getClassNameOfValue());
                log.debug(test.getTypeAsString());
                if (test.getTypeAsString().equals("TABLE")) {
                    printFieldDebugInfo(test.getTable().getRecordFieldIterator());
                }
                if (test.getTypeAsString().equals("STRUCTURE")) {
                    printFieldDebugInfo(test.getStructure().getRecordFieldIterator());
                }
            }

            log.debug("---------------------------------------------");
        }

        JCoParameterList tableParameters = function.getTableParameterList();
        if (tableParameters != null) {
            it = tableParameters.getParameterFieldIterator();
            log.debug("================================ TABLE ========================================");
            while (it.hasNextField()) {
                log.debug("---------------------------------------------");
                JCoField test = it.nextParameterField();
                log.debug(test.getName());
                log.debug(test.getDescription());
                log.debug(test.getClassNameOfValue());
                log.debug(test.getTypeAsString());
                if (test.getTypeAsString().equals("TABLE")) {
                    printFieldDebugInfo(test.getTable().getRecordFieldIterator());
                }
                if (test.getTypeAsString().equals("STRUCTURE")) {
                    printFieldDebugInfo(test.getStructure().getRecordFieldIterator());
                }
                log.debug("---------------------------------------------");
            }
        }

        // try to call function
        try {
            function.getImportParameterList().setValue("IM_DEL_VBELN", "80108175");
            function.execute(destination);
            System.out.println("STFC_CONNECTION finished:");
        } catch (AbapException e) {
            System.out.println(e.toString());
        }

        return prettyFormat(function.toXML());
    }

    public void printFieldDebugInfo(JCoRecordFieldIterator recordFieldIterator) {
        while (recordFieldIterator.hasNextField()) {
            log.debug("            " + formatColumnDetails(recordFieldIterator.nextRecordField()));
        }
    }

    public static String formatColumnDetails(JCoRecordField field) {
        return String.format("%20s%10s%20s%s", field.getName(), field.getTypeAsString(), field.getClassNameOfValue(), field.getDescription());
    }


    public static String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e); // simple exception handling, please review it
        }
    }

    public static String prettyFormat(String input) {
        return prettyFormat(input, 2);
    }

}
