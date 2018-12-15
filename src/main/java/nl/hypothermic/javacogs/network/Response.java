package nl.hypothermic.javacogs.network;

import nl.hypothermic.javacogs.entities.Entity;

public class Response<T extends Object> {
	
	private final boolean hasSucceeded;
	
	public final T value;
	
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
