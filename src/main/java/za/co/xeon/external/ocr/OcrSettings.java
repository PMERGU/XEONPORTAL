package za.co.xeon.external.ocr;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by derick on 2016/02/07.
 */
@Configuration
@ConfigurationProperties(prefix="ocr", locations={"application.yml"})
public class OcrSettings {
    public enum TaskStatus {
		Unknown, Submitted, Queued, InProgress, Completed, ProcessingFailed, Deleted, NotEnoughCredits
	}

    private String application;
    private String password;
    private String uri;

    public OcrSettings() {
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
