package ru.auroramusic.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.auroramusic.config.ConfigManager;
import ru.auroramusic.config.Configs;
import ru.auroramusic.race.manager.ScoreBoardManager;
import ru.auroramusic.servlets.RPIServlet;
import ru.auroramusic.servlets.RaceResultsServlet;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
       ConfigManager configManager = new ConfigManager();
       Configs configs = configManager.load("./config.properties");
        RPIServlet rpiServlet = new RPIServlet();
        RaceResultsServlet raceResultsServlet = new RaceResultsServlet(new ScoreBoardManager(configs));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(rpiServlet), "/file");
        context.addServlet(new ServletHolder(raceResultsServlet), "/result");
        String port = configs.get("server.port");
        Server server = new Server(Integer.valueOf(port));
        server.setHandler(context);
        server.start();
        server.join();
    }
}
