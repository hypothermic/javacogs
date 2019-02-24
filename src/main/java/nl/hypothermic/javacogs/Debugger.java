package nl.hypothermic.javacogs;

import nl.hypothermic.javacogs.authentication.TokenAuthenticationMethod;
import nl.hypothermic.javacogs.concurrency.UncheckedCallback;
import nl.hypothermic.javacogs.handlers.Handler;
import nl.hypothermic.javacogs.network.Response;

/**
 * Sample class for contributers to test (new) functions.<br>
 * <br>
 * <b>Not to be used in production.</b>
 */
public class Debugger {
	
	public static void main(String[] args) throws Exception {
		// sample: get amount of labels in the database using the ApiStatisticsHandler
		Javacogs.getInstance().setAuthenticationMethod(new TokenAuthenticationMethod(System.getenv("debug.token")));
		Javacogs.getInstance().getHandler(Handler.STATISTICS).getLabelsCount(new UncheckedCallback<Long>() {
			@Override public void onResult(Response<Long> response) {
				if (response.hasSucceeded()) {
					i("Total amount of labels: " + response.getValue());
				} else {
					i("Response failed");
				}
			}
		});
		Javacogs.getInstance().getHandler(Handler.STATISTICS).getReleasesCount(new UncheckedCallback<Long>() {
			@Override public void onResult(Response<Long> response) {
				if (response.hasSucceeded()) {
					i("Total amount of releases: " + response.getValue());
				} else {
					i("Response failed");
				}
			}
		});
		Javacogs.getInstance().getHandler(Handler.STATISTICS).getArtistsCount(new UncheckedCallback<Long>() {
			@Override public void onResult(Response<Long> response) {
				if (response.hasSucceeded()) {
					i("Total amount of artists: " + response.getValue());
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
