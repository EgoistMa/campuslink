package com.shiropure.campuslink.Form;

import java.util.Date;

public class UpdateSettingsForm extends Form {

    String token;

    Boolean reminder;

    Date time;

    public UpdateSettingsForm(String token) {
        this.token = token;
    }

    public UpdateSettingsForm(String token, Boolean reminder, Date time) {
        this.token = token;
        this.reminder = reminder;
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getReminder() {
        return reminder;
    }

    public void setReminder(Boolean reminder) {
        this.reminder = reminder;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean isFormValid() {
        return !areAnyFieldsNull(token,reminder,time);
    }
}
