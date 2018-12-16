package nl.hypothermic.javacogs;

import java.io.IOException;

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
import nl.hypothermic.javacogs.handlers.Handler;
import nl.hypothermic.javacogs.network.Response;

/**
 * Sample class for contributers to test (new) functions.<br>
 * <br>
 * Not to be used in production.
 */
public class Debugger {
	
	public static void main(String[] args) throws Exception {
		// sample: get entities by search and resolve each entity
		Javacogs.getInstance().setAuthenticationMethod(new TokenAuthenticationMethod("TOKEN"));
		Javacogs.getInstance().getHandler(Handler.DATABASE).getEntitiesBySearch(new SearchBuilder().setQuery("flip_de_vogelaar"), new UncheckedCallback<SearchResult[]>() {
			public void onResult(Response<SearchResult[]> response) {
				if (response.hasSucceeded()) {
					for (SearchResult result : response.getValue()) {
						try {
							Javacogs.getInstance().getHandler(Handler.DATABASE).getEntityFromSearchResult(result, new UncheckedCallback<Entity>() {
								public void onResult(Response<Entity> response) {
									Entity e = response.getValue();
									if (e instanceof ArtistGroup) {
										i(((ArtistGroup) e).toString());
									} else if (e instanceof ArtistMember) {
										i(((ArtistMember) e).toString());
									} else if (e instanceof Label) {
										i(((Label) e).toString());
									} else if (e instanceof Master) {
										i(((Master) e).toString());
									} else if (e instanceof Release) {
										i(((Release) e).toString());
									} else {
										i("Cannot cast object!");
									}
								}
							});
						} catch (IOException x) {
							// TODO Auto-generated catch block
							x.printStackTrace();
						}
					}
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
