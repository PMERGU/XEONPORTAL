package za.co.xeon.security;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Created by Derick on 8/15/2016.
 */
@Service
@SuppressWarnings("unused")
public class LoginAttemptService {
	private final Logger log = LoggerFactory.getLogger(LoginAttemptService.class);

	private final int MAX_ATTEMPT = 5;
	private LoadingCache<String, Integer> attemptsCache;

	public LoginAttemptService() {
		super();
		attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS).build(new CacheLoader<String, Integer>() {
			@Override
			public Integer load(final String key) {
				return 0;
			}
		});
	}

	//

	public void loginSucceeded(final String key) {
		attemptsCache.invalidate(key);
	}

	public void loginFailed(final String key) {
		int attempts = 0;
		try {
			attempts = attemptsCache.get(key);
		} catch (final ExecutionException e) {
			attempts = 0;
		}
		attempts++;
		attemptsCache.put(key, attempts);
	}

	public boolean isBlocked(final String key) {
		/*try {
			return attemptsCache.get(key) >= MAX_ATTEMPT;
		} catch (final ExecutionException e) {
			return false;
		}
		*/ 
		//Security is disabled - to enable unblock the above code
		return false;
	}
}
