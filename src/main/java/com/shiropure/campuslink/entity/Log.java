package com.shiropure.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "log")
public class Log {
    private LogLevel LogLevel;
    private UUID uuid;
    private String action;
    private String additionalInfo;
    private Date date;
    private String IP;

    public Log(LogLevel logLevel,String action,Date date, String IP) {
        LogLevel = logLevel;
        this.action = action;
        this.date = date;
        this.IP = IP;
    }

    public Log(LogLevel logLevel, UUID uuid, String action, String additionalInfo,Date date, String IP) {
        LogLevel = logLevel;
        this.uuid = uuid;
        this.action = action;
        this.additionalInfo = additionalInfo;
        this.date = date;
        this.IP = IP;
    }
    public com.shiropure.campuslink.entity.LogLevel getLogLevel() {
        return LogLevel;
    }

    public void setLogLevel(com.shiropure.campuslink.entity.LogLevel logLevel) {
        LogLevel = logLevel;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Log{" +
                "LogLevel=" + LogLevel +
                ", uuid=" + uuid +
                ", action='" + action + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", IP='" + IP + '\'' +
                '}';
    }
}
