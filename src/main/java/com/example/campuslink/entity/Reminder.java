package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
@Document(collection = "reminder")
public class Reminder {
    private int reminderId;
    private int eventId;
    private String status;
    private String reminderTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reminder reminder)) return false;
        return reminderId == reminder.reminderId && eventId == reminder.eventId && status.equals(reminder.status) && reminderTime.equals(reminder.reminderTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reminderId, eventId, status, reminderTime);
    }

    @Override
    public String toString() {
        return "Reminde{" +
                "remindeid=" + reminderId +
                ", eventid=" + eventId +
                ", status='" + status + '\'' +
                ", remindertime='" + reminderTime + '\'' +
                '}';
    }

    public int getRemindeid() {
        return reminderId;
    }

    public void setRemindeid(int remindeid) {
        this.reminderId = remindeid;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemindertime() {
        return reminderTime;
    }

    public void setRemindertime(String remindertime) {
        this.reminderTime = remindertime;
    }

    public Reminder(int remindeid, int eventid, String status, String remindertime) {
        this.reminderId = remindeid;
        this.eventId = eventid;
        this.status = status;
        this.reminderTime = remindertime;
    }
}
