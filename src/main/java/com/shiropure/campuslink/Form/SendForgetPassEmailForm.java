package com.shiropure.campuslink.Form;

public class SendForgetPassEmailForm extends Form{
    private String email;

    public SendForgetPassEmailForm() {
    }

    public SendForgetPassEmailForm(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public SendForgetPassEmailForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isFormValid() {
        return !areAnyFieldsNull(email);
    }

}
