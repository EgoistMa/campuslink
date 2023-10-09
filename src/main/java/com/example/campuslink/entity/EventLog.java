package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "eventLog")
public class EventLog {
    private int logid;
    private int userid;
    private String action;
    private String timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventLog eventLog)) return false;
        return logid == eventLog.logid && userid == eventLog.userid && action.equals(eventLog.action) && timestamp.equals(eventLog.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logid, userid, action, timestamp);
    }

    @Override
    public String toString() {
        return "EventLog{" +
                "logid=" + logid +
                ", userid=" + userid +
                ", action='" + action + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public int getLogid() {
        return logid;
    }

    public void setLogid(int logid) {
        this.logid = logid;
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
        this.logid = logid;
        this.userid = userid;
        this.action = action;
        this.timestamp = timestamp;
    }
}
