package com.example.pts3;

import java.io.Serializable;
import java.util.ArrayList;

public class Promo  implements ListStudent {

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

    public int getNumberStudent(){
        int sommeNum = 0;
        for( GroupTD grTd : GroupsTD){
            sommeNum = sommeNum+ grTd.getNumberStudent();
        }
        return sommeNum;
    }

    @Override
    public ArrayList<Student> getStudentList() {
        ArrayList<Student> studentList = new ArrayList<>();
        for(GroupTD eachTD : GroupsTD){
            studentList.addAll(eachTD.getStudentList());
        }
        return studentList;
    }
}
