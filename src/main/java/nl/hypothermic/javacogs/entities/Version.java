package nl.hypothermic.javacogs.entities;

import com.alibaba.fastjson.annotation.JSONField;

public class Version extends Entity {
	
	@JSONField(name="status")
	public String status;

	@Override public String toString() {
		return "Version [status=" + this.status + "]";
	}
}
