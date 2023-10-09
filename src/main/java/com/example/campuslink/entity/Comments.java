package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "comments")
public class Comments {
    private int eventId;
    private int commentId;
    private String author;
    private String createdTime;
    private String content;

    @Override
    public String toString() {
        return "comments{" +
                "eventid=" + eventId +
                ", commentid=" + commentId +
                ", auther='" + author + '\'' +
                ", createdtime='" + createdTime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comments comments)) return false;
        return eventId == comments.eventId && commentId == comments.commentId && author.equals(comments.author) && createdTime.equals(comments.createdTime) && content.equals(comments.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, commentId, author, createdTime, content);
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comments(int eventid, int commentid, String auther, String createdtime, String content) {
        this.eventId = eventid;
        this.commentId = commentid;
        this.author = auther;
        this.createdTime = createdtime;
        this.content = content;
    }
}
