package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "groupLog")
public class GroupLog {
    private int logid;
    private int userid;
    private int groupid;
    private String action;
    private String timestamp;

    public GroupLog(int logid, int userid, int groupid, String action, String timestamp) {
        this.logid = logid;
        this.userid = userid;
        this.groupid = groupid;
        this.action = action;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "GroupLog{" +
                "logid=" + logid +
                ", userid=" + userid +
                ", groupid=" + groupid +
                ", action='" + action + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupLog groupLog)) return false;
        return logid == groupLog.logid && userid == groupLog.userid && groupid == groupLog.groupid && action.equals(groupLog.action) && timestamp.equals(groupLog.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logid, userid, groupid, action, timestamp);
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

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
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
}
