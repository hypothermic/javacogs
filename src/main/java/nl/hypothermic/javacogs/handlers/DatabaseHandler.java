package nl.hypothermic.javacogs.handlers;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import nl.hypothermic.javacogs.AuthenticationType;
import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.ResponseCallback;
import nl.hypothermic.javacogs.annotations.RequiredAuthenticationLevel;
import nl.hypothermic.javacogs.constants.Currency;
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
	public void getReleaseById(final int releaseId, final ResponseCallback cb) throws IOException {
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
	 * Get a release by id and specify preferred response currency.
	 * 
	 * @return Release object
	 */
	@RequiredAuthenticationLevel(authType = AuthenticationType.PUBLIC)
	public void getReleaseById(final int releaseId, final Currency currency, final ResponseCallback cb) throws IOException {
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
	public void getMasterById(final int masterId, final ResponseCallback cb) throws IOException {
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
	
	public AuthenticationType getPrivilege() {
		return AuthenticationType.PUBLIC;
	}
}
