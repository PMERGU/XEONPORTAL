package za.co.xeon.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by Derick on 7/17/2016.
 */
public enum AttachmentCategories{
    CARTAGE, INVOICE, POD, TREM, COLLECTION_DOCUMENTS
}
