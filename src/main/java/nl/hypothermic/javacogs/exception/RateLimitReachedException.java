package nl.hypothermic.javacogs.exception;

import java.io.IOException;

public class RateLimitReachedException extends IOException {
	
	public RateLimitReachedException() {
		super("Rate limit has been reached.");
	}
}
