package ru.auroramusic.race.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = {"Classes",  "TieBreaker", "StartGroup", "Draw", "_RaceOrder", "DrawOK", "_LastShootingUpdateRound", "CourseData", "KORoundNr", "Remarks"})
public class Result {
    @JsonProperty("RaceId")
    private String raceId;
    @JsonProperty("Id")
    private int id;
    @JsonProperty("StartOrder")
    private int startOrder;
    @JsonProperty("HeatNr")
    private int heatNr;
    @JsonProperty("StartPosition")
    private int startPosition;
    @JsonProperty("LapsToGo")
    private int lapsToGo;
    @JsonProperty("Bib")
    private String bib;
    @JsonProperty("StartTime")
    private String startTime;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("dtStart")
    private String dtStart;
    @JsonProperty("dtFinish")
    private String dtFinish;
    @JsonProperty("dtFinish2")
    private String dtFinish2;
    @JsonProperty("RunTime")
    private int runTime;
    @JsonProperty("TotalTime")
    private int totalTime;
    @JsonProperty("Rnk")
    private String rnk;
    @JsonProperty("RnkOrder")
    private int rnkOrder;
    @JsonProperty("RacePoints")
    private float racePoints;
    @JsonProperty("TotalBehind")
    private String totalBehind;
    @JsonProperty("ValidResult")
    private boolean validResult;

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartOrder() {
        return startOrder;
    }

    public void setStartOrder(int startOrder) {
        this.startOrder = startOrder;
    }

    public int getHeatNr() {
        return heatNr;
    }

    public void setHeatNr(int heatNr) {
        this.heatNr = heatNr;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getLapsToGo() {
        return lapsToGo;
    }

    public void setLapsToGo(int lapsToGo) {
        this.lapsToGo = lapsToGo;
    }

    public String getBib() {
        return bib;
    }

    public void setBib(String bib) {
        this.bib = bib;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDtStart() {
        return dtStart;
    }

    public void setDtStart(String dtStart) {
        this.dtStart = dtStart;
    }

    public String getDtFinish() {
        return dtFinish;
    }

    public void setDtFinish(String dtFinish) {
        this.dtFinish = dtFinish;
    }

    public String getDtFinish2() {
        return dtFinish2;
    }

    public void setDtFinish2(String dtFinish2) {
        this.dtFinish2 = dtFinish2;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public String getRnk() {
        return rnk;
    }

    public void setRnk(String rnk) {
        this.rnk = rnk;
    }

    public int getRnkOrder() {
        return rnkOrder;
    }

    public void setRnkOrder(int rnkOrder) {
        this.rnkOrder = rnkOrder;
    }

    public float getRacePoints() {
        return racePoints;
    }

    public void setRacePoints(float racePoints) {
        this.racePoints = racePoints;
    }

    public String getTotalBehind() {
        return totalBehind;
    }

    public void setTotalBehind(String totalBehind) {
        this.totalBehind = totalBehind;
    }

    public boolean isValidResult() {
        return validResult;
    }

    public void setValidResult(boolean validResult) {
        this.validResult = validResult;
    }

    @Override
    public String toString() {
        return "Result{" +
                "raceId='" + raceId + '\'' +
                ", id='" + id + '\'' +
                ", startOrder=" + startOrder +
                ", heatNr=" + heatNr +
                ", startPosition=" + startPosition +
                ", lapsToGo=" + lapsToGo +
                ", bib='" + bib + '\'' +
                ", startTime='" + startTime + '\'' +
                ", status='" + status + '\'' +
                ", dtStart='" + dtStart + '\'' +
                ", dtFinish='" + dtFinish + '\'' +
                ", dtFinish2='" + dtFinish2 + '\'' +
                ", runTime=" + runTime +
                ", totalTime=" + totalTime +
                ", rnk='" + rnk + '\'' +
                ", rnkOrder=" + rnkOrder +
                ", racePoints=" + racePoints +
                ", totalBehind='" + totalBehind + '\'' +
                ", validResult=" + validResult +
                '}';
    }
}
