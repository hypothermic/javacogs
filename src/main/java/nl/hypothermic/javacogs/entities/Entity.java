package nl.hypothermic.javacogs.entities;

/**
 * Every entity superclass must be optimized for loading by FastJSON.<br>
 * <br>
 * The main goal of the Entity hierarchy is to safe cast and fill the responses.
 */
public abstract class Entity {
	
	public abstract String toString();

}
