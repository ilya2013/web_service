package ru.auroramusic.race.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Schedule {
    @JsonProperty("RaceId")
    private String raceId;
    @JsonProperty("StartTime")
    private String startTime;
    @JsonProperty("ClassId")
    private String classId;
    @JsonProperty("DisId")
    private String disId;
    @JsonProperty("FirstBib")
    private int firstBib;
    @JsonProperty("JuryNr")
    private int juryNr;
    @JsonProperty("CourseNr")
    private int courseNr;
    @JsonProperty("RaceStatusId")
    private String raceStatusId;
    @JsonProperty("CustomTitle")
    private String customTitle;
    @JsonProperty("IsLive")
    private boolean isLive;
    @JsonProperty("TransponderOffset")
    private int transponderOffset;
    @JsonProperty("Hidden")
    private boolean hidden;


    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getDisId() {
        return disId;
    }

    public void setDisId(String disId) {
        this.disId = disId;
    }

    public int getFirstBib() {
        return firstBib;
    }

    public void setFirstBib(int firstBib) {
        this.firstBib = firstBib;
    }

    public int getJuryNr() {
        return juryNr;
    }

    public void setJuryNr(int juryNr) {
        this.juryNr = juryNr;
    }

    public int getCourseNr() {
        return courseNr;
    }

    public void setCourseNr(int courseNr) {
        this.courseNr = courseNr;
    }

    public String getRaceStatusId() {
        return raceStatusId;
    }

    public void setRaceStatusId(String raceStatusId) {
        this.raceStatusId = raceStatusId;
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public void setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getTransponderOffset() {
        return transponderOffset;
    }

    public void setTransponderOffset(int transponderOffset) {
        this.transponderOffset = transponderOffset;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
