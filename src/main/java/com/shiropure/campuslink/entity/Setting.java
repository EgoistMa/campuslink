package com.shiropure.campuslink.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection="Settings")
public class Setting {
    @Id
    UUID owner;

    Boolean enabledEmail;

    Date time;

    public Setting() {
    }

    public Setting(UUID owner, Boolean enabledEmail, Date time) {
        this.owner = owner;
        this.enabledEmail = enabledEmail;
        this.time = time;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public Boolean getEnabledEmail() {
        return enabledEmail;
    }

    public void setEnabledEmail(Boolean enabledEmail) {
        this.enabledEmail = enabledEmail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "owner=" + owner +
                ", enabledEmail=" + enabledEmail +
                ", time=" + time +
                '}';
    }
}
