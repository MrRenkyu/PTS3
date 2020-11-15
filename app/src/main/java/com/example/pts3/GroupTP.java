package com.example.pts3;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupTP implements ListStudent {

    private String name;
    private GroupTD groupTD;
    private ArrayList<Student> students = new ArrayList<Student>();

    public GroupTP(String name, GroupTD groupTD){
        this.name = name;
        this.groupTD = groupTD;
    }

    public void setStudentsList(ArrayList<Student> list){
        students = list;
    }

    public String getName() {
        return name;
    }

    public GroupTD getGroupTD() {
        return groupTD;
    }

    public int getNumberStudent(){
        return students.size();
    }

    @Override
    public ArrayList<Student> getStudentList() {
        return students;
    }
}
