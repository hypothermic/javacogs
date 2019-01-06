package nl.hypothermic.javacogs.entities;

import com.alibaba.fastjson.annotation.JSONField;

import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.handlers.Handler;

/**
 * A collection is arranged into folders.<br>
 * <br>
 * <i>Every user has two permanent folders already:<br>
 *    <br>
 *    - ID 0, the “All” folder, which cannot have releases added to it, and<br>
 *    - ID 1, the “Uncategorized” folder.
 * </i>
 */
public class CollectionFolder extends Entity {

	@JSONField(name="id")
	public short id;
	
	@JSONField(name="count")
	public int count;
	
	@JSONField(name="name")
	public String name;
	
	@JSONField(name="resource_url")
	public String resourceUrl;

	@Override public String toString() {
		return "CollectionFolder [id=" + this.id + ", count=" + this.count + ", name=" + this.name + ", resourceUrl=" + this.resourceUrl + "]";
	}
}
