package nl.hypothermic.javacogs.authentication;

import javax.net.ssl.HttpsURLConnection;

import nl.hypothermic.javacogs.AuthenticationMethod;

public class DiscogsKeySecretAuthMethod implements AuthenticationMethod {
	
	private final String key, secret;
	
	public DiscogsKeySecretAuthMethod(final String key, final String secret) {
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
