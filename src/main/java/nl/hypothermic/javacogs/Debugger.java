package nl.hypothermic.javacogs;

import java.util.Arrays;

import nl.hypothermic.javacogs.authentication.TokenAuthenticationMethod;
import nl.hypothermic.javacogs.concurrency.ResponseCallback;
import nl.hypothermic.javacogs.concurrency.UncheckedCallback;
import nl.hypothermic.javacogs.entities.CollectionRelease;
import nl.hypothermic.javacogs.entities.Release;
import nl.hypothermic.javacogs.handlers.Handler;
import nl.hypothermic.javacogs.network.Response;

/**
 * Sample class for contributers to test (new) functions.<br>
 * <br>
 * <b>Not to be used in production.</b>
 */
public class Debugger {
	
	public static void main(String[] args) throws Exception {
		// sample: get user's wantlist and print individually
		// (this is a bad example, use .resolve() if you need in-sync resolve!!)
		Javacogs.getInstance().setAuthenticationMethod(new TokenAuthenticationMethod(System.getenv("debug.token")));
		Javacogs.getInstance().getHandler(Handler.USER_WANTLIST).addReleaseToWantlist("Buurthuis", 130076, "MyNotesPlease", (short) 5, new UncheckedCallback<Boolean>() {
			@Override public void onResult(Response<Boolean> response) {
				if (response.hasSucceeded()) {
					i("Result: " + response.getValue());
				} else {
					i("Response failed");
				}
			}
		});
	}
	
	public static void i(String msg) {
		System.out.println(msg);
	}
}
