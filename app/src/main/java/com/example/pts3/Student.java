package com.example.pts3;

public class Student {

    private GroupTP groupTP;


    private String lastName;
    private String firstName;
    private String number;
    private String birthDate;
    private String bac;
    private String originSchool;

    public Student(GroupTP groupTP, String lastName, String firstName,
                   String number, String birthDate, String bac, String originSchool){

        this.groupTP = groupTP;
        this.lastName = lastName;
        this.firstName = firstName;
        this.number = number;
        this.birthDate = birthDate;
        this.bac = bac;
        this.originSchool = originSchool;
    }

    public GroupTP getGroupTP() {
        return groupTP;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getNumber() {
        return number;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getBac() {
        return bac;
    }

    public String getOriginSchool() {
        return originSchool;
    }
}
