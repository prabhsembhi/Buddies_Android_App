
package com.example.finalproject;

public class FriendlyMessage {

    private String id;
    private String msg;
    private String name;
    private String photoUrl;
    private String imageUrl;
    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public FriendlyMessage(String group) {
        this.group = group;
    }

    public FriendlyMessage() {
    }

    public FriendlyMessage(String msg, String name) {
        this.msg = msg;
        this.name = name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getText() {
        return msg;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
