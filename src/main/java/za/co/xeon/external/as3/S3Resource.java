package za.co.xeon.external.as3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by derick on 2016/02/16.
 */
@Controller
@RequestMapping(value = "/s3")
public class S3Resource {
	private final static Logger log = LoggerFactory.getLogger(S3Resource.class);

	@Autowired
	private S3Service s3Service;

	// @RequestMapping(value = "/{fileName:.+}", method= RequestMethod.GET)
	// @Secured(AuthoritiesConstants.ADMIN)
	// public ResponseEntity<InputStreamResource>
	// retrieveFile(@PathVariable("fileName") String fileName,
	// HttpServletResponse response) {
	// log.debug("Retrieving S3 file " + fileName);
	// try {
	// // get your file as InputStream
	// InputStream is = s3Service.retrieveFile(fileName);
	// return ResponseEntity
	// .ok()
	// .contentLength(is.available())
	// .contentType(MediaType.parseMediaType("application/octet-stream"))
	// .body(new InputStreamResource(is));
	// } catch (IOException ex) {
	// log.info("Error writing file to output stream. Filename was '{}'",
	// fileName, ex);
	// throw new RuntimeException("IOError writing file to output stream");
	// }
	// }

	// @RequestMapping(value = "/folder/{folderName}", method=
	// RequestMethod.GET)
	// @Secured(AuthoritiesConstants.ADMIN)
	// public ResponseEntity<List<String>>
	// retrieveListing(@PathVariable("folderName") String folderName,
	// HttpServletResponse response) {
	// log.debug("Retrieving listing for bucket");
	// List<String> files = new ArrayList<>();
	//
	// ObjectListing objectListing = s3Service.listFolder(folderName);
	//
	// for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries())
	// {
	// files.add(objectSummary.getKey());
	// }
	// return new ResponseEntity<>(files, HttpStatus.OK);
	// }
	//
	//
	// @RequestMapping(method= RequestMethod.POST)
	// public @ResponseBody
	// String uploadFile(@RequestParam("fileName") String fileName,
	// @RequestParam("file") MultipartFile file){
	// if (!file.isEmpty()) {
	// try{
	// s3Service.uploadFile(fileName, file.getInputStream());
	//
	// return "You successfully uploaded " + fileName + "!";
	// } catch (Exception e) {
	// return "You failed to upload " + fileName + " => " + e.getMessage();
	// }
	// } else {
	// return "You failed to upload " + fileName + " because the file was empty.
	// Please include a multipart file.";
	// }
	// }
}
