package nl.hypothermic.javacogs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import nl.hypothermic.javacogs.authentication.NoopAuthenticationMethod;
import nl.hypothermic.javacogs.handlers.ApiStatisticsHandler;
import nl.hypothermic.javacogs.handlers.DatabaseHandler;
import nl.hypothermic.javacogs.handlers.Handler;
import nl.hypothermic.javacogs.handlers.IHandler;
import nl.hypothermic.javacogs.handlers.UserCollectionHandler;
import nl.hypothermic.javacogs.handlers.UserIdentityHandler;
import nl.hypothermic.javacogs.handlers.UserWantlistHandler;
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
	 * It is not neccesary to use this instance, you may also create a new one by using
	 * <pre>
	 * <code>
	 * Javacogs instance = new Javacogs();
	 * </code>
	 * </pre>
	 */
	public static Javacogs getInstance() {
		return instance;
	}
	
	/**
	 * The major version.<br>
	 * X.0.00
	 */
	public static final int VERSION_MAJOR = 1;
	
	/**
	 * The minor version.<br>
	 * 0.X.00
	 */
	public static final int VERSION_MINOR = 0;
	/**
	 * The patch version.<br>
	 * 0.0.XX
	 */
	public static final int VERSION_PATCH = 17;
	
	/**
	 * The full path to the RESTful interface, including the protocol.
	 */
	public static final String apiUrlBase = "https://api.discogs.com/";
	
	/**
	 * The User-Agent to identify the application.
	 */
	public static final String USER_AGENT = "Javacogs/" + VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_PATCH + " +https://github.com/hypothermic/javacogs";
	
	private final AtomicInteger counter = new AtomicInteger();
	
	/**
	 * The main thread pool which the handlers will use.
	 */
	public final ExecutorService threadpool = Executors.newCachedThreadPool(new ThreadFactory() {
		public Thread newThread(Runnable r) {
			return new Thread(r, "Javacogs-" + counter.incrementAndGet());
		}
	});
	
	/**
	 * List of included handlers. Cannot be modified after class construction.
	 */
	private final List<IHandler> handlerList;
	
	private AuthenticationMethod authMethod = new NoopAuthenticationMethod();
	private RateLimiter rateLimiter = new RateLimiter();
	
	private HttpExecutor httpExecutor = new HttpExecutor(this);

	/**
	 * Construct a Javacogs instance with all included handlers.
	 */
    public Javacogs() {
    	handlerList = new ArrayList<IHandler>();
        handlerList.add(new DatabaseHandler(this));
        handlerList.add(new UserIdentityHandler(this));
        handlerList.add(new UserCollectionHandler(this));
        handlerList.add(new UserWantlistHandler(this));
        handlerList.add(new ApiStatisticsHandler(this));
    }
    
    /**
     * Construct a Javacogs instance with custom handlers.<br>
     * <br>
     * <b>Warning</b>: use this constructor at own risk. 
     * 				   Calling getHandler() for non-included handlers will cause NullPointerExceptions.
     */
    public Javacogs(List<IHandler> handlers) {
    	this.handlerList = handlers;
    }

	public AuthenticationMethod getAuthenticationMethod() {
		return this.authMethod;
	}

	public void setAuthenticationMethod(AuthenticationMethod authMethod) {
		if (!(authMethod instanceof NoopAuthenticationMethod)) {
			this.rateLimiter.setRateLimit(60);
		}
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
