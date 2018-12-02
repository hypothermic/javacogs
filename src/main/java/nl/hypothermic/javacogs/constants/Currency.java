package nl.hypothermic.javacogs.constants;

public enum Currency {
	
	USD("USD", "United States Dollar"),
	GBP("GBP", "British pound sterling"),
	EUR("EUR", "Euro"),
	CAD("CAD", "Canadian Dollar"),
	AUD("AUD", "Australian Dollar"),
	JPY("JPY", "Japanese Yen"),
	CHF("CHF", "Swiss Franc"),
	MXN("MXN", "Mexican Peso"),
	BRL("BRL", "Brazilian Real"),
	NZD("NZD", "New Zealand Dollar"),
	SEK("SEK", "Swedish Krona"),
	ZAR("ZAR", "South African Rand"),
	
	;
	
	/**
	 * Abbrevation used by Discogs API v2.
	 * 
	 * ISO country code format.
	 */
	private final String abbr;
	
	/**
	 * English name.
	 */
	private final String name;
	
	Currency(String abbr, String name) {
		this.abbr = abbr;
		this.name = name;
	}
	
	public String getAbbrevation() {
		return abbr;
	}
	
	public String getEnglishName() {
		return name;
	}
}
