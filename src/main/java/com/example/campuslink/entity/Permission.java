package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "permissions")
public class Permission {
    private int userid;
    private int permissionlevel;
    private int groupid;

    public Permission(int userid, int permissionlevel, int groupid) {
        this.userid = userid;
        this.permissionlevel = permissionlevel;
        this.groupid = groupid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPermissionlevel() {
        return permissionlevel;
    }

    public void setPermissionlevel(int permissionlevel) {
        this.permissionlevel = permissionlevel;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "userid=" + userid +
                ", permissionlevel=" + permissionlevel +
                ", groupid=" + groupid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission that)) return false;
        return userid == that.userid && permissionlevel == that.permissionlevel && groupid == that.groupid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, permissionlevel, groupid);
    }
}
