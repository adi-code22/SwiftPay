package com.stackroute.com.UserService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
//@Table(name='user_master')
public class User {
    @Id
    @Column(name="email_id",length=30)
    private String emailId;
    @Column(name="password",length=30)
    private String password;
    @Column(name="user_name",length=30)
    private String nameOfTheUser;
    @Column(name="mobile_number",length=15)
    private String mobileNumber;
    @Column(name="location", length=30)
    private String location;
    @Column(name="pan_number",length=30)
    private String panNumber;
    @Column(name="profile_password",length=30)
    private String profilePassword;

    //Getters and Setters
    public String getEmailId(){ return emailId; }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNameOfTheUser() {
        return nameOfTheUser;
    }
    public void setNameOfTheUser(String nameOfTheUser) {
        this.nameOfTheUser = nameOfTheUser;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getPanNumber() {
        return panNumber;
    }
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getProfilePassword() {
        return profilePassword;
    }
    public void setProfilePassword(String profilePassword) {
        this.profilePassword = profilePassword;
    }


    @Override
    public String toString() {
        return "User{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", nameOfTheUser='" + nameOfTheUser + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", location='" + location + '\'' +
                ", panNumber='" + panNumber + '\'' +
                ", profilePassword='" + profilePassword + '\'' +
                '}';
    }
}
