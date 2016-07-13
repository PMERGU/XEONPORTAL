package za.co.xeon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No attachment found")
public class NoAttachmentoundException extends RuntimeException {
}
