package nl.hypothermic.javacogs.network;

import javax.net.ssl.HttpsURLConnection;

public class RateLimiter {
	
	/**
	 * 60 seconds (60 000 milliseconds).
	 */
	public static final long TIMEFRAME_LENGTH = 60 * 1000;
	
	private int limit;
	private int used;
	//private int remaining; // we don't need remaining if we have used and limit...
	private long lastTimeframeStart;
	
	public RateLimiter() {
		limit = 30;
		resetTimeframe();
	}
	
	public void fetchRegulationsFrom(HttpsURLConnection connection) {
		used++;
		try {
			this.limit = Integer.valueOf(connection.getRequestProperty("X-Discogs-Ratelimit"));
			this.used = Integer.valueOf(connection.getRequestProperty("X-Discogs-Ratelimit-Used"));
			//this.remaining = Integer.valueOf(connection.getRequestProperty("X-Discogs-Ratelimit-Remaining"));
		} catch (NumberFormatException nfe) {
			System.out.println("Unable to retrieve rate limit headers from response."); //FIXME
		}
	}
	
	public boolean hasReachedLimit() {
		// check if timeframe has passed
		if (lastTimeframeStart + TIMEFRAME_LENGTH < System.currentTimeMillis()) {
			resetTimeframe();
		}
		// check if limit has been reached
		if (used >= limit) {
			return true;
		}
		return false;
	}
	
	private void resetTimeframe() {
		lastTimeframeStart = System.currentTimeMillis();
		this.used = 0;
		//this.remaining = this.limit;
	}
}
