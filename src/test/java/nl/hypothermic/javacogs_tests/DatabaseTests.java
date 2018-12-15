package nl.hypothermic.javacogs_tests;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.ResponseCallback;
import nl.hypothermic.javacogs.entities.ArtistGroup;
import nl.hypothermic.javacogs.entities.ArtistMember;
import nl.hypothermic.javacogs.entities.ArtistWrapper;
import nl.hypothermic.javacogs.entities.Master;
import nl.hypothermic.javacogs.entities.Release;
import nl.hypothermic.javacogs.handlers.Handler;
import nl.hypothermic.javacogs.network.Response;

public class DatabaseTests {
	
	/**
	 * Fails if Javacogs couldn't get/parse response:
	 * <pre><code>if (response.hasSucceeded()) {</code></pre>
	 * or the response was invalid: (
	 * <pre><code>Assert.assertEquals(response.getValue().getReleaseYear(), 1987);</code></pre>
	 */
	@Test
    public void getReleaseByIdTest() throws IOException {
        Javacogs.getInstance().getHandler(Handler.DATABASE).getReleaseById(249504, new ResponseCallback<Release>() {
			public void onResult(Response<Release> response) {
				if (response.hasSucceeded()) {
					System.out.println(response.getValue().toString());
					Assert.assertEquals(response.getValue().getReleaseYear(), 1987);
				} else {
					Assert.fail("DatabaseTests: getReleaseByIdTest: Response failed.");
				}
			}
		});
    }
	
	/**
	 * Fails if Javacogs couldn't get/parse response:
	 * <pre><code>if (response.hasSucceeded()) {</code></pre>
	 * or the response was invalid: (
	 * <pre><code>Assert.assertEquals(response.getValue().getReleaseYear(), 1997);</code></pre>
	 */
	@Test
    public void getMasterByIdTest() throws IOException {
        Javacogs.getInstance().getHandler(Handler.DATABASE).getMasterById(1000, new ResponseCallback<Master>() {
			public void onResult(Response<Master> response) {
				if (response.hasSucceeded()) {
					System.out.println(response.getValue().toString());
					Assert.assertEquals(response.getValue().getReleaseYear(), 1997);
				} else {
					Assert.fail("DatabaseTests: getMasterByIdTest: Response failed.");
				}
			}
		});
    }
	
	/**
	 * Fails if Javacogs couldn't get/parse response:
	 * <pre><code>if (response.hasSucceeded()) {</code></pre>
	 * or the response was invalid: (
	 * <pre><code>Assert.assertEquals(((ArtistGroup) wrapper).name, "Nickelback");</code></pre>
	 */
	@Test
    public void getArtistByIdTest() throws IOException {
        Javacogs.getInstance().getHandler(Handler.DATABASE).getArtistById(108713, new ResponseCallback<ArtistWrapper>() {
			public void onResult(Response<ArtistWrapper> response) {
				ArtistWrapper wrapper = response.getValue();
				if (!(wrapper instanceof ArtistGroup)) {
					Assert.fail("DatabaseTests: getArtistByIdTest: 108713 != group");
					ArtistGroup group = (ArtistGroup) wrapper;
					System.out.println(group.toString());
				}
				Assert.assertEquals(((ArtistGroup) wrapper).name, "Nickelback");
			}
		});
    }
}
