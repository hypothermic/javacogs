package nl.hypothermic.javacogs.concurrency;

import nl.hypothermic.javacogs.entities.Entity;
import nl.hypothermic.javacogs.network.Response;

public interface UncheckedCallback<T extends Object> {
	
	void onResult(Response<T> response);

}
