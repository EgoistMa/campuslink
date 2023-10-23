package com.shiropure.campuslink.Form;

import io.swagger.annotations.ApiModelProperty;

public class LoginForm extends Form{
    @ApiModelProperty(value = "email",required = true,example = "tianyuma4869@gmail.com")
    private String email;
    @ApiModelProperty(value = "password",required = true,example = "123456")
    private String password;

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isFormValid() {
        if (areAnyFieldsNull(email, password)) {
            System.out.println("email or pass is null");
            return false;
        }

        if (!isEmailValid(email)) {
            System.out.println("email does not match regex");
            return false;
        }

        return true;
    }
}
