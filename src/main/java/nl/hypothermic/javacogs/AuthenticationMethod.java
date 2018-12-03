package nl.hypothermic.javacogs;

import javax.net.ssl.HttpsURLConnection;

import nl.hypothermic.javacogs.authentication.DiscogsKeySecretAuthMethod;
import nl.hypothermic.javacogs.authentication.DiscogsTokenAuthMethod;

public interface AuthenticationMethod {
	
	enum Methods {
		Token(DiscogsTokenAuthMethod.class),
		KeySecret(DiscogsKeySecretAuthMethod.class),
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
