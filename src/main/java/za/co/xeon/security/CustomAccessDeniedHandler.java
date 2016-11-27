package za.co.xeon.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Derick on 8/15/2016.
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Inject
	private LoginAttemptService loginAttemptService;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.debug("================================== B-LOGIN ==================================");
		log.debug("Login failed");
		log.debug("================================== E-LOGIN ==================================");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
	}
}
