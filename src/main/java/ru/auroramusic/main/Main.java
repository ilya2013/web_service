package ru.auroramusic.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.auroramusic.config.ConfigManager;
import ru.auroramusic.servlets.RPIServlet;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        RPIServlet rpiServlet = new RPIServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(rpiServlet), "/dht");
        String port = new ConfigManager().load("./config.properties").get("server.port");
        Server server = new Server(Integer.valueOf(port));
        server.setHandler(context);
        server.start();
        server.join();
    }
}
