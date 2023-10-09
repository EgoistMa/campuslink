package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "event")
public class Event {
    private int eventId;
    private int calendarId;
    private String DTSTART;
    private String DTEND;
    private String SUMMARY;
    private String location;
    private String description;
    private String backgroundColor;
    private String buildingCode;
    private String tag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return eventId == event.eventId && calendarId == event.calendarId && DTSTART.equals(event.DTSTART) && DTEND.equals(event.DTEND) && Objects.equals(SUMMARY, event.SUMMARY) && Objects.equals(location, event.location) && Objects.equals(description, event.description) && Objects.equals(backgroundColor, event.backgroundColor) && Objects.equals(buildingCode, event.buildingCode) && Objects.equals(tag, event.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, calendarId, DTSTART, DTEND, SUMMARY, location, description, backgroundColor, buildingCode, tag);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventid=" + eventId +
                ", calendarid=" + calendarId +
                ", DTSTART='" + DTSTART + '\'' +
                ", DTEND='" + DTEND + '\'' +
                ", SUMMARY='" + SUMMARY + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", backgroundcolor='" + backgroundColor + '\'' +
                ", buildingcode='" + buildingCode + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
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
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Event(int eventid, int calendarid, String DTSTART, String DTEND, String SUMMARY, String location, String description, String backgroundcolor, String buildingcode, String tag) {
        this.eventId = eventid;
        this.calendarId = calendarid;
        this.DTSTART = DTSTART;
        this.DTEND = DTEND;
        this.SUMMARY = SUMMARY;
        this.location = location;
        this.description = description;
        this.backgroundColor = backgroundcolor;
        this.buildingCode = buildingcode;
        this.tag = tag;
    }
}
