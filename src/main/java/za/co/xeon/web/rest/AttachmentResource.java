package za.co.xeon.web.rest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;

import za.co.xeon.config.MobileConfiguration;
import za.co.xeon.domain.Attachment;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.dto.ReadAttachmentDto;
import za.co.xeon.external.ocr.Converters;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.service.AttachmentService;

@RestController
@RequestMapping("/api")
@MultipartConfig(fileSizeThreshold = 5971520)
public class AttachmentResource {

    private AttachmentService attachmentService;
    private MobileConfiguration mobileConf;
    private final Logger log = LoggerFactory.getLogger(AttachmentResource.class);
    @Inject
    private PurchaseOrderRepository purchaseOrderRepository;
    private File tmpDir;

    @Autowired
    public AttachmentResource(final AttachmentService attachmentService, final MobileConfiguration mobileConf) {
        this.attachmentService = attachmentService;
        this.mobileConf = mobileConf;

        tmpDir = new File(this.mobileConf.getAttachmentDirectory());
    }

    @RequestMapping(value = "/attachments", method = RequestMethod.POST)
    @Timed
    public Callable<Attachment> createAttachment(@RequestParam String deliveryNumber,
            @RequestParam String description,
            @RequestParam String category,
            @RequestParam(defaultValue = "true") Boolean visible,
            @RequestParam("file") MultipartFile file) throws IOException {
        log.debug("createAttachment[{},{},{},{},{}]", deliveryNumber, description, category, visible, file.getName());
        log.debug("writing temp file to : {}", tmpDir.getAbsolutePath());
        String originalFileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
        String originalExtension = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
        File attachmentFile = Converters.multipartToFile(tmpDir, originalFileName, originalExtension, file);
        return new Callable<Attachment>() {
            public Attachment call() throws Exception {
                return attachmentService.createAttachment(deliveryNumber,
                        description,
                        category,
                        file.getContentType(),
                        visible,
                        attachmentFile);
            }
        };
    }

    @RequestMapping(value = "/purchaseOrders/{id}/attachments", method = RequestMethod.POST)
    @Timed
    public Callable<Attachment> createAttachment(@PathVariable Long id,
                                                 @RequestParam String description,
                                                 @RequestParam String category,
                                                 @RequestParam(defaultValue = "true") Boolean visible,
                                                 @RequestParam("file") MultipartFile file) throws IOException {
        log.debug("createAttachment[{},{},{},{},{}]", id, description, category, visible, file.getName());
        log.debug("writing temp file to : {}", tmpDir.getAbsolutePath());
        String originalFileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
        String originalExtension = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
        File attachmentFile = Converters.multipartToFile(tmpDir, originalFileName, originalExtension, file);
        return new Callable<Attachment>() {
            public Attachment call() throws Exception {
                PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(id);
                if(purchaseOrder != null) {
                    return attachmentService.createAttachmentAgainstPO(purchaseOrder,
                        description,
                        category,
                        file.getContentType(),
                        visible,
                        attachmentFile);
                }else{
                    log.error("PO {} not found in db, please double check", id);
                    return null;
                }
            }
        };
    }

    @RequestMapping(value = "/attachments/{uuid}", method = RequestMethod.GET)
    @Timed
    public Callable<ResponseEntity<ByteArrayResource>> readAttachment(@PathVariable String uuid,
                                                                      @RequestParam(defaultValue = "true") Boolean activated) {
        return new Callable<ResponseEntity<ByteArrayResource>>() {
            public ResponseEntity<ByteArrayResource> call() throws Exception {
                ReadAttachmentDto readAttachment = attachmentService.readAttachment(uuid, activated);
                return ResponseEntity
                        .ok()
                        .contentLength(readAttachment.getAttachment().length)
                        .contentType(MediaType.parseMediaType(readAttachment.getMimeType()))
                        .body(new ByteArrayResource(readAttachment.getAttachment()));
            }
        };
    }

    @RequestMapping(value = "/attachments/{uuid}", method = RequestMethod.DELETE)
    @Timed
    public void deleteAttachment(@PathVariable String uuid) {
        attachmentService.deleteAttachment(uuid);
    }

    @RequestMapping(value = "/attachments", method = RequestMethod.GET)
    @Timed
    public List<Attachment> listAttachments(@RequestParam String deliveryNumber,
            @RequestParam(required = false) Boolean visible) {
        return attachmentService.listAttachments(deliveryNumber, visible);
    }
}
