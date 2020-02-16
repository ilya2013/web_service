package ru.auroramusic.race.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Participant {
    @JsonProperty("Id")
    private int id;
    @JsonProperty("FamilyName")
    private String familyName;
    @JsonProperty("GivenName")
    private String givenName;
    @JsonProperty("FamilyName2")
    private String familyName2;
    @JsonProperty("GivenName2")
    private String givenName2;
    @JsonProperty("ClassId")
    private String classId;
    @JsonProperty("NOC")
    private String noc;
    @JsonProperty("Regione")
    private String regione;
    @JsonProperty("Club")
    private String club;
    @JsonProperty("Ranking")
    private String ranking;
    @JsonProperty("Birthdate")
    private String birthdate;
    @JsonProperty("IsTeam")
    private boolean isTeam;
    @JsonProperty("FISSprintPoints")
    private String fISSprintPoints;

    public String getnATSprintPoints() {
        return nATSprintPoints;
    }

    public void setnATSprintPoints(String nATSprintPoints) {
        this.nATSprintPoints = nATSprintPoints;
    }

    @JsonProperty("NATSprintPoints")
    private String nATSprintPoints;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName2() {
        return familyName2;
    }

    public void setFamilyName2(String familyName2) {
        this.familyName2 = familyName2;
    }

    public String getGivenName2() {
        return givenName2;
    }

    public void setGivenName2(String givenName2) {
        this.givenName2 = givenName2;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getNoc() {
        return noc;
    }

    public void setNoc(String noc) {
        this.noc = noc;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isTeam() {
        return isTeam;
    }

    public void setTeam(boolean team) {
        isTeam = team;
    }

    public String getfISSprintPoints() {
        return fISSprintPoints;
    }

    public void setfISSprintPoints(String fISSprintPoints) {
        this.fISSprintPoints = fISSprintPoints;
    }



    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", familyName='" + familyName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", familyName2='" + familyName2 + '\'' +
                ", givenName2='" + givenName2 + '\'' +
                ", classId='" + classId + '\'' +
                ", noc='" + noc + '\'' +
                ", regione='" + regione + '\'' +
                ", club='" + club + '\'' +
                ", ranking='" + ranking + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", isTeam=" + isTeam +
                ", fISSprintPoints='" + fISSprintPoints + '\'' +
                ", nATSprintPoints='" + nATSprintPoints + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
