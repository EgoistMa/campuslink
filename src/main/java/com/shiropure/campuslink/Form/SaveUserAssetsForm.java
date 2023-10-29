package com.shiropure.campuslink.Form;

import com.shiropure.campuslink.entity.Task;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SaveUserAssetsForm extends Form {
    private String token;
    private String note;
    private List<Task> tasks;

    public SaveUserAssetsForm() {
    }
    public SaveUserAssetsForm(String token, String note, List<Task> tasks) {
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean isFormValid() {
        return !areAnyFieldsNull();
    }

    @Override
    public String toString() {
        return "SaveUserAssetsForm{" +
                "token='" + token + '\'' +
                ", note='" + note + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
