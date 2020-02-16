package ru.auroramusic.servlets;

import ru.auroramusic.config.ConfigManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;


public class RPIServlet extends HttpServlet {
    File mainFile = new File(ConfigManager.get("server.file.main"));
    ConcurrentMap<String, File> files = new ConcurrentHashMap<>();

    public RPIServlet() {
        super();
        parseFiles(ConfigManager.get("server.file.extra"));
    }

    private void parseFiles(String str) {
        if (str != null && !str.isEmpty()) {
            for (String keyValue : str.split(";")) {
                    String[] aliasValue = keyValue.split("=");
                    if (aliasValue.length == 2) {
                    files.put(aliasValue[0], new File(aliasValue[1]));
                }
            }
        }
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String rowParam = request.getParameter("row");
        String fileAlias = request.getParameter("file");
        File file = fileAlias == null ? mainFile : files.get(fileAlias);
        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            if (rowParam != null && rowParam.toUpperCase().equals("ALL")) {
                read.lines().forEach(response.getWriter()::println);
            } else if ((rowParam != null && rowParam.toUpperCase().equals("FIRST"))) {
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
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String file = request.getParameter("file");
        String alias = request.getParameter("alias");
        response.setContentType("text/html;charset=utf-8");

        if (alias == null || file == null) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else if (!new File(file).exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            files.put(alias, new File(file));
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
