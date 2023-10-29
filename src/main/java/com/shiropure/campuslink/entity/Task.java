package com.shiropure.campuslink.entity;

public class Task {
    private String taskname;
    private boolean status;
    private Task() {

    }

    public Task(String taskname, boolean status) {
        this.taskname = taskname;
        this.status = status;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
