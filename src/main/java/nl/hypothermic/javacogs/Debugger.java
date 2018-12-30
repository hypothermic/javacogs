package nl.hypothermic.javacogs;

import nl.hypothermic.javacogs.authentication.TokenAuthenticationMethod;
import nl.hypothermic.javacogs.concurrency.UncheckedCallback;
import nl.hypothermic.javacogs.entities.CollectionFolder;
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
		Javacogs.getInstance().setAuthenticationMethod(new TokenAuthenticationMethod(System.getenv("debug.token")));
		Javacogs.getInstance().getHandler(Handler.USER_COLLECTION).deleteFolderById("Buurthuis", 1649204, new UncheckedCallback<Boolean>() {
			public void onResult(Response<Boolean> response) {
				if (response.hasSucceeded()) {
					i(response.getValue().toString());
				} else {
					i("Response failed.");
				}
			}
		});
	}
	
	public static void i(String msg) {
		System.out.println(msg);
	}
}
