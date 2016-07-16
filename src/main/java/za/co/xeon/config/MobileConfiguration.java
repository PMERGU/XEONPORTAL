package za.co.xeon.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by derick on 2016/02/07.
 */
@Configuration
@ConfigurationProperties(prefix="mobile", locations={"application.yml"})
public class MobileConfiguration {
    private String podDirectory;
    private String attachmentDirectory;
    private String httpServerName;

    public MobileConfiguration() {
    }

    public String getPodDirectory() {
        return podDirectory;
    }

    public void setPodDirectory(String podDirectory) {
        this.podDirectory = podDirectory;
    }

    public String getAttachmentDirectory() {
        return attachmentDirectory;
    }

    public void setAttachmentDirectory(final String attachmentDirectory) {
        this.attachmentDirectory = attachmentDirectory;
    }

    public String getHttpServerName() {
        return httpServerName;
    }

    public void setHttpServerName(String httpServerName) {
        this.httpServerName = httpServerName;
    }
}
