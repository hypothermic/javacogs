package nl.hypothermic.javacogs.entities;

/**
 * ArtistWrapper is the base artist class.<br>
 * <br>
 * The objects returned by Javacogs can always be casted into ArtistGroup or ArtistMember.<br>
 * <br>
 * See relevant javadoc at function: {@link nl.hypothermic.javacogs.handlers.DatabaseHandler#getArtistById(int, nl.hypothermic.javacogs.ResponseCallback)}
 */
public class ArtistWrapper extends Entity {
	
	/**
	 * Do not use this constructor.<br>
	 * <br>
	 * The only reason it exists, is because the JSON parser requires it.<br>
	 * <br>
	 * Use {@link nl.hypothermic.javacogs.entities.ArtistWrapper#ArtistWrapper(int)} instead and pass the <code>artistId</code> as parameter.
	 */
	public ArtistWrapper() {
		
	}
	
	public ArtistWrapper(int artistId) {
		this.artistId = artistId;
	}
	
	private int artistId;
	
	public int getId() {
		return artistId;
	}
	
	public void setId(int artistId) {
		this.artistId = artistId;
	}

	@Override public String toString() {
		return "ArtistWrapper [artistId=" + this.artistId + "]";
	}
}
