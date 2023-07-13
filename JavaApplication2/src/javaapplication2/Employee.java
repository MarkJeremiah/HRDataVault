/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

/**
 *
 * @author MARK
 */
class Employee {
    private int EmpNo, ContactNo, PayRate, Bonus;;
    private String EmpName, Position, Department, PR_Per, TaxExempt, Classification, MaritalStatus, TOE, LDW, Eligibility;
    
    public Employee(int EmpNo, String EmpName, int ContactNo, String Position, String Department, int PayRate, String PR_Per, String TaxExempt, String Classification, String MaritalStatus, int Bonus, String TOE, String LDW, String Eligbility){
        this.EmpNo = EmpNo;
        this.EmpName = EmpName;
        this.ContactNo = ContactNo;
        this.Position = Position;
        this.Department = Department;
        this.PayRate = (int) PayRate;
        this.PR_Per = PR_Per;
        this.TaxExempt = TaxExempt;
        this.Classification = Classification;
        this.MaritalStatus = MaritalStatus;
        this.Bonus = (int) Bonus;
        this.TOE = TOE;
        this.LDW = LDW;
        this.Eligibility = Eligibility;        
    }
    
    public int getEmpNo(){
        return EmpNo;
    }
    public String getEmpName(){
        return EmpName;
    }
    public int getContactNo(){
        return ContactNo;
    }
    public String getPosition(){
        return Position;
    }
    public String getDepartment(){
        return Department;
    }
    public int getPayRate(){
        return PayRate;
    }
    public String getPR_Per(){
        return PR_Per;
    }
    public String getTaxExempt(){
        return TaxExempt;
    }
    public String getClassification(){
        return Classification;
    }
    public String getMaritalStatus(){
        return MaritalStatus;
    }
    public int getBonus(){
        return Bonus;
    }
    public String getTOE(){
        return TOE;
    }
    public String getLDW(){
        return LDW;
    }
    public String getEligibility(){
        return Eligibility;
    }
    
}

