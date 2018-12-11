package database;

import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.ResponseCallback;
import nl.hypothermic.javacogs.entities.ArtistGroup;
import nl.hypothermic.javacogs.entities.ArtistMember;
import nl.hypothermic.javacogs.entities.ArtistWrapper;
import nl.hypothermic.javacogs.entities.Release;
import nl.hypothermic.javacogs.handlers.Handler;
import nl.hypothermic.javacogs.network.Response;

/**
 * Example program which interacts with Discogs API through the Javacogs client.
 */
public class ArtistInformationExample {
	
	public static void main(String[] args) throws Exception {
		
		int artistId = 108713; // id of group Nickelback. This can also be a person,
		                       // for example 270222 (Chad Kroeger). In that case,
		                       // the ArtistWrapper below will be an instanceof ArtistMember.
		
		// Use the global Javacogs instance to get the Database Handler.
		//
		// The Database Handler can be seen as an endpoint which
		// handles all interactions related to the Discogs Database.
		
		Javacogs.getInstance().getHandler(Handler.DATABASE)
		
		// then, call the getArtistById() function and pass the following values:
		//
		// - artistId 	= id of the artist determined by Discogs.
		//
		// - cb 		= a callback which will be called by Javacogs
		//				  once the information has been received.
		
		.getArtistById(artistId, new ResponseCallback<ArtistWrapper>() {
			
			public void onResult(Response<ArtistWrapper> response) {
				
				// The information has been received. We can handle it now.
				//
				// First, check if the response has been successful.
				//
				// If not, the response.getValue() will most likely be null
				// and we can't use it.
				
				if (response.hasSucceeded()) {
					
					// Retrieve the requested artist wrapper from the response:
					
					ArtistWrapper wrapper = response.getValue();
					
					// Here's the difficult part: we've requested the artist,
					// but we don't know if it's a single person or a group.
					// We can figure out by casting the wrapper:
					
					if (wrapper instanceof ArtistGroup) {
						// it's a group of persons (ex. Nickelback)
						
						ArtistGroup group = (ArtistGroup) wrapper;
					}
					else if (wrapper instanceof ArtistMember) {
						// it's a single person (ex. Chad Kroeger)
						
						ArtistMember person = (ArtistMember) wrapper;
					}
					
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
