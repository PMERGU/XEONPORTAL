package za.co.xeon.web.rest.util;

import org.springframework.http.HttpHeaders;

/**
 * Utility class for http header creation.
 *
 */
public class HeaderUtil {

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-portalApp-alert", message);
        headers.add("X-portalApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert("A new " + entityName + " was created successfully. ID " + param, param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert("A " + entityName + " was updated successfully. ID " + param, param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert("A " + entityName + " was deleted successfully. ID " + param, param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-portalApp-error", defaultMessage);
        headers.add("X-portalApp-params", entityName);
        return headers;
    }

    public static HttpHeaders createFailureAlert(String defaultMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-portalApp-error", defaultMessage);
        return headers;
    }
}
