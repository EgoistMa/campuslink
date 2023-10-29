package com.shiropure.campuslink.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Document(collection = "userAssets")
public class UserAssets {
    @Id
    UUID user;
    String note;
    List<Task> todos;

    public UserAssets() {
    }
    public UserAssets(UUID user, String note,List<Task> todos) {
        this.user = user;
        this.note = note;
        this.todos = todos;
    }
    public UserAssets(UUID user) {
        List<Task> task  = new ArrayList<Task>();
        task.add(new Task("have",false));
        task.add(new Task("fun",false));
        this.user = user;
        this.note = "you can write your notes here ...";
        this.todos = task;
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

    public List<Task> getTodos() {
        return todos;
    }

    public void setTodos(List<Task> todos) {
        this.todos = todos;
    }
}
