package nl.hypothermic.javacogs.constants;

public enum SortOrder {
	
	ASCENDING("asc", "Ascending"),
	DESCENDING("desc", "Descending"),
	
	;
	
	/**
	 * See {@link nl.hypothermic.javacogs.constants.Condition#getEnglishName()}
	 */
	private final String englishName;
	
	/**
	 * See {@link nl.hypothermic.javacogs.constants.Condition#getAbbrevation()}
	 */
	private final String abbrevation;
	
	SortOrder(String abbrevation, String englishName) {
		this.englishName = englishName;
		this.abbrevation = abbrevation;
	}
	
	/**
	 * English name.<br>
	 * <br>
	 * Ex.: <code>Ascending</code>
	 */
	public String getEnglishName() {
		return englishName;
	}
	
	/**
	 * 3-4 character abbrevation used by the Discogs API<br>
	 * <br>
	 * Ex.: <code>asc</code>
	 */
	public String getAbbrevation() {
		return abbrevation;
	}
}
