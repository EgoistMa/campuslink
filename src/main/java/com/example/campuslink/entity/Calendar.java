package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "calendar")
public class Calendar {
    private int calendarid;
    private int userid;
    private String calendarname;
    private String url;

    public Calendar(int calendarid, int userid, String calendarname, String url) {
        this.calendarid = calendarid;
        this.userid = userid;
        this.calendarname = calendarname;
        this.url = url;
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
