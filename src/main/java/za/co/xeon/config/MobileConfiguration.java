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


    public MobileConfiguration() {
    }

    public String getPodDirectory() {
        return podDirectory;
    }

    public void setPodDirectory(String podDirectory) {
        this.podDirectory = podDirectory;
    }
}
