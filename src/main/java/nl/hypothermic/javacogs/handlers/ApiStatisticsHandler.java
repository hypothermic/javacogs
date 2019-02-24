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

public class ApiStatisticsHandler implements IHandler {
	
	private Javacogs instance;
	
	public ApiStatisticsHandler(Javacogs instance) {
		this.instance = instance;
	}
	
	/**
	 * Get the total amount of labels in the Discogs database.
	 * 
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return Long object with the amount.
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getLabelsCount(final UncheckedCallback<Long> cb) throws IOException {
		this.getStatistic("labels", cb);
	}
	
	/**
	 * Get the total amount of releases in the Discogs database.
	 * 
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return Long object with the amount.
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getReleasesCount(final UncheckedCallback<Long> cb) throws IOException {
		this.getStatistic("releases", cb);
	}
	
	/**
	 * Get the total amount of artists in the Discogs database.
	 * 
	 * @param cb			The callback which will be called at result time
	 * 
	 * @return Long object with the amount.
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getArtistsCount(final UncheckedCallback<Long> cb) throws IOException {
		this.getStatistic("artists", cb);
	}
	
	/**
	 * <i>Internal use only</i><br>
	 * <br>
	 * Use one of the front-ends here:
	 * - Labels: {@link nl.hypothermic.javacogs.handlers.ApiStatisticsHandler#getLabelCount(UncheckedCallback)}
	 */
	private void getStatistic(final String jsonProperty, final UncheckedCallback<Long> cb) throws IOException {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<Long>(true, 
									new JSONObject(instance.getHttpExecutor().get(Javacogs.apiUrlBase))
										.getJSONObject("statistics")
										.getLong(jsonProperty)));
				} catch (IOException x) {
					cb.onResult(new Response<Long>(false, null));
				}
			}
		});
	}
}
