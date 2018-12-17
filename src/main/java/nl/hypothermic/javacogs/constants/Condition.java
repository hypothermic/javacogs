package nl.hypothermic.javacogs.constants;

public enum Condition {
	
	MINT(			"Mint (M)", 			"Mint", 			"M"		),
	NEAR_MINT(		"Near Mint (NM or M-)", "Near Mint", 		"NM"	),
	VERY_GOOD_PLUS(	"Very Good Plus (VG+)", "Very Good Plus", 	"VG+"	),
	VERY_GOOD(		"Very Good (VG)", 		"Very Good", 		"VG"	),
	GOOD_PLUS(		"Good Plus (G+)", 		"Good Plus", 		"G+"	),
	GOOD(			"Good (G)", 			"Good",				"G"		),
	FAIR(			"Fair (F)", 			"Fair",				"F"		),
	POOR(			"Poor (P)", 			"Poor",				"P"		),
	
	;
	
	/**
	 * See {@link nl.hypothermic.javacogs.constants.Condition#getDescription()}
	 */
	private final String description;
	
	/**
	 * See {@link nl.hypothermic.javacogs.constants.Condition#getEnglishName()}
	 */
	private final String name;
	
	/**
	 * See {@link nl.hypothermic.javacogs.constants.Condition#getAbbrevation()}
	 */
	private final String abbrevation;
	
	Condition(String description, String name, String abbrevation) {
		this.description = description;
		this.name = name;
		this.abbrevation = abbrevation;
	}
	
	/**
	 * Full string used by Discogs API v2.<br>
	 * <br>
	 * Ex.: <code>Very Good Plus (VG+)</code>
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * English name.<br>
	 * <br>
	 * Ex.: <code>Very Good Plus</code>
	 */
	public String getEnglishName() {
		return name;
	}
	
	/**
	 * 1-2 character abbrevation.<br>
	 * <br>
	 * Ex.: <code>VG+</code>
	 */
	public String getAbbrevation() {
		return abbrevation;
	}
}
