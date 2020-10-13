package com.example.pts3;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupTD implements Serializable {
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

    public int getNumberStudent(){
        int sommeNum = 0;
        for( GroupTP grTp : GroupsTP){
            sommeNum = sommeNum+ grTp.getNumberStudent();
        }
        return sommeNum;
    }
}
