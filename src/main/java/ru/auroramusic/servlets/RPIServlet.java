package ru.auroramusic.servlets;

import ru.auroramusic.config.ConfigManager;
import ru.auroramusic.config.Configs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class RPIServlet extends HttpServlet {
    File file = new File(ConfigManager.get("server.dht.filePath"));

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("row");
        try (BufferedReader read = new BufferedReader(new FileReader(this.file))) {
            if (value != null && value.toUpperCase().equals("ALL")) {
                read.lines().forEach(response.getWriter()::println);
            } else if ((value != null && value.toUpperCase().equals("FIRST"))) {
                read.lines().findFirst().ifPresent(response.getWriter()::println);
            } else {
                List<String> lines = read.lines().collect(Collectors.toList());
                if (lines.size() != 0) {
                    response.getWriter().println(lines.get(lines.size() - 1));
                }
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            e.printStackTrace();
        }
        //response.getWriter().println(line);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String dir = request.getParameter("dir");
        response.setContentType("text/html;charset=utf-8");

        if (dir == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            file = new File(dir);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
