package nl.hypothermic.javacogs;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import nl.hypothermic.javacogs.authentication.NoopAuthenticationMethod;
import nl.hypothermic.javacogs.handlers.DatabaseHandler;
import nl.hypothermic.javacogs.handlers.Handler;
import nl.hypothermic.javacogs.handlers.IHandler;
import nl.hypothermic.javacogs.network.HttpExecutor;
import nl.hypothermic.javacogs.network.RateLimiter;

/**
 * The main entry point. Acts as a registry system of Javacogs. All parts of the the API can be accessed starting from this class.
 */
public class Javacogs {

	private static final Javacogs instance = new Javacogs();

	/**
	 * Get the global instance.<br>
	 * <br>
	 * It is not neccesary to use this instance, you can also create a new one by using
	 * <pre>
	 * <code>
	 * 	Javacogs instance = new Javacogs();
	 * </code>
	 * </pre>
	 */
	public static Javacogs getInstance() {
		return instance;
	}
	
	public static final String apiUrlBase = "https://api.discogs.com/";
	
	public static final int VERSION_MAJOR = 1;
	
	public static final int VERSION_MINOR = 0;
	
	public static final int VERSION_PATCH = 16;
	
	private final AtomicInteger counter = new AtomicInteger();
	public final ExecutorService threadpool = Executors.newCachedThreadPool(new ThreadFactory() {
		public Thread newThread(Runnable r) {
			return new Thread(r, "Javacogs-" + counter.incrementAndGet());
		}
	});
	
	private final ArrayList<IHandler> handlerList = new ArrayList<IHandler>();
	
	private AuthenticationMethod authMethod = new NoopAuthenticationMethod();
	private RateLimiter rateLimiter = new RateLimiter();
	
	private HttpExecutor httpExecutor = new HttpExecutor(this);

    public Javacogs() {
        handlerList.add(new DatabaseHandler(this));
    }

	public AuthenticationMethod getAuthenticationMethod() {
		return this.authMethod;
	}

	public void setAuthenticationMethod(AuthenticationMethod authMethod) {
		this.authMethod = authMethod;
	}
	
	public RateLimiter getRateLimiter() {
		return this.rateLimiter;
	}
	
	public HttpExecutor getHttpExecutor() {
		return httpExecutor;
	}

	/**
	 * Get the instance of specified handler.<br>
	 * Example for the database handler:
	 * <pre>
	 * <code>
	 * DatabaseHandler handler = Javacogs.getInstance().getHandler(Handler.DATABASE);
	 * </code>
	 * </pre>
	 */
	public <T extends IHandler> T getHandler(Handler<T> handler) {
		for (IHandler h : handlerList) {
			if (h.getClass() == handler.getHandler()) {
				return handler.getHandler().cast(h);
			}
		}
		return null;
	}
}
