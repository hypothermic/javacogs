package nl.hypothermic.javacogs;

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
		//Javacogs.getInstance().setAuthenticationMethod(new TokenAuthenticationMethod("TOKEN"));
		Javacogs.getInstance().getHandler(Handler.USER_COLLECTION).getFolderById("Buurthuis", 0, new UncheckedCallback<CollectionFolder>() {
			public void onResult(Response<CollectionFolder> response) {
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
