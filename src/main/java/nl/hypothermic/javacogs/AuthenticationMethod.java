package nl.hypothermic.javacogs;

import javax.net.ssl.HttpsURLConnection;

public interface AuthenticationMethod {
	
	public void beforeRequest();
	
	public void applyHttpParameters(HttpsURLConnection connection);
	
	public void afterRequest();

}
