package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "event")
public class Event {
    private int eventid;
    private int calendarid;
    private String DTSTART;
    private String DTEND;
    private String SUMMARY;
    private String location;
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
    }

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

    public Event(int eventid, int calendarid, String DTSTART, String DTEND, String SUMMARY, String location, String description, String backgroundcolor, String buildingcode, String tag) {
        this.eventid = eventid;
        this.calendarid = calendarid;
        this.DTSTART = DTSTART;
        this.DTEND = DTEND;
        this.SUMMARY = SUMMARY;
        this.location = location;
        this.description = description;
        this.backgroundcolor = backgroundcolor;
        this.buildingcode = buildingcode;
        this.tag = tag;
    }
}
