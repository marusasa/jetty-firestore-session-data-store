package ssg.firestore_session_example;

import org.eclipse.jetty.server.session.NullSessionCache;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.server.session.SessionHandler;

import io.javalin.Javalin;

/**
 * Run this class to start Javalin server.
 */
public class ServerMain {

	/**
	 * Access http://localhost:8080 to show the test page.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		var app = Javalin.create(config -> {			
			config.jetty.modifyServletContextHandler(jettyContext ->{
				//Session Handling
				jettyContext.setSessionHandler(noSqlSessionHandler());
				jettyContext.getSessionHandler().getSessionCookieConfig().setPath("/");	//change this to a path that only needs session.
				jettyContext.getSessionHandler().getSessionCookieConfig().setMaxAge(31_536_000);	//365 days * 24h * 60min * 60 sec = 31,536,000
			});			
			
		});

		app.get("/", ctx -> {
			Long count = ctx.sessionAttribute("count");
			if(count == null) {
				count = 0L;
			}
			ctx.sessionAttribute("count", ++count);
			ctx.html(
					"""
					<html>
						<body>
							<div>Refresh to test session.</div>
							<div>Count: %s</div>
						</body>
					</html>
					""".formatted(count));			
		});
		
		app.start(8080);
	}
	
	/**
	 * Prepares the session handler.
	 * 
	 * It uses NullSessionCache, which doesn't cache anything in memory.
	 * 
	 * @return
	 */
	public static SessionHandler noSqlSessionHandler() {
	    SessionHandler sessionHandler = new SessionHandler();
	    SessionCache sessionCache = new NullSessionCache(sessionHandler);//no caching
	    sessionCache.setSessionDataStore(
	    	new JettyFirestoreDataStore()
	    );
	    sessionHandler.setSessionCache(sessionCache);
	    sessionHandler.setHttpOnly(true);
	    return sessionHandler;
	}

}
