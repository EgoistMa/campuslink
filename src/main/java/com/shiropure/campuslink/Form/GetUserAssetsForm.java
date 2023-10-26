package com.shiropure.campuslink.Form;

public class GetUserAssetsForm extends Form {
    private String token;

    public GetUserAssetsForm() {
    }
    public GetUserAssetsForm(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean isFormValid() {
        return !areAnyFieldsNull(token);
    }
}
