package nl.hypothermic.javacogs.entities;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Master (aka "Master Release")
 */
public class Master extends Entity {
	
	// TODO: styles
	
	// TODO: genres
	
	// TODO: videos
	
	@JSONField(name="title")
	public String title;
	
	@JSONField(name="main_release")
	public String mainRelease;
	
	@JSONField(name="main_release_url")
	public String mainReleaseUrl;
	
	@JSONField(name="uri")
	public String uri;
	
	// TODO: artists
	
	@JSONField(name="versions_url")
	public String versionsUrl;
	
	@JSONField(name="year")
	public short releaseYear;
	
	// TODO: images
	
	@JSONField(name="resource_url")
	public String resourceUrl;
	
	// TODO: tracklist
	
	@JSONField(name="id")
	public String id;
	
	@JSONField(name="num_for_sale")
	public int numForSale;
	
	@JSONField(name="lowest_price")
	public double lowestPrice;
	
	@JSONField(name="data_quality")
	public String dataQuality;

	public String getTitle() {
		return this.title;
	}

	public String getMainRelease() {
		return this.mainRelease;
	}

	public String getMainReleaseUrl() {
		return this.mainReleaseUrl;
	}

	public String getUri() {
		return this.uri;
	}

	public String getVersionsUrl() {
		return this.versionsUrl;
	}

	public short getReleaseYear() {
		return this.releaseYear;
	}

	public String getResourceUrl() {
		return this.resourceUrl;
	}

	public String getId() {
		return this.id;
	}

	public int getNumForSale() {
		return this.numForSale;
	}

	public double getLowestPrice() {
		return this.lowestPrice;
	}

	public String getDataQuality() {
		return this.dataQuality;
	}

	@Override public String toString() {
		return "Master [title=" + this.title + ", mainRelease=" + this.mainRelease + ", mainReleaseUrl=" + this.mainReleaseUrl + ", uri=" + this.uri + ", versionsUrl=" + this.versionsUrl + ", releaseYear=" + this.releaseYear + ", resourceUrl=" + this.resourceUrl + ", id=" + this.id + ", numForSale=" + this.numForSale + ", lowestPrice=" + this.lowestPrice + ", dataQuality=" + this.dataQuality + "]";
	}
}
