package nl.hypothermic.javacogs;

import nl.hypothermic.javacogs.entities.Entity;
import nl.hypothermic.javacogs.network.Response;

public interface ResponseCallback<T extends Entity> {
	
	void onResult(Response<T> response);

}
