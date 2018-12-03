package nl.hypothermic.javacogs.authentication;

import javax.net.ssl.HttpsURLConnection;

import nl.hypothermic.javacogs.AuthenticationMethod;

public class DiscogsTokenAuthMethod implements AuthenticationMethod {
	
	private final String token;
	
	public DiscogsTokenAuthMethod(final String token) {
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
