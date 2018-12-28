package nl.hypothermic.javacogs.entities;

import java.io.IOException;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

import nl.hypothermic.javacogs.Javacogs;
import nl.hypothermic.javacogs.concurrency.ResponseCallback;
import nl.hypothermic.javacogs.network.Response;

/**
 * Entities extending <code>ResolvableEntity<code> may not have all fields filled.<br>
 * <br>
 * You can get a filled entity from calling {@link nl.hypothermic.javacogs.entities.ResolvableEntity#resolve()} 
 * 										 or {@link nl.hypothermic.javacogs.entities.ResolvableEntity#resolveAsync(ResponseCallback)} (the latter is recommended).
 */
public abstract class ResolvableEntity<X extends ResolvableEntity> extends Entity {
	
	/**
	 * The type of the superclass that we must pass on to the JSON parser to reconstruct it.<br>
	 */
	private final Class<X> referenceType;
	
	public ResolvableEntity(Class<X> referenceType) {
		this.referenceType = referenceType;
	}
	
	/**
	 * Instead of accessing the superclass field <code>resource_url</code> with reflection, it's easier and more flexible to just implement a method.
	 */
	protected abstract String getResolveUrl();
	
	/**
	 * Return a resolved copy of this entity.<br>
	 * <br>
	 * Note: the <code>resourceUrl</code> must have been set. Every result from Discogs will have this.
	 * 
	 * @deprecated You should not use this as it is not executed asynchronously and blocks the current thread until the result has been retrieved.
	 * 			   Use {@link nl.hypothermic.javacogs.entities.ResolvableEntity#resolveAsync(ResponseCallback)} instead.
	 */
	@Deprecated
	public X resolve() throws IOException {
		return this.resolve(Javacogs.getInstance());
	}
	
	/**
	 * Return a resolved copy of this entity.<br>
	 * <br>
	 * Note: the <code>resourceUrl</code> must have been set. Every result from Discogs will have this.
	 * 
	 * @deprecated You should not use this as it is not executed asynchronously and blocks the current thread until the result has been retrieved.
	 * 			   Use {@link nl.hypothermic.javacogs.entities.ResolvableEntity#resolveAsync(ResponseCallback)} instead.
	 */
	@Deprecated
	public X resolve(final Javacogs instance) throws IOException {
		return JSON.parseObject(instance.getHttpExecutor().get(getResolveUrl()),
								    referenceType);
	}
	
	/**
	 * Return a resolved copy of this entity.<br>
	 * <br>
	 * Note: the <code>resourceUrl</code> must have been set. Every result from Discogs will have this.
	 * 
	 * @param cb			The callback which will be called at result time
	 */
	public void resolveAsync(final ResponseCallback<X> cb) {
		this.resolveAsync(Javacogs.getInstance(), cb);
	}
	
	/**
	 * Return a resolved copy of this entity.<br>
	 * <br>
	 * Note: the <code>resourceUrl</code> must have been set. Every result from Discogs will have this.
	 * 
	 * @param cb			The callback which will be called at result time
	 * @param instance		The Javacogs instance which will be used to make threads and execute HTTP requests
	 */
	public void resolveAsync(final Javacogs instance, final ResponseCallback<X> cb) {
		instance.threadpool.execute(new Runnable() {
			public void run() {
				try {
					cb.onResult(new Response<X>(true,
						JSON.parseObject(instance.getHttpExecutor().get(getResolveUrl()),
										 referenceType)));
				} catch (IOException x) {
					cb.onResult(new Response<X>(false, null));
				}
			}
		});
	}
}
