package nl.hypothermic.javacogs.entities;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Label - "The Label resource represents a label, company, recording studio, location, 
 * or other entity involved with Artists and Releases. Labels were recently expanded in 
 * scope to include things that aren’t labels – the name is an artifact of this history."
 */
public class Label extends Entity {
	
	public Label() {
		
	}
	
	public Label(int labelId) {
		this.id = labelId;
	}
	
	@JSONField(name="id")
	public int id;
	
	@JSONField(name="name")
	public String name;
	
	@JSONField(name="profile")
	public String profile;
	
	@JSONField(name="contact_info")
	public String contactInfo;
	
	// TODO; sublabels
	
	// TODO: images
	
	// TODO: urls
	
	@JSONField(name="uri")
	public String uri;
	
	@JSONField(name="releases_url")
	public String releasesUrl;
	
	@JSONField(name="resource_url")
	public String resourceUrl;
	
	@JSONField(name="data_quality")
	public String dataQuality;

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getProfile() {
		return this.profile;
	}

	public String getContactInfo() {
		return this.contactInfo;
	}

	public String getUri() {
		return this.uri;
	}

	public String getReleasesUrl() {
		return this.releasesUrl;
	}

	public String getResourceUrl() {
		return this.resourceUrl;
	}

	public String getDataQuality() {
		return this.dataQuality;
	}
	
	@Override public String toString() {
		return "Label [id=" + this.id + ", name=" + this.name + ", profile=" + this.profile + ", contactInfo=" + this.contactInfo + ", uri=" + this.uri + ", releasesUrl=" + this.releasesUrl + ", resourceUrl=" + this.resourceUrl + ", dataQuality=" + this.dataQuality + "]";
	}
}
