package com.shiropure.campuslink.Form;

public class IsTokenValidForm extends Form{
    private String token;

    public IsTokenValidForm() {
    }

    public IsTokenValidForm(String token) {
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
