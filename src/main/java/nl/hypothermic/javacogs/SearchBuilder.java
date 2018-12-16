package nl.hypothermic.javacogs;

import nl.hypothermic.javacogs.constants.EntityType;
import nl.hypothermic.javacogs.entities.ArtistGroup;
import nl.hypothermic.javacogs.entities.ArtistMember;
import nl.hypothermic.javacogs.entities.Label;

public class SearchBuilder implements Cloneable {
	
	private final StringBuilder stringBuilder = new StringBuilder();
	private boolean isFirstParameter = true;
	
	/*private String query, type, title, releaseTitle, credit, 
				   artist, anv, label, genre, style, country, format, 
				   catNo, barcode, track, submitter, contributer;
	  private short year;*/
	
	/**
	 * Append parameter to the StringBuilder in a HTML-compliant manner. 
	 */
	private void appendParameter(String parameter, String value) {
		if (isFirstParameter) {
			stringBuilder.append('?');
			isFirstParameter = false;
		} else {
			stringBuilder.append('&');
		}
		stringBuilder.append(parameter)
					 .append('=')
					 .append(value);
	}
	
	public SearchBuilder setQuery(String query) {
		//this.query = query;
		appendParameter("query", query);
		return this;
	}
	
	public SearchBuilder setType(String type) {
		//this.type = type;
		appendParameter("type", type);
		return this;
	}
	
	public SearchBuilder setType(EntityType type) {
		//this.type = type.getAbbrevation();
		appendParameter("type", type.getAbbrevation());
		return this;
	}
	
	public SearchBuilder setTitle(String title) {
		//this.title = title;
		appendParameter("title", title);
		return this;
	}
	
	public SearchBuilder setReleaseTitle(String releaseTitle) {
		//this.releaseTitle = releaseTitle;
		appendParameter("release_title", releaseTitle);
		return this;
	}
	
	public SearchBuilder setCredit(String credit) {
		//this.credit = credit;
		appendParameter("credit", credit);
		return this;
	}
	
	public SearchBuilder setArtist(String artist) {
		//this.artist = artist;
		appendParameter("artist", artist);
		return this;
	}
	
	public SearchBuilder setArtist(ArtistGroup group) {
		//this.artist = group.name;
		appendParameter("artist", group.name);
		return this;
	}
	
	public SearchBuilder setArtist(ArtistMember member) {
		//this.artist = member.name;
		appendParameter("artist", member.name);
		return this;
	}
	
	/**
	 * Search for Artist Name Variation (also known as ANV).
	 */
	public SearchBuilder setArtistNameVariation(String anv) {
		//this.anv = anv;
		appendParameter("anv", anv);
		return this;
	}
	
	public SearchBuilder setLabel(String label) {
		//this.label = label;
		appendParameter("label", label);
		return this;
	}
	
	public SearchBuilder setLabel(Label label) {
		//this.label = label.name;
		appendParameter("label", label.getName());
		return this;
	}
	
	public SearchBuilder setGenre(String genre) {
		//this.genre = genre;
		appendParameter("genre", genre);
		return this;
	}
	
	public SearchBuilder setStyle(String style) {
		//this.style = style;
		appendParameter("style", style);
		return this;
	}
	
	public SearchBuilder setCountry(String country) {
		//this.country = country;
		appendParameter("country", country);
		return this;
	}
	
	public SearchBuilder setYear(short year) {
		//this.year = year;
		appendParameter("year", year + "");
		return this;
	}
	
	public SearchBuilder setFormat(String format) {
		//this.format = format;
		appendParameter("format", format);
		return this;
	}
	
	public SearchBuilder setCatalogNumber(String catNo) {
		//this.catNo = catNo;
		appendParameter("catno", catNo);
		return this;
	}
	
	public SearchBuilder setBarcode(String barcode) {
		//this.barcode = barcode;
		appendParameter("barcode", barcode);
		return this;
	}
	
	public SearchBuilder setTrack(String track) {
		//this.track = track;
		appendParameter("track", track);
		return this;
	}
	
	public SearchBuilder setSubmitter(String submitter) {
		//this.submitter = submitter;
		appendParameter("submitter", submitter);
		return this;
	}
	
	public SearchBuilder setContributer(String contributer) {
		//this.contributer = contributer;
		appendParameter("contributer", contributer);
		return this;
	}
	
	public boolean isEmpty() {
		return this.isFirstParameter;
	}
	
	public String toParameters() {
		return stringBuilder.toString().replace(" ", "_");
	}
	
	// TODO: execute
}
