package nl.hypothermic.javacogs.authentication;

import javax.net.ssl.HttpsURLConnection;

import nl.hypothermic.javacogs.AuthenticationMethod;

/**
 * No-operation auth method.<br>
 * <br>
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
