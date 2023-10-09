package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "permissions")
public class Permission {
    private int userid;
    private int permissionLevel;
    private int groupId;

    public Permission(int userid, int permissionlevel, int groupid) {
        this.userid = userid;
        this.permissionLevel = permissionlevel;
        this.groupId = groupid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "userid=" + userid +
                ", permissionlevel=" + permissionLevel +
                ", groupid=" + groupId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission that)) return false;
        return userid == that.userid && permissionLevel == that.permissionLevel && groupId == that.groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, permissionLevel, groupId);
    }
}
