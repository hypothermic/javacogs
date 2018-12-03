package database;

import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.ResponseCallback;
import nl.hypothermic.javacogs.entities.Release;
import nl.hypothermic.javacogs.handlers.Handler;
import nl.hypothermic.javacogs.network.Response;

/**
 * Example program which interacts with Discogs API through the Javacogs client.
 */
public class ReleaseInformationExample {
	
	public static void main(String[] args) throws Exception {
		
		int releaseId = 249504; // id of Rick Astley - Never Gonna Give You Up
		
		// Use the global Javacogs instance to get the Database Handler.
		//
		// The Database Handler can be seen as an endpoint which
		// handles all interactions related to the Discogs Database.
		
		Javacogs.getInstance().getHandler(Handler.DATABASE)
		
		// then, call the getReleaseById() function and pass the following values:
		//
		// - releaseId 	= id of the release determined by Discogs.
		//
		// - cb 		= a callback which will be called by Javacogs
		//				  once the information has been received.
		
		.getReleaseById(releaseId, new ResponseCallback<Release>() {
			
			public void onResult(Response<Release> response) {
				
				// The information has been received. We can handle it now.
				//
				// First, check if the response has been successful.
				//
				// If not, the response.getValue() will most likely be null
				// and we can't use it.
				
				if (response.hasSucceeded()) {
					
					// Retrieve the requested release information from the response:
					
					Release release = response.getValue();
					
					// Print it out to confirm it works:
					
					System.out.println(release.toString());
					
				} else {
					
					// Whoops! The response has failed due one of the following reasons:
					//
					// - Network unavailible
					//
					// - Unknown exception
					
					System.out.println("Response failed.");
					
				}
			}
			
		});
	}
}
