package nl.hypothermic.javacogs;

import nl.hypothermic.javacogs.constants.Currency;
import nl.hypothermic.javacogs.entities.Release;
import nl.hypothermic.javacogs.handlers.Handler;
import nl.hypothermic.javacogs.network.Response;

/**
 * Sample class for contributers to test (new) functions.<br>
 * <br>
 * Not to be used in production.
 */
public class Debugger {
	
	public static void main(String[] args) throws Exception {
		// sample: test rate limiter
		for (int i = 0; i < 35; i++) {
			Javacogs.getInstance().getHandler(Handler.DATABASE).getReleaseById(249504, Currency.USD, new ResponseCallback<Release>() {
				public void onResult(Response<Release> response) {
					if (response.hasSucceeded()) {
						System.out.println(response.getValue().toString());
					} else {
						System.out.println("Response failed.");
					}
				}
			});
		}
	}
}
