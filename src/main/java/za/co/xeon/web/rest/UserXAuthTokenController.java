package za.co.xeon.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.xeon.security.AuthoritiesConstants;
import za.co.xeon.security.xauth.Token;
import za.co.xeon.security.xauth.TokenProvider;
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

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class UserXAuthTokenController {
    private final Logger log = LoggerFactory.getLogger(UserXAuthTokenController.class);

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate",
        method = RequestMethod.POST)
    @Timed
    public Token authorize(@RequestParam String username, @RequestParam String password) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails details = this.userDetailsService.loadUserByUsername(username);
        if (details.getAuthorities().stream()
            .filter(authority ->
                authority.getAuthority().equals(AuthoritiesConstants.ADMIN) || authority.getAuthority().equals(AuthoritiesConstants.USER)
            ).count() > 0) {
            log.debug("Providing long lived token for Xeon/Admin user.");
            return tokenProvider.createToken(details, (60 * 60 * 10));// auth token valid for 10 hours
        } else {
            return tokenProvider.createToken(details);
        }

    }
}
