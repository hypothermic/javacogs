package nl.hypothermic.javacogs;

import java.util.Arrays;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

import nl.hypothermic.javacogs.concurrency.UncheckedCallback;
import nl.hypothermic.javacogs.entities.ArtistGroup;
import nl.hypothermic.javacogs.entities.ArtistMember;
import nl.hypothermic.javacogs.entities.ArtistWrapper;
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
		// sample: get master release
		Javacogs.getInstance().getHandler(Handler.DATABASE).getReleasesByArtist(108713, new UncheckedCallback<Release[]>() {
			public void onResult(Response<Release[]> response) {
				if (response.hasSucceeded()) {
					System.out.println(Arrays.toString(response.getValue()));
				} else {
					System.out.println("Response failed.");
				}
			}
		});
	}
}
