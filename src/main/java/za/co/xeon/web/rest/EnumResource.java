package za.co.xeon.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import za.co.xeon.domain.enumeration.AttachmentCategories;
import za.co.xeon.domain.enumeration.MaterialType;

import javax.servlet.annotation.MultipartConfig;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/static")
public class EnumResource {

    private final Logger log = LoggerFactory.getLogger(EnumResource.class);

    @RequestMapping(value = "/attachmentCategories", method = RequestMethod.GET)
    public List<AttachmentCategories> listAttachments() {
        return Arrays.asList(AttachmentCategories.values());
    }

    @RequestMapping(value = "/materialTypes", method = RequestMethod.GET)
    public List<MaterialType> listMaterials() {
        return Arrays.asList(MaterialType.values());
    }
}
