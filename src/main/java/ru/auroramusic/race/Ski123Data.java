package ru.auroramusic.race;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import ru.auroramusic.race.data.Participant;
import ru.auroramusic.race.data.Result;
import ru.auroramusic.race.data.Schedule;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(value = { "Classes", "CompOfficials", "Schedule", "CourseData", "StartGroups", "Events", "Clubs", "FISHeader", "CompetitionStatistics"})
public class Ski123Data {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Participants")
    private List<Participant> participants = new ArrayList<>();
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Results")
    private List<Result> results = new ArrayList<>();
    @JsonProperty("Schedule")
    private List<Schedule> schedule = new ArrayList<>();


    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Result> getResults() {
        return results;
    }
    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
