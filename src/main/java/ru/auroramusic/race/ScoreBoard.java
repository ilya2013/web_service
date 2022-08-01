package ru.auroramusic.race;

import ru.auroramusic.race.data.Result;
import ru.auroramusic.race.data.ResultRecord;
import ru.auroramusic.race.manager.ScoreBoardManager;

import java.util.*;
import java.util.stream.Collectors;

public class ScoreBoard {
    private final  ScoreBoardManager scoreBoardManager;
    private final String name;
    private final String raceId;
    private final Set<ResultRecord> resultRecords = new TreeSet<>();
    private final static int DEFAULT_ROW_LIMIT = 5;
    private final String separator = ",";
    public static final String  START_ORDER = "START";
    public static final String  FINISH_ORDER = "FINISH";
    public final String  disId;
    private final Comparator<ResultRecord> startOrderComparator = Comparator.comparingInt(o -> o.getResult().getStartOrder());
    private final Comparator<ResultRecord> rnkOrderComparator = Comparator.comparingInt(o -> o.getResult().getRnkOrder());

    public ScoreBoard(String name, String raceId, ScoreBoardManager scoreBoardManager) {
        this.name = name;
        this.scoreBoardManager = scoreBoardManager;
        this.raceId = raceId;
        this.disId = scoreBoardManager.getSchedule(raceId).getDisId();
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

    public String getScores(int rowLimit, String type, int timePrecision) {
        int localRowLimit = rowLimit < 0 || rowLimit > 100 ? this.DEFAULT_ROW_LIMIT : rowLimit;
        Comparator<ResultRecord> comparator = null;
        if (type == null) {
            comparator = null;
        } else if (START_ORDER.equals(type.toUpperCase())) {
            comparator = startOrderComparator;
        } else if (FINISH_ORDER.equals(type.toUpperCase())){
            comparator = rnkOrderComparator;
        }
        return toCSV(localRowLimit, comparator, timePrecision);
    }


    public String getScores(int rowLimit) {
     return getScores(rowLimit, null, 2);
    }
    
    private String toCSV(int rowLimit, Comparator<ResultRecord> comparator, int timePrecision) {
        int rowNumber = 0;
        StringBuilder sb = new StringBuilder();
        List<ResultRecord> sortedResults;
        if (comparator != null) {
            sortedResults = resultRecords.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            sortedResults = new ArrayList<>(resultRecords);
        }
        for(int i = 1; i <= rowLimit; i++) {
            sb.append(i).append("FamilyName").append(separator)
                    .append(i).append("GivenName").append(separator)
                    .append(i).append("FormatName").append(separator)
                    .append(i).append("NOC").append(separator)
                    .append(i).append("Region").append(separator)
                    .append(i).append("Club").append(separator)
                    .append(i).append("StartPosition").append(separator)
                    .append(i).append("StartOrder").append(separator)
                    .append(i).append("DtFinish").append(separator)
                    .append(i).append("TotalBehind").append(separator)
                    .append(i).append("RunTime").append(separator)
                    .append(i).append("RnkOrder").append(separator)
                    .append(i).append("TotalTime").append(i != rowLimit ? separator : System.lineSeparator());
        }
        for (ResultRecord resultRecord : sortedResults) {
            if (resultRecord != null && scoreBoardManager.getParticipant(resultRecord.getResult().getId()) != null
                    && resultRecord.getResult().isValidResult()) {
                sb.append(nameFormat(resultClean(scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getFamilyName()))).append(separator)
                        .append(nameFormat(resultClean(scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getGivenName()))).append(separator)
                        .append(resultClean(nameFormat(scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getFamilyName()
                                                        , scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getGivenName()))).append(separator)
                        .append(resultClean(scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getNoc())).append(separator)
                        .append(resultClean(scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getRegione())).append(separator)
                        .append(resultClean(scoreBoardManager.getParticipant(resultRecord.getResult().getId()).getClub())).append(separator)
                        .append(resultRecord.getResult().getStartPosition()).append(separator)
                        .append(resultRecord.getResult().getStartOrder()).append(separator)
                        .append(resultRecord.getResult().getDtFinish()).append(separator)
                        .append(resultRecord.getResult().getTotalBehind()).append(separator)
                        .append(msToTime(resultRecord.getResult().getRunTime(), timePrecision)).append(separator)
                        .append(resultRecord.getResult().getRnkOrder()).append(separator)
                        .append(msToTime(resultRecord.getResult().getTotalTime(), timePrecision));
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

    public String  msToTime(int mls, int precision) {
        StringBuilder sb = new StringBuilder();
        String timeSeparator = ":";
        int milliseconds = (int) (mls % 1000);
        int seconds = (int) (mls / 1000) % 60 ;
        int minutes = (int) ((mls / (1000*60)) % 60);
        int hours   = (int) ((mls / (1000*60*60)) % 24);
        sb.append(hours < 10 ? "0" + hours : hours ).append(timeSeparator)
                .append(minutes < 10 ? "0" + minutes : minutes).append(timeSeparator)
                .append(seconds < 10 ? "0" + seconds : seconds).append(".")
                .append((milliseconds < 10 ? "00" + milliseconds
                        : milliseconds < 100 ? "0" + milliseconds
                                             : String.valueOf(milliseconds)
                        ).substring(0, precision));
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

    private String nameFormat(String str) {
        String result = null;
        if (str != null) {
            result = str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
        }
        return result;
    }

    private String nameFormat(String str, String str2) {
        StringBuilder result = new StringBuilder();
        if (str != null) {
            result.append(nameFormat(str));
        }
        if (str2 != null) {
            result.append(" ").append(str2.substring(0, 1).toUpperCase()).append(".");
        }
        return result.toString();
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
