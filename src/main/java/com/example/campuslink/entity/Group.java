package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "group")
public class Group {

    private int groupId;
    private String groupCreateTime;
    private String description;
    private String GroupName;

    public Group(int groupid, String groupcreatetime, String description, String groupName) {
        this.groupId = groupid;
        this.groupCreateTime = groupcreatetime;
        this.description = description;
        GroupName = groupName;
    }

    @Override
    public String toString() {
        return "group{" +
                "groupid=" + groupId +
                ", groupcreatetime='" + groupCreateTime + '\'' +
                ", description='" + description + '\'' +
                ", GroupName='" + GroupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group group)) return false;
        return groupId == group.groupId && groupCreateTime.equals(group.groupCreateTime) && Objects.equals(description, group.description) && GroupName.equals(group.GroupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupCreateTime, description, GroupName);
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupCreateTime() {
        return groupCreateTime;
    }

    public void setGroupCreateTime(String groupCreateTime) {
        this.groupCreateTime = groupCreateTime;
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
