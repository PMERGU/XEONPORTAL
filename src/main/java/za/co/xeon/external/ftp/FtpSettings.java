package za.co.xeon.external.ftp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by derick on 2016/02/07.
 */
@Configuration
@ConfigurationProperties(prefix = "ftp", locations = { "application.yml" })
public class FtpSettings {
	private String host;
	private String user;
	private String pass;
	private String folder;

	public FtpSettings() {
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
}
