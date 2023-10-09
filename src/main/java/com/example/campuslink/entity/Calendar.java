package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "calendar")
public class Calendar {
    private int calendarId;
    private int userid;
    private String calendarName;
    private String url;

    public Calendar(int calendarid, int userid, String calendarname, String url) {
        this.calendarId = calendarid;
        this.userid = userid;
        this.calendarName = calendarname;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "calendarid=" + calendarId +
                ", userid=" + userid +
                ", calendarname='" + calendarName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Calendar calendar)) return false;
        return calendarId == calendar.calendarId && userid == calendar.userid && calendarName.equals(calendar.calendarName) && url.equals(calendar.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendarId, userid, calendarName, url);
    }

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
