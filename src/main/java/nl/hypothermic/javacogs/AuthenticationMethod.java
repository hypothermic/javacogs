package nl.hypothermic.javacogs;

import javax.net.ssl.HttpsURLConnection;

import nl.hypothermic.javacogs.authentication.KeySecretAuthenticationMethod;
import nl.hypothermic.javacogs.authentication.TokenAuthenticationMethod;

public interface AuthenticationMethod {
	
	/**
	 * Enumeration of all authentication methods.
	 * 
	 * @see {@link nl.hypothermic.javacogs.authentication.TokenAuthenticationMethod#TokenAuthenticationMethod(String)}
	 * @see {@link nl.hypothermic.javacogs.authentication.KeySecretAuthenticationMethod#KeySecretAuthenticationMethod(String, String)}
	 */
	enum Methods {
		
		Token(TokenAuthenticationMethod.class),
		KeySecret(KeySecretAuthenticationMethod.class),
		//OAuth1_0A(OAuthMethod.class),
		
		;
		
		private Class clazz;
		
		private Methods(Class clazz) {
			this.clazz = clazz;
		}
		
		public Class getClazz() {
			return clazz;
		}
	}
	
	public void beforeRequest();
	
	public void applyHttpParameters(HttpsURLConnection connection);
	
	public void afterRequest();

}
