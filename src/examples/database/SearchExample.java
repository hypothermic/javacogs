package database;

import java.io.IOException;

import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.SearchBuilder;
import nl.hypothermic.javacogs.authentication.KeySecretAuthenticationMethod;
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
 * Example program which interacts with Discogs API through the Javacogs client.
 */
public class SearchExample {
	
	public static void main(String[] args) throws Exception {
		
		// This is an advanced example. If you have never worked with Javacogs before,
		// it is heavily recommended you try something simpler first.
		
		// For searching the database, you need to Authenticate to Discogs:
		
		Javacogs.getInstance().setAuthenticationMethod(
				
				// You can either authentication using a Token:
				
				new TokenAuthenticationMethod("YOUR-TOKEN")
				
				// or using a key/secret pair:
				
				//new KeySecretAuthenticationMethod("YOUR-KEY", "YOUR-SECRET")
				
		);
		
		// Searching for anything relating "Flip de Vogelaar" (my favourite artist)
		
		Javacogs.getInstance().getHandler(Handler.DATABASE).getEntitiesBySearch(new SearchBuilder().setQuery("Flip de Vogelaar"), 
																				new UncheckedCallback<SearchResult[]>() {
			public void onResult(Response<SearchResult[]> response) {
				if (response.hasSucceeded()) {
					
					// Iterate through the results
					
					for (SearchResult result : response.getValue()) {
						
						try {
							
							// Get the full Entity from the search result
							
							Javacogs.getInstance().getHandler(Handler.DATABASE).getEntityFromSearchResult(result, 
																										  new UncheckedCallback<Entity>() {
								public void onResult(Response<Entity> response) {
									
									Entity e = response.getValue();
									
									// Cast the Entity into a superclass
									
									if (e instanceof ArtistGroup) {
										System.out.println(((ArtistGroup) e).toString());
									} else if (e instanceof ArtistMember) {
										System.out.println(((ArtistMember) e).toString());
									} else if (e instanceof Label) {
										System.out.println(((Label) e).toString());
									} else if (e instanceof Master) {
										System.out.println(((Master) e).toString());
									} else if (e instanceof Release) {
										System.out.println(((Release) e).toString());
									}
									
								}
							});
							
						} catch (IOException x) {
							// TODO: handle this exception
						}
						
					}
				} else {
					// TODO: handle this exception
				}
			}
		});
	}
}
