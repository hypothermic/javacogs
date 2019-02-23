package nl.hypothermic.javacogs.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import nl.hypothermic.javacogs.AuthenticationMethod;
import nl.hypothermic.javacogs.Debugger;
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
	 * @param address
	 *            Full URI including the protocol.
	 */
	public String get(String address) throws IOException {
		return this.execute(address, "GET", true);
	}
	
	public String post(String address) throws IOException {
		return this.execute(address, "POST", true);
	}

	public boolean delete(String address) throws IOException {
		return this.execute(address, "DELETE", false).equals("204");
	}

	private String execute(String address, String protocol, boolean read) throws IOException {
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
		conn.setRequestMethod(protocol);
		conn.setRequestProperty("Accept", "application/vnd.discogs.v2.plaintext+json");
		conn.setRequestProperty("User-Agent", Javacogs.USER_AGENT);

		am.applyHttpParameters(conn);
		instance.getRateLimiter().fetchRegulationsFrom(conn);

		if (read) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();

			conn.disconnect();
			am.afterRequest();

			return result.toString();
		} else {
			return conn.getResponseCode() + "";
		}
	}
}
