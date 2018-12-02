package nl.hypothermic.javacogs;

import javax.net.ssl.HttpsURLConnection;

/**
 * No-op auth method.
 * 
 * Does exactly what it advertises: nothing.
 */
public class NoopAuthenticationMethod implements AuthenticationMethod {

	public void beforeRequest() {
		;
	}

	public void applyHttpParameters(HttpsURLConnection connection) {
		;
	}

	public void afterRequest() {
		;
	}
}
