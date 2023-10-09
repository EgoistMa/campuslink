package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "group")
public class Group {

    private int groupid;
    private String groupcreatetime;
    private String description;
    private String GroupName;

    public Group(int groupid, String groupcreatetime, String description, String groupName) {
        this.groupid = groupid;
        this.groupcreatetime = groupcreatetime;
        this.description = description;
        GroupName = groupName;
    }

    @Override
    public String toString() {
        return "group{" +
                "groupid=" + groupid +
                ", groupcreatetime='" + groupcreatetime + '\'' +
                ", description='" + description + '\'' +
                ", GroupName='" + GroupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group group)) return false;
        return groupid == group.groupid && groupcreatetime.equals(group.groupcreatetime) && Objects.equals(description, group.description) && GroupName.equals(group.GroupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupid, groupcreatetime, description, GroupName);
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getGroupcreatetime() {
        return groupcreatetime;
    }

    public void setGroupcreatetime(String groupcreatetime) {
        this.groupcreatetime = groupcreatetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }
}
