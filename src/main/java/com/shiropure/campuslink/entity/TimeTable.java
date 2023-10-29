package com.shiropure.campuslink.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection = "timetable")
public class TimeTable {
    @Id
    UUID owner;
    String url;
    List<Event> events;

    public TimeTable() {
    }
    public TimeTable(UUID owner) {
        this.owner = owner;
        this.url = "";
        this.events = new ArrayList<Event>();
    }
    public TimeTable(UUID owner, String url) {
        this.owner = owner;
        this.url = url;
        this.events = new ArrayList<Event>();
    }

    public TimeTable(UUID owner, String url, List<Event> events) {
        this.owner = owner;
        this.url = url;
        this.events = events;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
