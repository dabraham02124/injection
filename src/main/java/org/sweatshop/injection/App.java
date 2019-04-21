package org.sweatshop.injection;

import javax.ws.rs.ApplicationPath;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class App extends ResourceConfig {

    private final MyServer jettyServer;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(App.class);

    public App(MyServer jettyServer) {
        this.jettyServer = jettyServer;
    }

    public static void main(String[] args) throws Exception {
        final ServletContextHandler context = getContext();
        final MyServer jettyServer = new MyServer(8080, context);
        prepJettyServlet(context);

        log.info("about to start up");
        new App(jettyServer).runServer();
    }

    private void register() {
        log.info("start register");

        register(EntryPoint.class);
        register(StringHolder.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                log.info("start bind singleton");
                bind(new StringHolder("injected string")).to(StringHolder.class);
                log.info("end bind singleton");
            }
        });

        log.info("end register");
    }

    private void runServer() throws Exception {
        register();
        try {
            jettyServer.start();
        } finally {
            jettyServer.destroy();
        }
    }

    /**
     * This is a magic method which sets things under the covers.  It doesn't have to return 
     * anything, but it does because I'm perverse that way.
     * @param context
     * @return
     */
    private static ServletHolder prepJettyServlet(ServletContextHandler context) {
        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                       "jersey.config.server.provider.packages",
                       EntryPoint.class.getPackage().getName());
        return jerseyServlet;
    }

    private static ServletContextHandler getContext() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        return context;
    }

}