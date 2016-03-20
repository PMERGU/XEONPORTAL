package za.co.xeon.external.ocr;

import za.co.xeon.external.ocr.dto.Result;
import za.co.xeon.external.ocr.dto.Task;
import za.co.xeon.external.ocr.dto.xsd.result.Document;
import za.co.xeon.external.ocr.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by derick on 2016/02/08.
 */

@RestController
//Max uploaded file size (here it is 20 MB)
@MultipartConfig(fileSizeThreshold = 5971520)
public class OcrRestService {
    private final static Logger log = LoggerFactory.getLogger(OcrRestService.class);

    @Autowired
    private OcrService ocrService;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/ocr", method = RequestMethod.POST)
    public Task scanDocument(@RequestParam("podDocument") MultipartFile podDocument) {
        try {
            // Get name of uploaded file.
            File tmpFile = Converters.multipartToFile(podDocument);
            Result result = ocrService.scanDocument(tmpFile.getPath());
            return result.getTask();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @RequestMapping(value = "/ocr/{id}", method = RequestMethod.GET)
    public Task getStatus(@PathVariable(value="id") String id) {
        try {
            Result result = ocrService.getStatus(id);
            return result.getTask();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @RequestMapping(value = "/ocr/{id}/result", method = RequestMethod.GET)
    public Document getResult(@PathVariable(value="id") String id) {
        try {
            Result result = ocrService.getStatus(id);
            log.debug("Result url is " + result.getTask().getResultUrl());
            if (result.getTask().getStatus() == OcrSettings.TaskStatus.Completed) {
                    return ocrService.getCompletedResult(result.getTask().getResultUrl());
//                return result.getTask();
		    }else {
                throw new IllegalArgumentException("Invalid task status : " + result.getTask().getStatus().name());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
    }



}
