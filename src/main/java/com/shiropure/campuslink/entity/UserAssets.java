package com.shiropure.campuslink.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@Document(collection = "userAssets")
public class UserAssets {
    @Id
    UUID user;
    String note;
    Map<String, Boolean>[] todos;

    public UserAssets() {
    }
    public UserAssets(UUID user, String note, Map<String, Boolean>[] todos) {
        this.user = user;
        this.note = note;
        this.todos = todos;
    }
    public UserAssets(UUID user) {
        this.user = user;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Map<String, Boolean>[] getTodos() {
        return todos;
    }

    public void setTodos(Map<String, Boolean>[] todos) {
        this.todos = todos;
    }
}
