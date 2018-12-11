package nl.hypothermic.javacogs.entities;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;

public class ArtistGroup extends ArtistWrapper {
	
	@JSONField(name="name")
	public String name;
	
	@JSONField(name="members")
	public JSONArray _members;
	
	// TODO: Image[] images
	
	@JSONField(name="data_quality")
	public String dataQuality;
	
	@JSONField(name="uri")
	public String uri;
	
	// TODO: String[] urls
	
	@JSONField(name="releases_url")
	public String releasesUrl;
	
	@JSONField(name="resource_url")
	public String resourceUrl;
	
	@JSONField(name="namevariations")
	public String[] nameVariations;
	
	public List<ArtistMember> getMembers() {
		return _members.toJavaList(ArtistMember.class);
		//return _members.toArray(new ArtistMember[_members.size() + 1]);
	}

	@Override public String toString() {
		return "ArtistGroup [name=" + this.name + ", _members=" + this._members + ", dataQuality=" + this.dataQuality + ", uri=" + this.uri + ", releasesUrl=" + this.releasesUrl + ", resourceUrl=" + this.resourceUrl + ", nameVariations=" + Arrays.toString(this.nameVariations) + "]";
	}
}
