package nl.hypothermic.javacogs.entities;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Note: even though this class implements ResolvableEntity, 
 * most of the time it is already returned in fully resolved form.
 */
public class CollectionRelease extends ResolvableEntity<Release> {
	
	public CollectionRelease() {
		super(Release.class);
	}

	@JSONField(name="date_added")
	public String dateAdded;
	
	@JSONField(name="instance_id")
	public long instanceId;
	
	@JSONField(name="rating")
	public short rating;
	
	@JSONField(name="basic_information")
	public String releaseJson;

	@JSONField(name="id")
	public long releaseId;
	
	@JSONField(name="folder_id")
	public short folderId;

	public String getDateAdded() {
		return this.dateAdded;
	}

	public long getInstanceId() {
		return this.instanceId;
	}

	public short getRating() {
		return this.rating;
	}

	public Release getRelease() {
		return JSON.parseObject(this.releaseJson, Release.class);
	}

	public long getReleaseId() {
		return this.releaseId;
	}

	public short getFolderId() {
		return this.folderId;
	}
	
	@Override protected String getResolveUrl() {
		return this.getRelease().getResolveUrl();
	}

	@Override public String toString() {
		return "CollectionRelease [dateAdded=" + this.dateAdded + ", instanceId=" + this.instanceId + ", rating=" + this.rating + ", releaseJson=" + this.releaseJson + ", releaseId=" + this.releaseId + ", folderId=" + this.folderId + "]";
	}
}
