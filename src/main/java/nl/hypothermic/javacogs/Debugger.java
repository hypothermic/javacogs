package nl.hypothermic.javacogs;

import nl.hypothermic.javacogs.entities.Master;
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
		Javacogs.getInstance().getHandler(Handler.DATABASE).getMasterById(1000, new ResponseCallback<Master>() {
			public void onResult(Response<Master> response) {
				if (response.hasSucceeded()) {
					System.out.println(response.getValue().toString());
				} else {
					System.out.println("Response failed.");
				}
			}
		});
	}
}
