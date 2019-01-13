package nl.hypothermic.javacogs;

import nl.hypothermic.javacogs.authentication.TokenAuthenticationMethod;
import nl.hypothermic.javacogs.concurrency.ResponseCallback;
import nl.hypothermic.javacogs.entities.CollectionValue;
import nl.hypothermic.javacogs.handlers.Handler;
import nl.hypothermic.javacogs.network.Response;

/**
 * Sample class for contributers to test (new) functions.<br>
 * <br>
 * Not to be used in production.
 */
public class Debugger {
	
	public static void main(String[] args) throws Exception {
		// sample: get user's "All" folder
		Javacogs.getInstance().setAuthenticationMethod(new TokenAuthenticationMethod(System.getenv("debug.token")));
		Javacogs.getInstance().getHandler(Handler.USER_COLLECTION).getCollectionValue("Buurthuis", new ResponseCallback<CollectionValue>() {
			@Override public void onResult(Response<CollectionValue> response) {
				if (response.hasSucceeded()) {
					i("Value: " + response.getValue().toString());
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
