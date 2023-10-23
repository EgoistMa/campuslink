package com.shiropure.campuslink.Form;

public class ResetPassForm extends Form{
    private String forgetPassToken;

    private String password;

    public ResetPassForm() {
    }

    public ResetPassForm(String forgetPassToken, String password) {
        this.forgetPassToken = forgetPassToken;
        this.password = password;
    }

    public String getForgetPassToken() {
        return forgetPassToken;
    }

    public void setForgetPassToken(String forgetPassToken) {
        this.forgetPassToken = forgetPassToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFormValid() {
        return !areAnyFieldsNull(forgetPassToken, password);
    }
}
