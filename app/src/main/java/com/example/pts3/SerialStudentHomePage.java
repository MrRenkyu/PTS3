package com.example.pts3;

import java.io.Serializable;

public class SerialStudentHomePage implements Serializable {

    private GroupTP groupTP;

    private String lastName;
    private String firstName;
    private String number;
    private String birthDate;
    private String bac;
    private String originSchool;

    private Photo photo;


    public SerialStudentHomePage(GroupTP groupTP, String lastName, String firstName, String number, String birthDate, String bac, String originSchool, Photo photo) {
        this.groupTP = groupTP;
        this.lastName = lastName;
        this.firstName = firstName;
        this.number = number;
        this.birthDate = birthDate;
        this.bac = bac;
        this.originSchool = originSchool;
        this.photo = photo;
    }


    public SerialStudentHomePage() {
    }





    public GroupTP getGroupTP() {
        return groupTP;
    }

    public void setGroupTP(GroupTP groupTP) {
        this.groupTP = groupTP;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBac() {
        return bac;
    }

    public void setBac(String bac) {
        this.bac = bac;
    }

    public String getOriginSchool() {
        return originSchool;
    }

    public void setOriginSchool(String originSchool) {
        this.originSchool = originSchool;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
