package nl.hypothermic.javacogs.network;

import nl.hypothermic.javacogs.entities.Entity;

public class Response<T extends Entity> {
	
	private final boolean hasSucceeded;
	
	private final T value;
	
	public Response(boolean hasSucceeded, T value) {
		this.hasSucceeded = hasSucceeded;
		this.value = value;
	}
	
	public boolean hasSucceeded() {
		return hasSucceeded;
	}
	
	public T getValue() {
		return value;
	}
}
