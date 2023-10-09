package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "groupLog")
public class GroupLog {
    private int logId;
    private int userid;
    private int groupId;
    private String action;
    private String timestamp;

    public GroupLog(int logid, int userid, int groupid, String action, String timestamp) {
        this.logId = logid;
        this.userid = userid;
        this.groupId = groupid;
        this.action = action;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "GroupLog{" +
                "logid=" + logId +
                ", userid=" + userid +
                ", groupid=" + groupId +
                ", action='" + action + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupLog groupLog)) return false;
        return logId == groupLog.logId && userid == groupLog.userid && groupId == groupLog.groupId && action.equals(groupLog.action) && timestamp.equals(groupLog.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, userid, groupId, action, timestamp);
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
