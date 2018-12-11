package nl.hypothermic.javacogs.entities;

import com.alibaba.fastjson.annotation.JSONField;

public class ArtistMember extends ArtistWrapper {
	
	@JSONField(name="active")
	public boolean active;
	
	@JSONField(name="id")
	public int id;

	@JSONField(name="name")
	public String name;
	
	@JSONField(name="resource_url")
	public String resourceUrl;

	@Override public String toString() {
		return "ArtistMember [active=" + this.active + ", id=" + this.id + ", name=" + this.name + ", resourceUrl=" + this.resourceUrl + "]";
	}
}