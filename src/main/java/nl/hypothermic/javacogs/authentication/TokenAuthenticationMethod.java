package nl.hypothermic.javacogs.authentication;

import javax.net.ssl.HttpsURLConnection;

import nl.hypothermic.javacogs.AuthenticationMethod;

public class TokenAuthenticationMethod implements AuthenticationMethod {
	
	private final String token;
	
	/**
	 * Also known as the Discogs Auth Flow.<br>
	 * <br>
	 * This class simply holds the user's <code>token</code> and appends a Authorization header to the HttpsURLConnection.<br>
	 * <br>
	 * For the key/secret method, see {@link nl.hypothermic.javacogs.authentication.KeySecretAuthenticationMethod#KeySecretAuthenticationMethod(String, String)}
	 * 
	 * @see <a href="https://www.discogs.com/developers/#page:authentication,header:authentication-discogs-auth-flow">
	 *          https://www.discogs.com/developers/#page:authentication,header:authentication-discogs-auth-flow
	 *      </a>
	 */
	public TokenAuthenticationMethod(final String token) {
		if (token == null) {
			throw new IllegalArgumentException("Token cannot be null.");
		}
		this.token = token;
	}

	public void beforeRequest() {
		// TODO Auto-generated method stub
		
	}

	public void applyHttpParameters(HttpsURLConnection connection) {
		connection.addRequestProperty("Authorization", "Discogs token=" + token);
	}

	public void afterRequest() {
		// TODO Auto-generated method stub
		
	}
}
