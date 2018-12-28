package nl.hypothermic.javacogs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
		Javacogs.getInstance().getHandler(Handler.USER_COLLECTION).getFoldersByUser("Buurthuis", new UncheckedCallback<CollectionFolder[]>() {
			public void onResult(Response<CollectionFolder[]> response) {
				if (response.hasSucceeded()) {
					i(Arrays.toString(response.getValue()));
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
