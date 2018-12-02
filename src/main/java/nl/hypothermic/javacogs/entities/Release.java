package nl.hypothermic.javacogs.entities;

import com.alibaba.fastjson.annotation.JSONField;

public class Release extends Entity {
	
	@JSONField(name="title")
	public String title;
	
	@JSONField(name="id")
	public String id;
	
	// TODO: artists
	
	@JSONField(name="data_quality")
	public String dataQuality;
	
	@JSONField(name="thumb")
	public String thumbnailUrl;
	
	// TODO: community
	
	// TODO: companies
	
	@JSONField(name="country")
	public String country;
	
	@JSONField(name="date_added")
	public String dateAdded;
	
	@JSONField(name="date_changed")
	public String dateChanged;
	
	@JSONField(name="estimated_weight")
	public short estimatedWeight;
	
	// TODO: extraartists
	
	@JSONField(name="format_quantity")
	public short formatQuantity;
	
	// TODO: formats
	
	// TODO: genres
	
	// TODO: identifiers
	
	// TODO: images
	
	// TODO: labels
	
	@JSONField(name="lowest_price")
	public double lowestPrice;
	
	@JSONField(name="master_url")
	public String masterUrl;
	
	@JSONField(name="notes")
	public String notes;
	
	@JSONField(name="num_for_sale")
	public int numForSale;
	
	@JSONField(name="released")
	public String released;

	@JSONField(name="released_formatted")
	public String releasedFormatted;
	
	@JSONField(name="resource_url")
	public String resourceUrl;
	
	// TODO: series
	
	@JSONField(name="status")
	public String status; // TODO: create Status enum
	
	// TODO: styles
	
	// TODO: tracklist
	
	@JSONField(name="uri")
	public String url;
	
	// TODO: videos
	
	@JSONField(name="year")
	public short releaseYear;

	public String getTitle() {
		return this.title;
	}

	public String getId() {
		return this.id;
	}

	public String getDataQuality() {
		return this.dataQuality;
	}

	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}

	public String getCountry() {
		return this.country;
	}

	public String getDateAdded() {
		return this.dateAdded;
	}

	public String getDateChanged() {
		return this.dateChanged;
	}

	public short getEstimatedWeight() {
		return this.estimatedWeight;
	}

	public short getFormatQuantity() {
		return this.formatQuantity;
	}

	public double getLowestPrice() {
		return this.lowestPrice;
	}

	public String getMasterUrl() {
		return this.masterUrl;
	}

	public String getNotes() {
		return this.notes;
	}

	public int getNumForSale() {
		return this.numForSale;
	}

	public String getReleased() {
		return this.released;
	}

	public String getReleasedFormatted() {
		return this.releasedFormatted;
	}

	public String getResourceUrl() {
		return this.resourceUrl;
	}

	public String getStatus() {
		return this.status;
	}

	public String getUrl() {
		return this.url;
	}

	public short getReleaseYear() {
		return this.releaseYear;
	}
	
	@Override public String toString() {
		return "Release [title=" + this.title + ", id=" + this.id + ", dataQuality=" + this.dataQuality + ", thumbnailUrl=" + this.thumbnailUrl + ", country=" + this.country + ", dateAdded=" + this.dateAdded + ", dateChanged=" + this.dateChanged + ", estimatedWeight=" + this.estimatedWeight + ", formatQuantity=" + this.formatQuantity + ", lowestPrice=" + this.lowestPrice + ", masterUrl=" + this.masterUrl + ", notes=" + this.notes + ", numForSale=" + this.numForSale + ", released=" + this.released + ", releasedFormatted=" + this.releasedFormatted + ", resourceUrl=" + this.resourceUrl
				+ ", status=" + this.status + ", url=" + this.url + ", releaseYear=" + this.releaseYear + "]";
	}
}
