package nl.hypothermic.javacogs.handlers;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.json.JSONObject;

import nl.hypothermic.javacogs.AuthenticationType;
import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.ResponseCallback;
import nl.hypothermic.javacogs.UncheckedCallback;
import nl.hypothermic.javacogs.annotations.RequiredAuthenticationLevel;
import nl.hypothermic.javacogs.constants.Currency;
import nl.hypothermic.javacogs.entities.ArtistGroup;
import nl.hypothermic.javacogs.entities.ArtistMember;
import nl.hypothermic.javacogs.entities.ArtistWrapper;
import nl.hypothermic.javacogs.entities.Master;
import nl.hypothermic.javacogs.entities.Release;
import nl.hypothermic.javacogs.network.Response;

public class DatabaseHandler implements IHandler {
	
	private Javacogs instance;
	
	public DatabaseHandler(Javacogs instance) {
		this.instance = instance;
	}
	
	/**
	 * Get a release by id.
	 * 
	 * @return Release object
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getReleaseById(final int releaseId, final ResponseCallback<Release> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<Release>(true,
						JSON.parseObject(instance.getHttpExecutor().get(Javacogs.apiUrlBase + "releases/" + releaseId), 
										 Release.class)));
				} catch (IOException x) {
					cb.onResult(new Response<Release>(false, null));
				}
			}
		});
	}
	
	/**
	 * Get list of releases by artist id.
	 * 
	 * @return Release[] object
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getReleasesByArtist(final int artistId, final UncheckedCallback<Release[]> cb) throws IOException {
		this.getReleasesByArtist(new ArtistWrapper(artistId), cb);
	}
	
	/**
	 * Get list of releases by artist.
	 * 
	 * @return Release[] object
	 */
	// Holy shit, this code looks beautiful! Fits perfectly within the 140-char limit.
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getReleasesByArtist(final ArtistWrapper artist, final UncheckedCallback<Release[]> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<Release[]>(true,
							(Release[]) JSON.parseArray(new JSONObject(
									instance.getHttpExecutor().get(Javacogs.apiUrlBase + "artists/" + artist.getId() + "/releases"))
															.getJSONArray("releases").toString(), 
														Release.class)
						.toArray(new Release[] {})));
				} catch (IOException x) {
					cb.onResult(new Response<Release[]>(false, null));
				}
			}
		});
	}

	/**
	 * Get a release by id and specify preferred response currency.
	 * 
	 * @return Release object
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getReleaseById(final int releaseId, final Currency currency, final ResponseCallback<Release> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<Release>(true,
						JSON.parseObject(instance.getHttpExecutor().get(Javacogs.apiUrlBase + "releases/" + releaseId + "?" + currency.getAbbrevation()), 
										 Release.class)));
				} catch (IOException x) {
					cb.onResult(new Response<Release>(false, null));
				}
			}
		});
	}
	
	/**
	 * Get a master release by id.
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getMasterById(final int masterId, final ResponseCallback<Master> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<Master>(true,
						JSON.parseObject(instance.getHttpExecutor().get(Javacogs.apiUrlBase + "masters/" + masterId), 
										 Master.class)));
				} catch (IOException x) {
					cb.onResult(new Response<Master>(false, null));
				}
			}
		});
	}
	
	/**
	 * TODO
	 */
	/*@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getMasterVersionsById(final int masterId, final int page, final int perPage, final ResponseCallback<Version> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					System.out.println("RES: " + instance.getHttpExecutor().get(Javacogs.apiUrlBase + "masters/" + masterId + "/versions?{" + page + "," + perPage + "}"));
					cb.onResult(new PageResponse<Version>(true,
						JSON.parseObject(instance.getHttpExecutor().get(Javacogs.apiUrlBase + "masters/" + masterId + "/versions?{" + page + "," + perPage + "}"), 
										 Version.class)));
				} catch (IOException x) {
					cb.onResult(new PageResponse<Version>(false, null));
				}
			}
		});
	}*/
	
	/**
	 * Get an artist by id.<br>
	 * <br>
	 * The returned object can be casted into either an ArtistGroup or ArtistMember (Discogs didn't seperate these in the API).<br>
	 * Here is an example:
	 * <pre>
	 * <code>
	 * ArtistWrapper wrapper = response.getValue();
	 * if (wrapper instanceof ArtistGroup) {
	 * 	ArtistGroup group = (ArtistGroup) wrapper;
	 * } else if (wrapper instanceof ArtistMember) {
	 * 	ArtistMember member = (ArtistMember) wrapper;
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @return ArtistWrapper object
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getArtistById(final int artistId, final ResponseCallback<ArtistWrapper> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					String res = instance.getHttpExecutor().get(Javacogs.apiUrlBase + "artists/" + artistId);
					// the JSON parser will actually parse to ArtistGroup, but if it's a member, _members will be null.
					ArtistGroup group = JSON.parseObject(res, ArtistGroup.class);
					group.setId(artistId);
					if (group._members != null) {
						// return a group
						cb.onResult(new Response<ArtistWrapper>(true, group));
					} else {
						// return a member
						cb.onResult(new Response<ArtistWrapper>(true,
								JSON.parseObject(res, ArtistMember.class)));
					}
				} catch (IOException x) {
					cb.onResult(new Response<ArtistWrapper>(false, null));
				}
			}
		});
	}
	
	public AuthenticationType getPrivilege() {
		return AuthenticationType.PUBLIC;
	}
}
