package nl.hypothermic.javacogs.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

import nl.hypothermic.javacogs.AuthenticationType;
import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.annotations.RequiredAuthenticationLevel;
import nl.hypothermic.javacogs.concurrency.ResponseCallback;
import nl.hypothermic.javacogs.concurrency.UncheckedCallback;
import nl.hypothermic.javacogs.entities.ArtistGroup;
import nl.hypothermic.javacogs.entities.ArtistMember;
import nl.hypothermic.javacogs.entities.Entity;
import nl.hypothermic.javacogs.entities.Label;
import nl.hypothermic.javacogs.entities.Release;
import nl.hypothermic.javacogs.entities.UserProfile;
import nl.hypothermic.javacogs.network.Response;

public class UserCollectionHandler implements IHandler {
	
	private Javacogs instance;
	
	public UserCollectionHandler(Javacogs instance) {
		this.instance = instance;
	}
	
	/**
	 * Get a user profile by username.
	 * 
	 * <pre>
	 * If authenticated as the requested user, the email key will be visible, and the num_list count will include the user’s private lists.
	 * If authenticated as the requested user or the user’s collection/wantlist is public, the num_collection / num_wantlist keys will be visible.
	 * </pre>
	 * 
	 * @param userName		The username of the user you want to request (ex. <code>rodneyfool</code>)
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return UserProfile object
	 */
	/*@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getProfileByUsername(final String userName, final ResponseCallback<UserProfile> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<UserProfile>(true,
						JSON.parseObject(instance.getHttpExecutor().get(Javacogs.apiUrlBase + "users/" + userName), 
								UserProfile.class)));
				} catch (IOException x) {
					x.printStackTrace();
					cb.onResult(new Response<UserProfile>(false, null));
				}
			}
		});
	}*/
}
