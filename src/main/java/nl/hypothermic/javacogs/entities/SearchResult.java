package nl.hypothermic.javacogs.entities;

import com.alibaba.fastjson.annotation.JSONField;

import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.constants.EntityType;

/**
 * This is a wrapper for any search result which can be crafted
 * into an Entity (Release, Master, Label, ArtistWrapper, etc.)
 * <br>
 * See {@link nl.hypothermic.javacogs.entities.SearchResult#toEntity()}
 */
public class SearchResult {
	
	@JSONField(name="type")
	public String type;
	
	@JSONField(name="id")
	public String id;
	
}
