package nl.hypothermic.javacogs.network;

import nl.hypothermic.javacogs.entities.Entity;

public class PageResponse<T extends Entity> extends Response<T> {

	public PageResponse(boolean hasSucceeded, T value) {
		super(hasSucceeded, value);
	}
	
	public PageResponse<T> getNextPage() {
		return null;
	}
}
