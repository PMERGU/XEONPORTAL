package za.co.xeon.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns a 401 error code (Unauthorized) to the client.
 */
@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(Http401UnauthorizedEntryPoint.class);

    @Inject
    private LoginAttemptService loginAttemptService;
    /**
     * Always returns a 401 error code to the client.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
        throws IOException,
        ServletException {

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress != null) {
            ipAddress = ipAddress.replaceFirst(",.*", "");  // cares only about the first IP if there is a list
        } else {
            ipAddress = request.getRemoteAddr();
        }

        if(arg2 instanceof BadCredentialsException){
            log.debug("[ip:{}] - UnauthorizedEntryPoint. Rejecting access and logging ip", ipAddress);
            loginAttemptService.loginFailed(ipAddress);
        }else {
            log.debug("[ip:{}] - Pre-authenticated entry point called. Rejecting access", ipAddress);
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
    }
}
