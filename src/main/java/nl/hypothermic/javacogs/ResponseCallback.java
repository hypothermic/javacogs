package nl.hypothermic.javacogs;

import nl.hypothermic.javacogs.network.Response;

public interface ResponseCallback {
	
	void onResult(Response response);

}
