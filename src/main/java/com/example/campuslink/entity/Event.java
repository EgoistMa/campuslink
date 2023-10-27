package com.example.campuslink.entity;

import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document(collection = "event")
public class Event {
    private  JSONObject eventJSON;
    private int eventid;
    private int calendarid;
    private String DTSTART;
    private String DTEND;
    private String SUMMARY;
    private String location;
    private String POI;
    private String description;
    private String backgroundcolor;
    private String buildingcode;
    private String tag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return eventid == event.eventid && calendarid == event.calendarid && DTSTART.equals(event.DTSTART) && DTEND.equals(event.DTEND) && Objects.equals(SUMMARY, event.SUMMARY) && Objects.equals(location, event.location) && Objects.equals(description, event.description) && Objects.equals(backgroundcolor, event.backgroundcolor) && Objects.equals(buildingcode, event.buildingcode) && Objects.equals(tag, event.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventid, calendarid, DTSTART, DTEND, SUMMARY, location, description, backgroundcolor, buildingcode, tag);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventid=" + eventid +
                ", calendarid=" + calendarid +
                ", DTSTART='" + DTSTART + '\'' +
                ", DTEND='" + DTEND + '\'' +
                ", SUMMARY='" + SUMMARY + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", backgroundcolor='" + backgroundcolor + '\'' +
                ", buildingcode='" + buildingcode + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public int getCalendarid() {
        return calendarid;
    }

    public void setCalendarid(int calendarid) {
        this.calendarid = calendarid;
    }

    public String getDTSTART() {
        return DTSTART;
    }

    public void setDTSTART(String DTSTART) {
        this.DTSTART = DTSTART;
    }

    public String getDTEND() {
        return DTEND;
    }

    public void setDTEND(String DTEND) {
        this.DTEND = DTEND;
    }

    public String getSUMMARY() {
        return SUMMARY;
    }

    public void setSUMMARY(String SUMMARY) {
        this.SUMMARY = SUMMARY;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {

        this.location = location;
        this.POI = extractPoi(location);
    }

    public String getPOI(){return  POI;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackgroundcolor() {
        return backgroundcolor;
    }

    public void setBackgroundcolor(String backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }

    public String getBuildingcode() {
        return buildingcode;
    }

    public void setBuildingcode(String buildingcode) {
        this.buildingcode = buildingcode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public JSONObject getEventJSON(){return eventJSON;};

    public Event(int eventid, int calendarid, String DTSTART, String DTEND, String SUMMARY, String location, String description, String backgroundcolor, String buildingcode, String tag) {
        this.eventid = eventid;
        this.calendarid = calendarid;
        this.DTSTART = DTSTART;
        this.DTEND = DTEND;
        this.SUMMARY = SUMMARY;
        this.location = location;
        this.POI = extractPoi(location);
        this.description = description;
        this.backgroundcolor = backgroundcolor;
        this.buildingcode = buildingcode;
        this.tag = tag;
    }

    public Event(JSONObject eventJson){
        try{
            this.setCalendarid(this.calendarid);
            this.setDTSTART(eventJson.getString("DTSTART"));
            this.setDTEND(eventJson.getString("DTEND"));
            this.setSUMMARY(eventJson.getString("SUMMARY"));
            this.setLocation(eventJson.getString("LOCATION"));
            this.setDescription(eventJson.getString("DESCRIPTION"));
            eventJson.put("POI", this.POI);
            this.eventJSON = eventJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm 'on' d MMMM yyyy");
    public String getHumanReadableDTSTART(){
        LocalDateTime startDateTime = LocalDateTime.parse(DTSTART, inputFormatter);
        return startDateTime.format(outputFormatter);
    }

    public String getHumanReadableDTEND(){
        LocalDateTime endDateTime = LocalDateTime.parse(DTEND, inputFormatter);
        return endDateTime.format(outputFormatter);
    }

    public String extractPoi(String input) {
        String[] parts = input.split("\\.");
        return parts[parts.length - 1];
    }


    public  Event(){

    }
}
