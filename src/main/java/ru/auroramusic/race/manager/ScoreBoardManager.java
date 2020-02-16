package ru.auroramusic.race.manager;

import org.apache.log4j.Logger;
import ru.auroramusic.config.Configs;
import ru.auroramusic.race.ScoreBoard;
import ru.auroramusic.race.data.Participant;
import ru.auroramusic.race.data.Result;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoardManager {
    private final Map<Integer, Participant> participants = new ConcurrentHashMap<>();
    private final Map<String, List<Result>> raceResults = new ConcurrentHashMap<>();
    private final Map<String, ScoreBoard> scoreBoards = new ConcurrentHashMap<>();
    private final DataManager dataManager;
    final static Logger logger = Logger.getLogger(ScoreBoardManager.class);
    private final Configs configs;


    public ScoreBoardManager(Configs configs) {
        this.dataManager = new DataManager(this, configs);
        this.configs = configs;
        new Thread(dataManager).start();
    }
    public String getScores(String raceId) {
        int rowLimit;
        rowLimit = Integer.parseInt(configs.get("server.race.ski.scoreBoard.rowLimit"));
        return getScores(raceId, rowLimit);
    }
    public String getScores(String raceId, int rowLimit) {
        String rslt;
        scoreBoards.putIfAbsent(raceId, new ScoreBoard(raceId, raceId, this));
        rslt = scoreBoards.get(raceId).getScores(rowLimit);
        return rslt;
    }

    public Participant getParticipant(int id) {
        return participants.get(id);
    }

    public List<Result> getResultsList(String raceId) {
        raceResults.putIfAbsent(raceId, new ArrayList<>());
        return raceResults.get(raceId);
    }

    public void addParticipant(Participant participant) {
        participants.putIfAbsent(participant.getId(), participant);
    }

    public void addResult(Result newResult) {
        List<Result> raceResultList;
        Optional<Result> oldResult;
        try {
            raceResultList = raceResults.get(newResult.getRaceId());
            if (raceResultList == null) {
                logger.info("Initialization of results...");
                raceResults.put(newResult.getRaceId(), new ArrayList<>());
                raceResults.get(newResult.getRaceId()).add(newResult);
            } else {
                oldResult = raceResultList.stream().filter(a -> a.getId() == newResult.getId()).findFirst();
                if (oldResult.isPresent()) {
//                    logger.info(String.format("Refresh of result: %s %s New result: %s"
//                            ,  oldResult.toString()
//                            , System.lineSeparator()
//                            , newResult.toString()));
                    oldResult.ifPresent(a -> a.setDtFinish(newResult.getDtFinish()));
                    oldResult.ifPresent(a -> a.setDtFinish2(newResult.getDtFinish2()));
                    oldResult.ifPresent(a -> a.setBib(newResult.getBib()));
                    oldResult.ifPresent(a -> a.setStartOrder(newResult.getStartOrder()));
                    oldResult.ifPresent(a -> a.setTotalBehind(newResult.getTotalBehind()));
                    oldResult.ifPresent(a -> a.setTotalTime(newResult.getTotalTime()));
                    oldResult.ifPresent(a -> a.setRunTime(newResult.getRunTime()));
                } else {
                    logger.info(String.format("Add new result: %s"
                            ,  newResult.toString()));
                    raceResults.get(newResult.getRaceId()).add(newResult);
                    if (scoreBoards.containsKey(newResult.getRaceId())) {
                        scoreBoards.get(newResult.getRaceId()).addResult(newResult);
                    }
                }
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
