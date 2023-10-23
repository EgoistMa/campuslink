package com.shiropure.campuslink.Form;

import io.swagger.annotations.ApiModelProperty;

public class LogoutForm extends Form{
    @ApiModelProperty(value = "token",required = true,example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5ZTJkOGJkYS00NDJjLTQ4M2UtYmIwMi01OWE3YTY2MTA4NGEiLCJpYXQiOjE2OTY4NzQzNjN9.-YbOJ2u6XDgsRWh9FW5Deh-qZPo4c3KmNeY4zZuApJA")
    private String token;
    public LogoutForm() {
    }
    public LogoutForm(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isFormValid() {
        return !areAnyFieldsNull(token);
    }
}
