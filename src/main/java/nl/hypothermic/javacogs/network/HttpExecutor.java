package nl.hypothermic.javacogs.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import nl.hypothermic.javacogs.AuthenticationMethod;
import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.exception.RateLimitReachedException;

public class HttpExecutor {
	
	private final Javacogs instance;
	
	public HttpExecutor(final Javacogs instance) {
		this.instance = instance;
	}
	
	/**
	 * Issue an authenticated HTTP-GET request to the <code>address</code>.
	 * 
	 * @param address 	Full URI including the protocol.
	 */
	public String get(String address) throws IOException {
		// I just felt like writing K&R C... good old times.
		final AuthenticationMethod am = instance.getAuthenticationMethod();
		final StringBuilder result = new StringBuilder();
	    final URL url = new URL(address);
	    HttpsURLConnection conn;
	    String line;
	    BufferedReader rd;
		
		if (instance.getRateLimiter().hasReachedLimit()) {
			throw new RateLimitReachedException();
		}
		
	    am.beforeRequest();
	    
	    conn = (HttpsURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Accept", "application/vnd.discogs.v2.plaintext+json");
	    conn.setRequestProperty("User-Agent", "Javacogs/1.0 +https://github.com/hypothermic/javacogs");
	    
	    am.applyHttpParameters(conn);
	    instance.getRateLimiter().fetchRegulationsFrom(conn);
	    
	    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    while ((line = rd.readLine()) != null) {
	       result.append(line);
	    }
	    rd.close();
	    
	    conn.disconnect();
	    am.afterRequest();
	    
	    return result.toString();
	}
}
