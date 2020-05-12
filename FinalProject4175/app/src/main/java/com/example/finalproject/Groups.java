package com.example.finalproject;

public class Groups {



    String GroupName, GroupDesc, GroupLocation, Interest, EventName, UserEmail;

    public Groups(String GroupName, String GroupDesc,String GroupLocation, String Interest, String EventName, String UserEmail ) {

        this.GroupName = GroupName;
        this.GroupDesc = GroupDesc;
        this.GroupLocation = GroupLocation;
        this.Interest = Interest;
        this.EventName = EventName;
        this.UserEmail = UserEmail;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public void setGroupDesc(String groupDesc) {
        GroupDesc = groupDesc;
    }

    public void setGroupLocation(String groupLocation) {
        GroupLocation = groupLocation;
    }

    public void setInterest(String interest) {
        Interest = interest;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }



    public String getGroupName() {
        return GroupName;
    }

    public String getGroupDesc() {
        return GroupDesc;
    }

    public String getGroupLocation() {
        return GroupLocation;
    }

    public String getInterest() {
        return Interest;
    }

    public String getEventName() {
        return EventName;
    }
}
