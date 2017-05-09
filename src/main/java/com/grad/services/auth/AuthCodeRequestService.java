package com.grad.services.auth;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grad.config.AuthorizationProperties;

/**
 * @author nikos_mas
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AuthCodeRequestService {

	@Autowired
	private AuthorizationProperties properties;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private final static Logger LOG = LoggerFactory.getLogger("Fitbit application");
	private static String OS = System.getProperty("os.name");
	

	public void codeRequest() {
		try {
			if (OS.equalsIgnoreCase("ind")) {

				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + properties.getAuthCodeUri1() + "client_id="
						+ redisTemplate.opsForValue().get("Client-id") + "&" + properties.getAuthCodeUri2());

			} else if (OS.equalsIgnoreCase("linux")) {

				Runtime.getRuntime().exec("xdg-open " + properties.getAuthCodeUri1() + "client_id="
						+ redisTemplate.opsForValue().get("Client-id") + "&" + properties.getAuthCodeUri2());
			}
		} catch (IOException e) {
			LOG.error(e.toString());
		}
	}
}