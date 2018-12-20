package nl.hypothermic.javacogs;

import java.io.IOException;
import java.util.Arrays;

import nl.hypothermic.javacogs.authentication.TokenAuthenticationMethod;
import nl.hypothermic.javacogs.concurrency.ResponseCallback;
import nl.hypothermic.javacogs.concurrency.UncheckedCallback;
import nl.hypothermic.javacogs.entities.ArtistGroup;
import nl.hypothermic.javacogs.entities.ArtistMember;
import nl.hypothermic.javacogs.entities.Entity;
import nl.hypothermic.javacogs.entities.Label;
import nl.hypothermic.javacogs.entities.Master;
import nl.hypothermic.javacogs.entities.Release;
import nl.hypothermic.javacogs.entities.SearchResult;
import nl.hypothermic.javacogs.entities.UserProfile;
import nl.hypothermic.javacogs.handlers.Handler;
import nl.hypothermic.javacogs.network.Response;

/**
 * Sample class for contributers to test (new) functions.<br>
 * <br>
 * Not to be used in production.
 */
public class Debugger {
	
	public static void main(String[] args) throws Exception {
		// sample: get user's submissions	
		Javacogs.getInstance().setAuthenticationMethod(new TokenAuthenticationMethod("TOKEN"));
		Javacogs.getInstance().getHandler(Handler.USER_IDENTITY).getUserSubmissions("Buurthuis", new UncheckedCallback<Entity[]>() {
			public void onResult(Response<Entity[]> response) {
				if (response.hasSucceeded()) {
					i(Arrays.toString(response.getValue()));
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
