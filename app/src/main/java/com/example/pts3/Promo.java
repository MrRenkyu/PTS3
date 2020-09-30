package com.example.pts3;

import java.util.ArrayList;

public class Promo {

    private String name;
    private ArrayList<GroupTD> GroupsTD = new ArrayList<GroupTD>();

    public Promo(String name){
        this.name = name;
    }

    public void setGroupTDList(ArrayList<GroupTD> list){
        GroupsTD = list;
    }

    public String getName() {
        return name;
    }

    public ArrayList<GroupTD> getGroupsTD() {
        return GroupsTD;
    }

}
