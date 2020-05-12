package com.example.finalproject;

public class User {


    String UserEmail, FullName, Preference, About;

    public User(String UserEmail,String FullName,String Preference, String About) {
        this.UserEmail = UserEmail;
        this.FullName = FullName;
        this.Preference = Preference;
        this.About = About;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public void setPreference(String preference) {
        Preference = preference;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getUserEmail() {
        return UserEmail;
    }



    public String getPreference() {
        return Preference;
    }

    public String getAbout() {
        return About;
    }
}
