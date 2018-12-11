package nl.hypothermic.javacogs.authentication;

import javax.net.ssl.HttpsURLConnection;

import nl.hypothermic.javacogs.AuthenticationMethod;

public class KeySecretAuthenticationMethod implements AuthenticationMethod {
	
	private final String key, secret;
	
	/**
	 * Also known as the Discogs Auth Flow.<br>
	 * <br>
	 * This class simply holds the user's <code>key</code> and <code>secret</code> and appends a Authorization header to the HttpsURLConnection.<br>
	 * <br>
	 * For the token method, see {@link nl.hypothermic.javacogs.authentication.KeySecretAuthenticationMethod#KeySecretAuthenticationMethod(String, String)}
	 * 
	 * @see <a href="https://www.discogs.com/developers/#page:authentication,header:authentication-discogs-auth-flow">
	 *          https://www.discogs.com/developers/#page:authentication,header:authentication-discogs-auth-flow
	 *      </a>
	 */
	public KeySecretAuthenticationMethod(final String key, final String secret) {
		if (key == null || secret == null) {
			throw new IllegalArgumentException("Key and/or secret cannot be null.");
		}
		this.key = key;
		this.secret = secret;
	}

	public void beforeRequest() {
		// TODO Auto-generated method stub
		
	}

	public void applyHttpParameters(HttpsURLConnection connection) {
		connection.addRequestProperty("Authorization", "Discogs key=" + key + ", secret=" + secret);
	}

	public void afterRequest() {
		// TODO Auto-generated method stub
		
	}
}
