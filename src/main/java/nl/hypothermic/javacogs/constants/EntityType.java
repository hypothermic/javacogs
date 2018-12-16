package nl.hypothermic.javacogs.constants;

import nl.hypothermic.javacogs.entities.ArtistWrapper;
import nl.hypothermic.javacogs.entities.Label;
import nl.hypothermic.javacogs.entities.Master;
import nl.hypothermic.javacogs.entities.Release;

public enum EntityType {
	
	RELEASE("release", Release.class),
	MASTER("master", Master.class),
	ARTIST("artist", ArtistWrapper.class),
	LABEL("label", Label.class),
	
	;
	
	/**
	 * Abbrevation used by Discogs API v2.
	 */
	private final String abbr;
	
	/**
	 * Related Javacogs (base) class.
	 */
	private final Class clazz;
	
	EntityType(String abbr, Class clazz) {
		this.abbr = abbr;
		this.clazz = clazz;
	}
	
	public String getAbbrevation() {
		return abbr;
	}
	
	public Class getClazz() {
		return clazz;
	}
	
	public static EntityType fromString(String str) {
		if (str.equals("release")) {
			return EntityType.RELEASE;
		} else if (str.equals("master")) {
			return EntityType.MASTER;
		} else if (str.equals("artist")) {
			return EntityType.ARTIST;
		} else if (str.equals("label")) {
			return EntityType.LABEL;
		}
		return null;
	}
}
