package nl.hypothermic.javacogs.exception;

import java.io.IOException;

public class AuthenticationException extends IOException {
	
	public AuthenticationException(String reason) {
		super(reason);
	}
}
