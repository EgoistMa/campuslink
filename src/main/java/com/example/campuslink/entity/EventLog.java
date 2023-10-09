package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "eventLog")
public class EventLog {
    private int logId;
    private int userid;
    private String action;
    private String timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventLog eventLog)) return false;
        return logId == eventLog.logId && userid == eventLog.userid && action.equals(eventLog.action) && timestamp.equals(eventLog.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, userid, action, timestamp);
    }

    @Override
    public String toString() {
        return "EventLog{" +
                "logid=" + logId +
                ", userid=" + userid +
                ", action='" + action + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public EventLog(int logid, int userid, String action, String timestamp) {
        this.logId = logid;
        this.userid = userid;
        this.action = action;
        this.timestamp = timestamp;
    }
}
