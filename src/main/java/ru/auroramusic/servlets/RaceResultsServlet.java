package ru.auroramusic.servlets;

import org.apache.log4j.Logger;
import ru.auroramusic.race.manager.DataManager;
import ru.auroramusic.race.manager.ScoreBoardManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RaceResultsServlet extends HttpServlet {
    private final ScoreBoardManager scoreBoardManager;
    final static Logger logger = Logger.getLogger(RaceResultsServlet.class);

    public RaceResultsServlet(ScoreBoardManager scoreBoardManager) {
        super();
        this.scoreBoardManager = scoreBoardManager;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        logger.info(req.toString());
        String raceId = req.getParameter("raceId");
        String type = req.getParameter("type");
        int rowLimit = 0;
        int timePrecision = 0;
        try {
            rowLimit = Integer.parseInt(req.getParameter("rowLimit"));
            timePrecision = req.getParameter("timePrecision") != null ? Integer.parseInt(req.getParameter("timePrecision")) : 2;
        } catch (Exception e) {
            logger.error(e);
        }
        if (raceId == null || raceId.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            resp.getWriter().print(rowLimit != 0 ? scoreBoardManager.getScores(raceId, rowLimit, type, timePrecision) : scoreBoardManager.getScores(raceId));
            resp.setStatus(HttpServletResponse.SC_OK);
            logger.info(resp.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
