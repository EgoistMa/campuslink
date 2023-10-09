package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
@Document(collection = "reminde")
public class Reminder {
    private int remindeid;
    private int eventid;
    private String status;
    private String remindertime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reminder reminder)) return false;
        return remindeid == reminder.remindeid && eventid == reminder.eventid && status.equals(reminder.status) && remindertime.equals(reminder.remindertime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remindeid, eventid, status, remindertime);
    }

    @Override
    public String toString() {
        return "Reminde{" +
                "remindeid=" + remindeid +
                ", eventid=" + eventid +
                ", status='" + status + '\'' +
                ", remindertime='" + remindertime + '\'' +
                '}';
    }

    public int getRemindeid() {
        return remindeid;
    }

    public void setRemindeid(int remindeid) {
        this.remindeid = remindeid;
    }

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemindertime() {
        return remindertime;
    }

    public void setRemindertime(String remindertime) {
        this.remindertime = remindertime;
    }

    public Reminder(int remindeid, int eventid, String status, String remindertime) {
        this.remindeid = remindeid;
        this.eventid = eventid;
        this.status = status;
        this.remindertime = remindertime;
    }
}
