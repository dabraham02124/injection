package org.sweatshop.injection;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class MyServer {
    private final Server jettyServer;

    public MyServer(int port, ServletContextHandler context) {
        jettyServer = new Server(port);
        jettyServer.setHandler(context);   
    }

    public void start() throws Exception {
        jettyServer.start();
        jettyServer.join();
    }

    public void destroy() {
        jettyServer.destroy();
    }

}
