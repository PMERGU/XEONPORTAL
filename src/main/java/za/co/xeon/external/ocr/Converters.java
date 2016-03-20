package za.co.xeon.external.ocr;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by derick on 2016/03/02.
 */
public class Converters {

    public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = File.createTempFile(multipart.getOriginalFilename(), "");
        multipart.transferTo(convFile);
        return convFile;
    }

}
