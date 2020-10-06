package com.example.pts3;

import java.io.IOException;

public class Student {

    private GroupTP groupTP;


    private String lastName;
    private String firstName;
    private String number;
    private String birthDate;
    private String bac;
    private String originSchool;

    private Photo photo;

    private ItemPersonAdapter.ItemPersonViewHolder itemPersonViewHolder;

    public Student(GroupTP groupTP, String lastName, String firstName,
                   String number, String birthDate, String bac, String originSchool){

        this.groupTP = groupTP;
        this.lastName = lastName;
        this.firstName = firstName;
        this.number = number;
        this.birthDate = birthDate;
        this.bac = bac;
        this.originSchool = originSchool;
        try {
            this.photo = new Photo(number.substring(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public Photo getPhoto() {
        return photo;
    }


    public ItemPersonAdapter.ItemPersonViewHolder getItemPersonViewHolder() {
        return itemPersonViewHolder;
    }

    public void setItemPersonViewHolder(ItemPersonAdapter.ItemPersonViewHolder itemPersonViewHolder) {
        this.itemPersonViewHolder = itemPersonViewHolder;
    }
}
