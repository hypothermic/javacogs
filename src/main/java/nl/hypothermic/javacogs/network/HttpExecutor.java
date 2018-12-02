package nl.hypothermic.javacogs.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpExecutor {
	
	/**
	 * Issue a HTTP-GET request to the <code>address</code>.
	 * 
	 * @param address 	Full URI including the protocol.
	 */
	public String get(String address) throws IOException {
		StringBuilder result = new StringBuilder();
	    URL url = new URL(address);
	    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Accept", "application/vnd.discogs.v2.plaintext+json");
	    conn.setRequestProperty("User-Agent", "Javacogs/1.0 +https://github.com/hypothermic/javacogs");
	    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line;
	    while ((line = rd.readLine()) != null) {
	       result.append(line);
	    }
	    rd.close();
	    return result.toString();
	}
}
