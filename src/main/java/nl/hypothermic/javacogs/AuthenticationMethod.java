package nl.hypothermic.javacogs;

import javax.net.ssl.HttpsURLConnection;

import nl.hypothermic.javacogs.authentication.KeySecretAuthenticationMethod;
import nl.hypothermic.javacogs.authentication.TokenAuthenticationMethod;

public interface AuthenticationMethod {
	
	/**
	 * Enumeration of all authentication methods and their corresponding classes (<code>getClazz()</code>).
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
	
	/**
	 * This function will be called before a HTTP request will be made.
	 */
	public void beforeRequest();
	
	/**
	 * This function will be called when the connection has been opened.<br>
	 * <br>
	 * The connection headers have been set already but the data has not been read yet.
	 * 
	 * @param connection	Opened HTTPS connection
	 */
	public void applyHttpParameters(HttpsURLConnection connection);
	
	/**
	 * This function will be called after the connection has been closed.
	 */
	public void afterRequest();

}
