package com.example.finalproject;

public class Group_User {
    String UserEmail;
    String GroupName;
    String GroupUserId;
    public Group_User(String UserEmail, String GroupName) {
        this.UserEmail = UserEmail;
        this.GroupName = GroupName;
    }
    public Group_User(String UserEmail, String GroupName, String GroupUserId) {
        this.UserEmail = UserEmail;
        this.GroupName = GroupName;
        this.GroupUserId = GroupUserId;
    }

    public String getGroupUserId() {
        return GroupUserId;
    }

    public void setGroupUserId(String groupUserId) {
        GroupUserId = groupUserId;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }
}
