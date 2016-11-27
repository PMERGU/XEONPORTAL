package za.co.xeon.security;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is throw in case of a not activated user trying to
 * authenticate.
 */
public class BlockedLoginException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 352486946028921567L;

	public BlockedLoginException(String message) {
		super(message);
	}

	public BlockedLoginException(String message, Throwable t) {
		super(message, t);
	}
}
