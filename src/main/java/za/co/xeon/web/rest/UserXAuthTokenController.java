package za.co.xeon.web.rest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import za.co.xeon.security.AuthoritiesConstants;
import za.co.xeon.security.BlockedLoginException;
import za.co.xeon.security.LoginAttemptService;
import za.co.xeon.security.xauth.Token;
import za.co.xeon.security.xauth.TokenProvider;

@RestController
@RequestMapping("/api")
public class UserXAuthTokenController {
    private final Logger log = LoggerFactory.getLogger(UserXAuthTokenController.class);

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private LoginAttemptService loginAttemptService;

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private HttpServletRequest request;

    @RequestMapping(value = "/authenticate",
        method = RequestMethod.POST)
    @Timed
    public Token authorize(@RequestParam String username, @RequestParam String password) {
        String ip = getClientIP();
        String lowercaseLogin = username.toLowerCase();
        log.debug("[ip:{}] - Authenticating {}", ip, lowercaseLogin);
        if (loginAttemptService.isBlocked(ip)) {
            log.debug("[ip:{}] - Blocking {} from trying to login. Brute force detected. Rejecting access", ip, lowercaseLogin);
            throw new BlockedLoginException("User " + lowercaseLogin + " does not exists. Authentication Blocked");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails details = this.userDetailsService.loadUserByUsername(username);

        loginAttemptService.loginSucceeded(ip);

        if (details.getAuthorities().stream()
            .filter(authority ->
                authority.getAuthority().equals(AuthoritiesConstants.ADMIN) || authority.getAuthority().equals(AuthoritiesConstants.USER)
            ).count() > 0) {
            log.debug("Providing long lived token for Xeon/Admin user.");
            return tokenProvider.createToken(details, (60 * 60 * 12));// auth token valid for 10 hours
        } else {
            return tokenProvider.createToken(details, (60 * 60));
        }

    }


    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
