package ru.auroramusic.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.auroramusic.servlets.RPIServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        RPIServlet rpiServlet = new RPIServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(rpiServlet), "/dht");
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        server.join();
    }
}