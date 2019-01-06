package nl.hypothermic.javacogs;

import java.util.Arrays;

import nl.hypothermic.javacogs.concurrency.UncheckedCallback;
import nl.hypothermic.javacogs.entities.CollectionRelease;
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
		Javacogs.getInstance().getHandler(Handler.USER_COLLECTION).getFolderContents("Buurthuis", 0, new UncheckedCallback<CollectionRelease[]>() {
			public void onResult(Response<CollectionRelease[]> response) {
				if (response.hasSucceeded()) {
					for (CollectionRelease iter : response.getValue()) {
						i(iter.toString());
					}
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
