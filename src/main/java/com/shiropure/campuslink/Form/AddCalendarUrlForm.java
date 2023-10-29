package com.shiropure.campuslink.Form;

public class AddCalendarUrlForm extends Form {
    String token;
    String url;

    public AddCalendarUrlForm() {
    }

    public AddCalendarUrlForm(String token, String url) {
        this.token = token;
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public boolean isFormValid() {
        return !areAnyFieldsNull(token, url);
    }
}
