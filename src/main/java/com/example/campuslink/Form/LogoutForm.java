package com.example.campuslink.Form;

import io.swagger.annotations.ApiModelProperty;

import static com.example.campuslink.utils.InputCheck.isNull;

public class LogoutForm {
    @ApiModelProperty(value = "token",required = true,example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5ZTJkOGJkYS00NDJjLTQ4M2UtYmIwMi01OWE3YTY2MTA4NGEiLCJpYXQiOjE2OTY4NzQzNjN9.-YbOJ2u6XDgsRWh9FW5Deh-qZPo4c3KmNeY4zZuApJA")
    private String token;

    public LogoutForm(String token) {
        this.token = token;
    }

    public LogoutForm() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean formNotLegal() {
        return (isNull(token));
    }
}
