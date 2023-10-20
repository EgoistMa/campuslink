package com.example.campuslink.entity;

import com.example.campuslink.services.IcsToJsonConverter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

@Document(collection = "calendar")
public class Calendar {
    private int calendarid;
    private int userid;
    private String calendarname;
    private String url;

    private List<Event> events;

    public Calendar(int calendarid, int userid, String calendarname, String url) {
        this.calendarid = calendarid;
        this.userid = userid;
        this.calendarname = calendarname;
        this.url = url;
        this.events = new ArrayList<>();
        generateCalendar();
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "calendarid=" + calendarid +
                ", userid=" + userid +
                ", calendarname='" + calendarname + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Calendar calendar)) return false;
        return calendarid == calendar.calendarid && userid == calendar.userid && calendarname.equals(calendar.calendarname) && url.equals(calendar.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendarid, userid, calendarname, url);
    }

    public void generateCalendar(){
        JSONObject JSONCalender = IcsToJsonConverter.convertIcsToJson(this.url);
        loadFromJson(JSONCalender);
    }

    public void loadFromJson(JSONObject JSONCalender) {
        try {
        JSONArray eventsArray = JSONCalender.getJSONArray("events");

        for (int i = 1; i < eventsArray.length(); i++) {
            JSONObject eventJson = eventsArray.getJSONObject(i);
            Event event = new Event();
            event.setCalendarid(this.calendarid);
            event.setDTSTART(eventJson.getString("DTSTART"));
            event.setDTEND(eventJson.getString("DTEND"));
            event.setSUMMARY(eventJson.getString("SUMMARY"));
            event.setLocation(eventJson.getString("LOCATION"));
            event.setDescription(eventJson.getString("DESCRIPTION"));
            this.events.add(event);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCalendarid() {
        return calendarid;
    }

    public void setCalendarid(int calendarid) {
        this.calendarid = calendarid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCalendarname() {
        return calendarname;
    }

    public void setCalendarname(String calendarname) {
        this.calendarname = calendarname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
