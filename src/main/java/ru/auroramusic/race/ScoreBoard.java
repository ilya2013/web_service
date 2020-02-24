package ru.auroramusic.race;

import ru.auroramusic.race.data.Result;
import ru.auroramusic.race.data.ResultRecord;
import ru.auroramusic.race.manager.ScoreBoardManager;

import java.util.*;

public class ScoreBoard {
    private final  ScoreBoardManager scoreBoardManager;
    private final String name;
    private final String raceId;
    private final Set<ResultRecord> resultRecords = new TreeSet<>();
    private final int DEFAULT_ROW_LIMIT = 5;
    private final String separator = ",";

    public ScoreBoard(String name, String raceId, ScoreBoardManager scoreBoardManager) {
        this.name = name;
        this.scoreBoardManager = scoreBoardManager;
        this.raceId = raceId;
        init();
    }

    private void init() {
        scoreBoardManager.getResultsList(this.raceId)
                .stream()
                //.filter(a -> a != null && a.getRaceId() != null && a.getRaceId().equals(raceId))
                .map(ResultRecord::new)
                .forEach(resultRecords::add);
    }

    public void addResult(Result result) {
        resultRecords.add(new ResultRecord(result));
    }
    public void showAll() {
        resultRecords
                .forEach((resultRecord) -> System.out.println(resultRecord
                        + ": "
                        + scoreBoardManager.getParticipant(resultRecord.getResult().getId())));
    }

    public String getScores(int rowLimit) {
       StringBuilder sb = new StringBuilder();
       int rowNumber = 0;
       int localRowLimit = rowLimit < 0 || rowLimit > 100 ? this.DEFAULT_ROW_LIMIT : rowLimit;
       for(int i = 1; i <= localRowLimit; i++) {
           sb.append(i).append("FamilyName").append(separator)
                   .append(i).append("GivenName").append(separator)
                   .append(i).append("NOC").append(separator)
                   .append(i).append("Region").append(separator)
                   .append(i).append("Club").append(separator)
                   .append(i).append("StartPosition").append(separator)
                   .append(i).append("StartOrder").append(separator)
                   .append(i).append("DtFinish").append(separator)
                   .append(i).append("TotalBehind").append(separator)
                   .append(i).append("RunTime").append(separator)
                   .append(i).append("TotalTime").append(i != rowLimit ? separator : System.lineSeparator());
       }
        for (ResultRecord resultRecord : resultRecords) {
            if (resultRecord != null && scoreBoardManager.getParticipant(resultRecord.getResult().getId()) != null
                && resultRecord.getResult().isValidResult()) {
                         sb.append(resultClean(scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getFamilyName())).append(separator)
                        .append(resultClean(scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getGivenName())).append(separator)
                        .append(resultClean(scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getNoc())).append(separator)
                        .append(resultClean(scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getRegione())).append(separator)
                        .append(resultClean(scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getClub())).append(separator)
                        .append(resultRecord.getResult().getStartPosition()).append(separator)
                        .append(resultRecord.getResult().getStartOrder()).append(separator)
                        .append(resultRecord.getResult().getDtFinish()).append(separator)
                        .append(resultRecord.getResult().getTotalBehind()).append(separator)
                        .append(msToTime(resultRecord.getResult().getRunTime())).append(separator)
                        .append(msToTime(resultRecord.getResult().getTotalTime()));
                rowNumber++;
                if ((rowNumber % rowLimit) == 0 || rowNumber == resultRecords.size()) {
                    sb.append(System.lineSeparator());
                } else {
                    sb.append(separator);
                }
            }
        }
        return sb.toString();
    }

    public String  msToTime(int mls) {
        StringBuilder sb = new StringBuilder();
        String timeSeparator = ":";
        int milliseconds = (int) (mls % 1000);
        int seconds = (int) (mls / 1000) % 60 ;
        int minutes = (int) ((mls / (1000*60)) % 60);
        int hours   = (int) ((mls / (1000*60*60)) % 24);
        sb.append(hours < 10 ? "0" + hours : hours ).append(timeSeparator)
                .append(minutes < 10 ? "0" + minutes : minutes).append(timeSeparator)
                .append(seconds < 10 ? "0" + seconds : seconds).append(".")
                .append(milliseconds < 10 ? "00" + milliseconds : milliseconds < 100 ? "0" + milliseconds : String.valueOf(milliseconds));
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getRaceId() {
        return raceId;
    }

    private String resultClean(String str) {
        str = str.replace(",", " ").replace(System.lineSeparator(), " ");
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreBoard that = (ScoreBoard) o;
        return Objects.equals(name, that.name) &&
                raceId.equals(that.raceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, raceId);
    }
}
