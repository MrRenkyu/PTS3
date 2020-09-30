package com.example.pts3;

import java.util.ArrayList;

public class GroupTD {
    private String name;
    private Promo promo;

    private ArrayList<GroupTP> GroupsTP = new ArrayList<GroupTP>();

    public GroupTD(String name, Promo promo){
        this.name = name;
        this.promo = promo;
    }

    public void setGroupTPList(ArrayList<GroupTP> list){
        GroupsTP = list;
    }

    public String getName() {
        return name;
    }

    public Promo getPromo() {
        return promo;
    }

    public ArrayList<GroupTP> getGroupsTP() {
        return GroupsTP;
    }
}
