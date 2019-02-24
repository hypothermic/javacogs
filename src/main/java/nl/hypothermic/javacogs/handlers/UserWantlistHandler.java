package nl.hypothermic.javacogs.handlers;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

import nl.hypothermic.javacogs.AuthenticationType;
import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.annotations.RequiredAuthenticationLevel;
import nl.hypothermic.javacogs.authentication.NoopAuthenticationMethod;
import nl.hypothermic.javacogs.concurrency.ResponseCallback;
import nl.hypothermic.javacogs.concurrency.UncheckedCallback;
import nl.hypothermic.javacogs.constants.SortOrder;
import nl.hypothermic.javacogs.entities.ArtistGroup;
import nl.hypothermic.javacogs.entities.ArtistMember;
import nl.hypothermic.javacogs.entities.CollectionFolder;
import nl.hypothermic.javacogs.entities.CollectionRelease;
import nl.hypothermic.javacogs.entities.CollectionValue;
import nl.hypothermic.javacogs.entities.Entity;
import nl.hypothermic.javacogs.entities.Label;
import nl.hypothermic.javacogs.entities.Master;
import nl.hypothermic.javacogs.entities.Release;
import nl.hypothermic.javacogs.entities.UserProfile;
import nl.hypothermic.javacogs.network.Response;

public class UserWantlistHandler implements IHandler {
	
	private Javacogs instance;
	
	public UserWantlistHandler(Javacogs instance) {
		this.instance = instance;
	}
	
	/**
	 * Get list the of items in a user's wantlist<br>
	 * <br>
	 * A CollectionRelease with basic information about each release is provided, suitable for display in a list.<br>
	 * For detailed information, use resolveAsync() to fetch the corresponding Release with all data.<br>
	 * <br>
	 * If the wantlist has been made private by its owner, you must be authenticated as the owner to view it.<br>
	 * The notes field will be visible if you are authenticated as the wantlist owner.<br>
	 * 
	 * @param userName		The username of the wantlist's owner (ex. <code>rodneyfool</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return CollectionRelease[], which can be resolved to Release[] if you need.
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.MIXED)
	public void getWantlistByUsername(final String userName, final UncheckedCallback<CollectionRelease[]> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<CollectionRelease[]>(true, 
							(CollectionRelease[]) JSON.parseArray(new JSONObject(
									instance.getHttpExecutor().get(Javacogs.apiUrlBase + "users/" + userName + 
									 											 		 "/wants"))
																  .getJSONArray("wants").toString(), 
												  CollectionRelease.class).toArray(new CollectionRelease[] {})));
				} catch (IOException x) {
					cb.onResult(new Response<CollectionRelease[]>(false, null));
				}
			}
		});
	}
	
	/**
	 * Add a release to a user's wantlist<br>
	 * <br>
	 * It is required to authenticate as the wantlist owner.
	 * 
	 * @param userName		The username of the wantlist's owner (ex. <code>rodneyfool</code>)
	 * @param releaseId		The ID of the release (ex. <code>130076</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return Boolean if succeeded or not (will not be null).
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PROTECTED)
	public void addReleaseToWantlist(final String userName, final int releaseId, final UncheckedCallback<Boolean> cb) throws IOException {
		this.addReleaseToWantlist(userName, releaseId, null, (short) -1, cb);
	}
	
	/**
	 * Add a release to a user's wantlist<br>
	 * <br>
	 * It is required to authenticate as the wantlist owner.
	 * 
	 * @param userName		The username of the wantlist's owner (ex. <code>rodneyfool</code>)
	 * @param releaseId		The ID of the release (ex. <code>130076</code>)
	 * @param notes			User notes to associate with this release (ex. <code>My Notes</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return Boolean if succeeded or not (will not be null).
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PROTECTED)
	public void addReleaseToWantlist(final String userName, final int releaseId, final String notes,
																					final UncheckedCallback<Boolean> cb) throws IOException {
		this.addReleaseToWantlist(userName, releaseId, notes, (short) -1, cb);
	}
	
	/**
	 * Add a release to a user's wantlist<br>
	 * <br>
	 * It is required to authenticate as the wantlist owner.
	 * 
	 * @param userName		The username of the wantlist's owner (ex. <code>rodneyfool</code>)
	 * @param releaseId		The ID of the release (ex. <code>130076</code>)
	 * @param rating		User's rating of this release, from <code>0</code> (unrated, default) 
	 * 							to <code>5</code> (best). Use <code>-1</code> to leave this field empty.
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return Boolean if succeeded or not (will not be null).
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PROTECTED)
	public void addReleaseToWantlist(final String userName, final int releaseId, final short rating,
																					final UncheckedCallback<Boolean> cb) throws IOException {
		this.addReleaseToWantlist(userName, releaseId, null, rating, cb);
	}
	
	/**
	 * Add a release to a user's wantlist<br>
	 * <br>
	 * It is required to authenticate as the wantlist owner.
	 * 
	 * @param userName		The username of the wantlist's owner (ex. <code>rodneyfool</code>)
	 * @param releaseId		The ID of the release (ex. <code>130076</code>)
	 * @param notes			User notes to associate with this release (ex. <code>My Notes</code>)
	 * @param rating		User's rating of this release, from <code>0</code> (unrated, default) 
	 * 							to <code>5</code> (best). Use <code>-1</code> to leave this field empty.
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return Boolean if succeeded or not (will not be null).
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PROTECTED)
	public void addReleaseToWantlist(final String userName, final int releaseId, final String notes, final short rating,
																					final UncheckedCallback<Boolean> cb) throws IOException {
		if (rating < -1 || rating > 5) {
			throw new IllegalArgumentException("rating: Invalid value " + rating + " - The accepted values are between -1 and 5.");
		}
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<Boolean>(true,
							instance.getHttpExecutor().put(Javacogs.apiUrlBase + "users/" + userName +
																				 "/wants/" + releaseId +
																				 (notes  == null && rating == -1 ? ""                 : "?") +
																				 (notes  != null                 ? "notes="  + URLEncoder.encode(notes, "UTF-8")  :  "") +
																				 (notes  != null && rating != -1 ? "&"                :  "") +
																				 (rating != -1                   ? "rating=" + rating :  ""))));
				} catch (IOException x) {
					x.printStackTrace();
					cb.onResult(new Response<Boolean>(false, false));
				}
			}
		});
	}
	
	/**
	 * Delete a release from a user's wantlist<br>
	 * <br>
	 * It is required to authenticate as the wantlist owner.
	 * 
	 * @param userName		The username of the wantlist's owner (ex. <code>rodneyfool</code>)
	 * @param releaseId		The ID of the release (ex. <code>130076</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return Boolean if succeeded or not (will not be null).
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PROTECTED)
	public void deleteReleaseFromWantlist(final String userName, final int releaseId, final UncheckedCallback<Boolean> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<Boolean>(true,
							instance.getHttpExecutor().delete(Javacogs.apiUrlBase + "users/" + userName +
																					"/wants/" + releaseId)));
				} catch (IOException x) {
					x.printStackTrace();
					cb.onResult(new Response<Boolean>(false, false));
				}
			}
		});
	}
}
