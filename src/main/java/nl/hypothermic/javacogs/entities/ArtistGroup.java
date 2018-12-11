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
	
	public List<ArtistMember> getMembers() {
		return _members.toJavaList(ArtistMember.class);
		//return _members.toArray(new ArtistMember[_members.size() + 1]);
	}

	@Override public String toString() {
		return "Artist [name=" + this.name + ", members=" + this.getMembers().toString() + "]";
	}
}
