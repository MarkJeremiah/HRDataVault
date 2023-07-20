/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

/**
 *
 * @author MARK
 */
class Dependents {
    private long ContactNo;
    private int EmpNo,Dep_Age;
    private String Dep_SID,Dep_Name, Relationship, Dep_Contact, Dep_Birthday, Dep_Sex;
    
    public Dependents(String Dep_SID, int EmpNo, String Dep_Name, String Relationship, long ContactNo, String Dep_Birthday, int Dep_Age, String Dep_Sex){
        this.EmpNo = EmpNo;
        this.Dep_SID = Dep_SID;
        this.Dep_Name = Dep_Name;
        this.Relationship = Relationship;
        this.ContactNo = ContactNo;
        this.Dep_Birthday = Dep_Birthday;
        this.Dep_Age = Dep_Age;
        this.Dep_Sex = Dep_Sex;       
    }
    
    public int getEmpNo() {
        return EmpNo;
    }

    public String getDepSID() {
        return Dep_SID;
    }

    public String getDepName() {
        return Dep_Name;
    }

    public String getRelationship() {
        return Relationship;
    }

    public long getContactNo() {
        return ContactNo;
    }

    public String getDepBirthday() {
        return Dep_Birthday;
    }

    public int getDepAge() {
        return Dep_Age;
    }

    public String getDepSex() {
        return Dep_Sex;
    }
    
}

