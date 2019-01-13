package nl.hypothermic.javacogs.entities;

import com.alibaba.fastjson.annotation.JSONField;

import nl.hypothermic.javacogs.constants.Currency;

public class CollectionValue extends Entity {
	
	@JSONField(name = "minimum")
	public String minimum;
	
	@JSONField(name = "median")
	public String median;
	
	@JSONField(name = "maximum")
	public String maximum;
	
	/*public Currency getCurrency() {
		
	}*/

	public double getMinimum() {
		return Double.parseDouble(this.minimum.replaceAll("[^\\d.]", ""));
	}

	public double getMedian() {
		return Double.parseDouble(this.median.replaceAll("[^\\d.]", ""));
	}

	public double getMaximum() {
		return Double.parseDouble(this.maximum.replaceAll("[^\\d.]", ""));
	}
	
	@Override public String toString() {
		return "CollectionValue [minimum=" + this.minimum + ", median=" + this.median + ", maximum=" + this.maximum + "]";
	}
}
