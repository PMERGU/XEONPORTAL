package za.co.xeon.external.ocr;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by derick on 2016/03/02.
 */
public class Converters {
	public static File multipartToFile(File directory, String name, String extension, MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = File.createTempFile(name + "-", "." + extension, directory);
		multipart.transferTo(convFile);
		return convFile;
	}
}
