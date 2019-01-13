package nl.hypothermic.javacogs.handlers;

import java.io.IOException;
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
import nl.hypothermic.javacogs.entities.Entity;
import nl.hypothermic.javacogs.entities.Label;
import nl.hypothermic.javacogs.entities.Master;
import nl.hypothermic.javacogs.entities.Release;
import nl.hypothermic.javacogs.entities.UserProfile;
import nl.hypothermic.javacogs.network.Response;

public class UserCollectionHandler implements IHandler {
	
	private Javacogs instance;
	
	public UserCollectionHandler(Javacogs instance) {
		this.instance = instance;
	}
	
	/**
	 * Retrieve a list of folders in a user’s collection.
	 * <pre>
	 * If you are not authenticated as the collection owner, only folder ID 0 (the “All” folder) 
	 * will be visible (if the requested user’s collection is public).
	 * If the collection has been made private by its owner, authentication as the collection owner is required. 
	 * <pre>
	 * 
	 * @param userName		The username of the user you want to request (ex. <code>rodneyfool</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return CollectionFolder array
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.MIXED)
	public void getFoldersByUser(final String userName, final UncheckedCallback<CollectionFolder[]> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<CollectionFolder[]>(true,
						JSON.parseArray(new JSONObject(instance.getHttpExecutor().get(Javacogs.apiUrlBase + "users/" + userName + "/collection/folders")).getJSONArray("folders").toString(), 
										 CollectionFolder.class).toArray(new CollectionFolder[] {})));
				} catch (IOException x) {
					cb.onResult(new Response<CollectionFolder[]>(false, null));
				}
			}
		});
	}
	
	/**
	 * Retrieve a folder from a user’s collection.
	 * <pre>
	 * If folderId is not 0, authentication as the collection owner is required.
	 * </pre>
	 * 
	 * @param userName		The username of the user you want to request (ex. <code>rodneyfool</code>)
	 * @param folderId		The ID of the folder to request (ex. <code>2</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return CollectionFolder object
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.MIXED)
	public void getFolderById(final String userName, final int folderId, final UncheckedCallback<CollectionFolder> cb) throws IOException {
		if (folderId != 0 && instance.getAuthenticationMethod() instanceof NoopAuthenticationMethod) {
			cb.onResult(new Response<CollectionFolder>(false, null));
		}
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<CollectionFolder>(true,
						JSON.parseObject(instance.getHttpExecutor().get(Javacogs.apiUrlBase + "users/" + userName + "/collection/folders/" + folderId), 
										 CollectionFolder.class)));
				} catch (IOException x) {
					cb.onResult(new Response<CollectionFolder>(false, null));
				}
			}
		});
	}
	
	/**
	 * Delete a folder from a user’s collection. A folder must be empty before it can be deleted.
	 * <pre>
	 * Authentication as the collection owner is required.
	 * </pre>
	 * 
	 * @param userName		The username of the user you want to modify (ex. <code>rodneyfool</code>)
	 * @param folderId		The ID of the folder to delete (ex. <code>3</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return Boolean if succeeded or not (will not be null).
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PROTECTED)
	public void deleteFolderById(final String userName, final int folderId, final UncheckedCallback<Boolean> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<Boolean>(true, 
							instance.getHttpExecutor().delete(Javacogs.apiUrlBase + "users/" + userName + "/collection/folders/" + folderId)));
				} catch (IOException x) {
					cb.onResult(new Response<Boolean>(false, false));
				}
			}
		});
	}
	
	/**
	 * Get list the of items in a folder in a user’s collection.
	 * 
	 * @param userName		The username of the folder's owner (ex. <code>rodneyfool</code>)
	 * @param folderId		The ID of the folder (ex. <code>0</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return CollectionRelease[], which can be resolved to Release[] if you need.
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getFolderContents(final String userName, final int folderId, final UncheckedCallback<CollectionRelease[]> cb) throws IOException {
		this.getFolderContents(userName, folderId, null, null, cb);
	}
	
	/**
	 * Get list the of items in a folder in a user’s collection.
	 * 
	 * @param userName		The username of the folder's owner (ex. <code>rodneyfool</code>)
	 * @param folderId		The ID of the folder (ex. <code>0</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return CollectionRelease[], which can be resolved to Release[] if you need.
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getFolderContents(final String userName, final int folderId, 
														 final SortOrder order, final UncheckedCallback<CollectionRelease[]> cb) throws IOException {
		this.getFolderContents(userName, folderId, null, order, cb);
	}
	
	/**
	 * Get list the of items in a folder in a user’s collection.
	 * 
	 * @param userName		The username of the folder's owner (ex. <code>rodneyfool</code>)
	 * @param folderId		The ID of the folder (ex. <code>0</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return CollectionRelease[], which can be resolved to Release[] if you need.
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getFolderContents(final String userName, final int folderId, 
														 final String sort, final UncheckedCallback<CollectionRelease[]> cb) throws IOException {
		this.getFolderContents(userName, folderId, sort, null, cb);
	}
	
	
	/**
	 * Get list the of items in a folder in a user’s collection.
	 * 
	 * @param userName		The username of the folder's owner (ex. <code>rodneyfool</code>)
	 * @param folderId		The ID of the folder (ex. <code>0</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return CollectionRelease[], which can be resolved to Release[] if you need.
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getFolderContents(final String userName, final int folderId, final String sort, 
								 						 final SortOrder order, final UncheckedCallback<CollectionRelease[]> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<CollectionRelease[]>(true, 
							(CollectionRelease[]) JSON.parseArray(new JSONObject(
									instance.getHttpExecutor().get(Javacogs.apiUrlBase + "users/" + userName + 
									 											 		 "/collection/folders/" + folderId + 
									 											 		 "/releases" + 
									 											 		 (sort  != null ? sort                   : "") + 
									 											 		 (order != null ? order.getAbbrevation() : "")))
																  .getJSONArray("releases").toString(), 
												  CollectionRelease.class).toArray(new CollectionRelease[] {})));
				} catch (IOException x) {
					cb.onResult(new Response<CollectionRelease[]>(false, null));
				}
			}
		});
	}
	
	/**
	 * Add a release to a folder in a user's collection
	 * 
	 * @param userName		The username of the folder's owner (ex. <code>rodneyfool</code>)
	 * @param folderId		The ID of the folder (ex. <code>0</code>)
	 * @param releaseId		The ID of the release (ex. <code>130076</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return Boolean if succeeded or not (will not be null).
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PROTECTED)
	public void addReleaseToFolder(final String userName, final int folderId, final int releaseId, 
																					final UncheckedCallback<Boolean> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<Boolean>(true, 
							instance.getHttpExecutor().post(Javacogs.apiUrlBase + "users/" + userName +
																				  "/collection/folders/" + folderId +
																				  "/releases/" + releaseId).charAt(0) != '0'));
				} catch (IOException x) {
					x.printStackTrace();
					cb.onResult(new Response<Boolean>(false, false));
				}
			}
		});
	}
}
