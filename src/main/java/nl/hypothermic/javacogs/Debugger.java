package nl.hypothermic.javacogs;

import nl.hypothermic.javacogs.entities.ArtistGroup;
import nl.hypothermic.javacogs.entities.ArtistMember;
import nl.hypothermic.javacogs.entities.ArtistWrapper;
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
		Javacogs.getInstance().getHandler(Handler.DATABASE).getArtistById(270222, new ResponseCallback<ArtistWrapper>() {
			public void onResult(Response<ArtistWrapper> response) {
				ArtistWrapper wrapper = response.getValue();
				if (wrapper instanceof ArtistGroup) {
					ArtistGroup group = (ArtistGroup) wrapper;
				} else if (wrapper instanceof ArtistMember) {
					ArtistMember member = (ArtistMember) wrapper;
				}
			}
		});
	}
}
