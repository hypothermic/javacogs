package nl.hypothermic.javacogs.entities;

import com.alibaba.fastjson.annotation.JSONField;

public class ArtistMember extends ArtistWrapper {
	
	@JSONField(name="active")
	public boolean active;
	
	@JSONField(name="id")
	public int id;

	@JSONField(name="name")
	public String name;
	
	@JSONField(name="realname")
	public String realName;
	
	@JSONField(name="profile")
	public String profile;
	
	@JSONField(name="profile_plaintext")
	public String profileText;
	
	@JSONField(name="data_quality")
	public String dataQuality;
	
	@JSONField(name="uri")
	public String uri;
	
	@JSONField(name="resource_url")
	public String resourceUrl;
	
	// TODO: String[] urls
	
	@JSONField(name="releases_url")
	public String releasesUrl;
	
	// TODO: groups
	
	// TODO: images
	
	// TODO: aliases
	
	// TODO: name variations
	
	@Override public String toString() {
		return "ArtistMember [active=" + this.active + ", id=" + this.id + ", name=" + this.name + ", realName=" + this.realName + ", profile=" + this.profile + ", profileText=" + this.profileText + ", dataQuality=" + this.dataQuality + ", uri=" + this.uri + ", resourceUrl=" + this.resourceUrl + ", releasesUrl=" + this.releasesUrl + "]";
	}
}