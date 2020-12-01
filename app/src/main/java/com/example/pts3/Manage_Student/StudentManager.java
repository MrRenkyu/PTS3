package com.example.pts3.Manage_Student;

import android.util.Log;

import com.example.pts3.Network_package.Network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class StudentManager{


    private static final String URL_JSON = "http://perso.univ-lemans.fr/~plafor/gestionabs/studentData.json";


    public ArrayList<Promo> promoList = new ArrayList<Promo>();

    public StudentManager(){
        try {
            byte[] byteStudents = Network.getUrlBytes(URL_JSON);
            Log.e("StudentManager", "JSON Byte were download");
            JSONArray jsonarray =new JSONArray(new String(byteStudents));

            for(int i=0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String namePromo       = jsonobject.getString("name");
                JSONArray arrayGroupsTD    = jsonobject.getJSONArray("groupsTD");
                ArrayList<GroupTD> groupTDList = new ArrayList<GroupTD>();
                Promo promo = new Promo(namePromo);

                for(int j=0; j < arrayGroupsTD.length(); j++) {
                    JSONObject GroupsTD = arrayGroupsTD.getJSONObject(j);
                    String nameGroupTD       = GroupsTD.getString("name");
                    JSONArray arrayGroupsTP    = GroupsTD.getJSONArray("groupsTP");
                    ArrayList<GroupTP> groupTPList = new ArrayList<GroupTP>();
                    GroupTD groupTD = new GroupTD(nameGroupTD,promo);

                    for(int k=0; k < arrayGroupsTP.length(); k++) {
                        JSONObject GroupsTP = arrayGroupsTP.getJSONObject(k);
                        String nameGroupTP       = GroupsTP.getString("name");
                        JSONArray arrayStudents    = GroupsTP.getJSONArray("students");
                        ArrayList<Student> studentList = new ArrayList<Student>();
                        GroupTP groupTP = new GroupTP(nameGroupTP,groupTD);

                        for(int l=0; l < arrayStudents.length(); l++) {
                            JSONObject Students = arrayStudents.getJSONObject(l);
                            studentList.add(new Student(groupTP,Students.getString("lastname"),Students.getString("firstname"),
                                    Students.getString("num"),Students.getString("birthDate"),
                                    Students.getString("bac"),Students.getString("originSchool")));
                        }
                        groupTP.setStudentsList(studentList);
                        groupTPList.add(groupTP);
                    }
                    groupTD.setGroupTPList(groupTPList);
                    groupTDList.add(groupTD);
                }
                promo.setGroupTDList(groupTDList);
                promoList.add(promo);
            }

            Log.e("studentManager",promoList.size()+"promo initialize");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudentByPromo(String promoName){
        ArrayList<Student> studentsList = new ArrayList<Student>();
        for (Promo promo: promoList) {
            if (promo.getName() == promoName){
                for (GroupTD groupTD: promo.getGroupsTD()) {
                    for (GroupTP groupTP: groupTD.getGroupsTP()) {
                        studentsList.addAll(groupTP.getStudentList());
                    }
                }

            }
        }
        return studentsList;
    }

    public ArrayList<Student> getStudentByTD(String tdName){
        ArrayList<Student> studentsList = new ArrayList<Student>();
        for (Promo promo: promoList) {
                for (GroupTD groupTD: promo.getGroupsTD()) {
                    if (groupTD.getName() == tdName){
                        for (GroupTP groupTP: groupTD.getGroupsTP()) {
                            studentsList.addAll(groupTP.getStudentList());
                        }
                    }
                }
        }
        return studentsList;
    }

    public ArrayList<Student> getStudentByTP(String tpName){
        ArrayList<Student> studentsList = new ArrayList<Student>();
        for (Promo promo: promoList) {
            for (GroupTD groupTD: promo.getGroupsTD()) {
                    for (GroupTP groupTP: groupTD.getGroupsTP()) {
                        if (groupTP.getName() == tpName)
                            studentsList.addAll(groupTP.getStudentList());
                }
            }
        }
        return studentsList;
    }

    public ArrayList<Student> getListStudentsByGroupName(String grpName){
        for (Promo promo: promoList) {
            if (promo.getName() == grpName) {  return promo.getStudentList(); }
            for (GroupTD groupTD: promo.getGroupsTD()) {
                if (promo.getName() == grpName) {  return promo.getStudentList(); }
                for (GroupTP groupTP: groupTD.getGroupsTP()) {
                    if (groupTP.getName() == grpName) {  return groupTP.getStudentList(); }
                }
            }
        }
        return null;
    }


    public ArrayList<Promo> getAllPromos(){
        return promoList;
    }

    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> allStudents = new ArrayList<>();
        for(Promo eachPromo : promoList){
            for(GroupTD grTd: eachPromo.getGroupsTD()){
                for(GroupTP grTp: grTd.getGroupsTP()){
                    allStudents.addAll(grTp.getStudentList());
                }
            }
        }

        return allStudents;
    }



    public ArrayList<Student> MatchingStudent(String parameter){
        ArrayList<Student> matchingListStudent = new ArrayList<>();

        for(Student eachStudent : getAllStudents()){
            if(eachStudent.getFirstName().contains(parameter.toUpperCase())){
                matchingListStudent.add(eachStudent);
                continue;
            }

            if(eachStudent.getLastName().contains(parameter.toUpperCase())){
                matchingListStudent.add(eachStudent);
                continue;
            }

            String promoName = eachStudent.getGroupTP().getGroupTD().getPromo().getName();
            if(promoName.contains(parameter.toUpperCase())){
                matchingListStudent.add(eachStudent);
                continue;
            }

            String grTd = eachStudent.getGroupTP().getGroupTD().getName();
            if(grTd.contains(parameter.toUpperCase())){
                matchingListStudent.add(eachStudent);
                continue;
            }

            String grTp = eachStudent.getGroupTP().getName();
            if(grTp.contains(parameter.toUpperCase())){
                matchingListStudent.add(eachStudent);
                continue;
            }

        }

        return matchingListStudent;
    }

    public ArrayList<String> getEachGroupName(){
        ArrayList<String> groupsName = new ArrayList<>();
        for(Promo eachPromo : promoList){
          groupsName.addAll(eachPromo.getNameOfGroupsAdChild());
        }
        return groupsName;
    }
}
