package com.example.finalproject;

public class Event {


    private String EventName, EventDesc, EventLocation, DateOfEvent,GroupName,TimeOfEvent;

    public Event( String GroupName, String EventName, String EventLocation, String EventDesc, String DateOfEvent,
                  String TimeOfEvent) {

        this.GroupName = GroupName;
        this.EventName = EventName;;
        this.EventDesc = EventDesc;
        this.EventLocation = EventLocation;
        this.DateOfEvent = DateOfEvent;
        this.TimeOfEvent = TimeOfEvent;
    }

    public String getTimeOfEvent() {
        return TimeOfEvent;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public void setTimeOfEvent(String timeOfEvent) {
        TimeOfEvent = timeOfEvent;
    }

    public void setGroupId(String groupName) {
        GroupName= groupName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public void setEventDesc(String eventDesc) {
        EventDesc = eventDesc;
    }

    public void setEventLocation(String eventLocation) {
        EventLocation = eventLocation;
    }

    public void setDateOfEvent(String dateOfEvent) {
        DateOfEvent = dateOfEvent;
    }



    public String getGroupName() {
        return GroupName;
    }

    public String getEventName() {
        return EventName;
    }

    public String getEventDesc() {
        return EventDesc;
    }

    public String getEventLocation() {
        return EventLocation;
    }

    public String getDateOfEvent() {
        return DateOfEvent;
    }
}