package com.shiropure.campuslink.Form;

import java.util.Map;

public class SaveUserAssetsForm extends Form {
    private String token;
    private String note;
    private Map<String,Boolean>[] tasks;

    public SaveUserAssetsForm() {
    }
    public SaveUserAssetsForm(String token, String note, Map<String, Boolean>[] tasks) {
        this.token = token;
        this.note = note;
        this.tasks = tasks;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Map<String, Boolean>[] getTasks() {
        return tasks;
    }

    public void setTodos(Map<String, Boolean>[] tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean isFormValid() {
        return !areAnyFieldsNull();
    }
}
