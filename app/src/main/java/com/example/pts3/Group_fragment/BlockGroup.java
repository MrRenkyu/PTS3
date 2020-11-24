package com.example.pts3.Group_fragment;

import com.example.pts3.Manage_Student.Student;

import java.util.ArrayList;

public class BlockGroup {
    private String categorie;
    private int nbTab;
    private int nbEleve;
    private ArrayList<Student> studentArrayList;

    public BlockGroup(int nbTab, String categorie, int nbEleve, ArrayList<Student> studentList) {
        this.nbTab = nbTab;
        this.categorie = categorie;
        this.nbEleve = nbEleve;
        this.studentArrayList = studentList;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNbTab() {
        return nbTab;
    }

    public void setNbTab(int nbTab) {
        this.nbTab = nbTab;
    }

    public int getNbEleve() {
        return nbEleve;
    }

    public void setNbEleve(int nbEleve) {
        this.nbEleve = nbEleve;
    }

    public ArrayList<Student> getStudentArrayList() {
        return studentArrayList;
    }

    public void setStudentArrayList(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }
}
