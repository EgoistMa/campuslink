package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "comments")
public class Comments {
    private int eventid;
    private int commentid;
    private String auther;
    private String createdtime;
    private String content;

    @Override
    public String toString() {
        return "comments{" +
                "eventid=" + eventid +
                ", commentid=" + commentid +
                ", auther='" + auther + '\'' +
                ", createdtime='" + createdtime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comments comments)) return false;
        return eventid == comments.eventid && commentid == comments.commentid && auther.equals(comments.auther) && createdtime.equals(comments.createdtime) && content.equals(comments.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventid, commentid, auther, createdtime, content);
    }

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comments(int eventid, int commentid, String auther, String createdtime, String content) {
        this.eventid = eventid;
        this.commentid = commentid;
        this.auther = auther;
        this.createdtime = createdtime;
        this.content = content;
    }
}
