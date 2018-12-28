package nl.hypothermic.javacogs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import nl.hypothermic.javacogs.concurrency.UncheckedCallback;
import nl.hypothermic.javacogs.entities.CollectionFolder;
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
		// sample: get user's submissions	
		//Javacogs.getInstance().setAuthenticationMethod(new TokenAuthenticationMethod("TOKEN"));
		Javacogs.getInstance().getHandler(Handler.DATABASE).getReleasesByArtist(108713, new UncheckedCallback<Release[]>() {
			public void onResult(Response<Release[]> response) {
				if (response.hasSucceeded()) {
					ArrayList<Release> releases = new ArrayList();
					for (Release iter : response.getValue()) {
						try {
							releases.add(iter.resolve());
						} catch (IOException x) {
							// TODO Auto-generated catch block
							x.printStackTrace();
						}
					}
					i(releases.toString());
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
