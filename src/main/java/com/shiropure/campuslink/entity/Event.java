package com.shiropure.campuslink.entity;

import com.shiropure.campuslink.services.EmailService;
import net.fortuna.ical4j.model.parameter.Email;
import org.springframework.beans.factory.annotation.Autowired;

public class Event {
    String LOCATION;
    String UID;
    String DTSTAMP;
    String SUMMARY;
    String DTSTART;
    String DESCRIPTION;
    String DTEND;


    public Event() {
    }

    public Event(String LOCATION, String UID, String DTSTAMP, String SUMMARY, String DTSTART, String DESCRIPTION, String DTEND) {
        this.LOCATION = LOCATION;
        this.UID = UID;
        this.DTSTAMP = DTSTAMP;
        this.SUMMARY = SUMMARY;
        this.DTSTART = DTSTART;
        this.DESCRIPTION = DESCRIPTION;
        this.DTEND = DTEND;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getDTSTAMP() {
        return DTSTAMP;
    }

    public void setDTSTAMP(String DTSTAMP) {
        this.DTSTAMP = DTSTAMP;
    }

    public String getSUMMARY() {
        return SUMMARY;
    }

    public void setSUMMARY(String SUMMARY) {
        this.SUMMARY = SUMMARY;
    }

    public String getDTSTART() {
        return DTSTART;
    }

    public void setDTSTART(String DTSTART) {
        this.DTSTART = DTSTART;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getDTEND() {
        return DTEND;
    }

    public void setDTEND(String DTEND) {
        this.DTEND = DTEND;
    }
    public String getPoi(){
        String desc = this.DESCRIPTION;
        String[] parts = desc.split("\\.");
        return parts[parts.length - 1];
    }

    public void sendMessage(String email){
        EmailService.sendNoticeEmail(email, "class notification","you will have a class soon");
    }
}
