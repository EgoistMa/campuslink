package com.shiropure.campuslink.Form;

public class CheckUserTypeForm extends Form{

    private String token;

    public CheckUserTypeForm() {
    }

    public CheckUserTypeForm(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public boolean isFormValid() {
        return !areAnyFieldsNull(token);
    }
}
