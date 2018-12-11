package nl.hypothermic.javacogs;

public enum AuthenticationType {
	
	/**
	 * Items labeled with PUBLIC can be accessed without authentication 
	 * 
	 * (or with {@link nl.hypothermic.javacogs.NoopAuthenticationMethod#NoopAuthenticationMethod()})
	 */
	PUBLIC,
	/**
	 * Items labeled with PROTECTED must be accessed from an authenticated Javacogs instance.<br>
	 * <br>
	 * See {@link nl.hypothermic.javacogs.Javacogs#setAuthenticationMethod(AuthenticationMethod)}
	 */
	PROTECTED

}
