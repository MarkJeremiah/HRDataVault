/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication2;

import java.util.Random;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MARK
 */
public class UpdateEmp extends javax.swing.JFrame {

    /**
     * Creates new form AddEmp
     */
    
    private int Index;
    public void setPassedInteger(Integer empno) {
           EmpNo.setText(empno.toString());
           Index = empno;
           // Call the method to fetch data from the database and update the JTextField
           fetchDataFromEmployee();
           display_dependents();
       }
    
    public UpdateEmp() {
        initComponents();
        display_dependents();
       /* int no = Integer.parseInt(EmpNo.getText());
        System.out.print(no); 
        Ito yung code if ever gusto mo kunin yung VALUE na nasa loob ng EmpNo na jLabel
        Importante toh sa index nung SQL data
        */
    }
    public ArrayList<Dependents> dependentList(){
        ArrayList<Dependents> dependentList = new ArrayList<>();

        try {
        Class.forName("com.mysql.cj.jdbc.Driver");        
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrdatavault", "root", "@forgotpassword123");
        System.out.print("Connected");
        String query1 = "SELECT * FROM dependents WHERE EmpNo=?";
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.setInt(1, Index);
        ResultSet rs = statement.executeQuery();
        
        
        Dependents dependents;
        while(rs.next()){
           dependents = new Dependents(rs.getString("Dep_SID"), rs.getInt("EmpNo"),rs.getString("Dep_Name"), rs.getString("Relationship"),
                                        rs.getLong("Dep_Contact"), rs.getString("Dep_Birthday"),rs.getInt("Dep_Age"), rs.getString("Dep_Sex"));
           dependentList.add(dependents);
        }
     
        
        
        } catch (Exception e) {
            System.out.print(e);
        }
        return dependentList;
    }
    
    public void display_dependents(){
        ArrayList<Dependents>list = dependentList();
        DefaultTableModel model = (DefaultTableModel)DependentsTable.getModel();
        Object [] row = new Object [8];
        for(int i = 0; i<list.size(); i++){
            row[0] = list.get(i).getDepSID();
            row[1] = list.get(i).getEmpNo();
            row[2] = list.get(i).getDepName();
            row[3] = list.get(i).getRelationship();
            row[4] = list.get(i).getContactNo();
            row[5] = list.get(i).getDepBirthday();
            row[6] = list.get(i).getDepAge();
            row[7] = list.get(i).getDepSex();
       
  
            model.addRow(row);
            
        }
            
        
    }

    public void fetchDataFromEmployee() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrdatavault", "root", "@forgotpassword123");

            String retrieve = "SELECT * FROM Employee WHERE Empno=?";
            
            PreparedStatement statement = connection.prepareStatement(retrieve);
            statement.setInt(1, Index);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("Empname");
                String position = resultSet.getString("Position");
                String department = resultSet.getString("Department");
                int contactnum = resultSet.getInt("ContactNo");
                int payrate = resultSet.getInt("PayRate");
                String per = resultSet.getString("PR_Per");
                String taxexempt = resultSet.getString("TaxExempt");
                String classification = resultSet.getString("Classification");
                String status = resultSet.getString("MaritalStatus");
                int bonus = resultSet.getInt("Bonus");
                String TOE = resultSet.getString("TOE");
                String LDW = resultSet.getString("LDW");
                String Eligibility = resultSet.getString("Eligibility");
                
                EmpName.setText(name);
                Position.setText(position);
                Department.setText(department);
                Contact.setText(String.valueOf(contactnum));
                PayRate.setText(String.valueOf(payrate));
                Per.setText(per);
                Bonus.setText(String.valueOf(bonus));
                LastDateWork.setText(LDW);
                
                if("Exempt".equals(taxexempt)){
                    Exempt.setSelected(true);
                }
                else if("Non-Exempt".equals(taxexempt)){
                    NonExempt.setSelected(true);
                }
                
                switch(classification){
                    case("Introductory"):
                        Introductory.setSelected(true);
                        break;
                        
                    case("Part-Time"):
                        PartTime.setSelected(true);
                        break;
                        
                    case("RFT"):
                        RFT.setSelected(true);
                        break;    
                        
                    case("Temporary"):
                        Temporary.setSelected(true);
                        break;    
                }
                
                switch(status){
                    case("Single"):
                        Single.setSelected(true);
                        break;
                        
                    case("Married"):
                        Married.setSelected(true);
                        break;
                        
                    case("Widowed"):
                        Widowed.setSelected(true);
                        break;    
                        
                    case("Seperated"):
                        Seperated.setSelected(true);
                        break;    
                }
                try{
                switch(TOE){
                    case("QWN"):
                        QWN.setSelected(true);
                        break;
                        
                    case("QWON"):
                        QWON.setSelected(true);
                        break;
                        
                    case("Terminated"):
                        Terminated.setSelected(true);
                        break;    
                        
                    case("Laid Off"):
                        LaidOff.setSelected(true);
                        break;    
                    case("EOA"):
                        EOA.setSelected(true);
                        break;    
                }
                
                if("Yes".equals(Eligibility)){
                    Yes.setSelected(true);
                }
                else if("Non-Exempt".equals(Eligibility)){
                    No.setSelected(true);
                }
                }catch (NullPointerException ex) {

                }
                
            }
            // Close the database resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
     private void saveDataToDatabase() {
        try {
            // Retrieve data from the input fields or components.
                String name = EmpName.getText();
                String position = Position.getText();
                String department = Department.getText();
                int contactNum = Integer.parseInt(Contact.getText());
                String TaxExempt="";
                int payrate = Integer.parseInt(PayRate.getText());
                String per = Per.getText();
                String classification="";
                String MaritalStatus="";
                int bonus = Integer.parseInt(Bonus.getText());
                String LDW ="";
                String TOE ="";
                String Eligibility ="";
                
                if (LDW != null && !LDW.isEmpty()) {
                    LastDateWork.getText();
             
                }
                else{
                    LDW = null;
                }
                
                if(Exempt.isSelected()){
                    TaxExempt ="Exempt";
                }
                else if(NonExempt.isSelected()){
                    TaxExempt = "Non-Exempt";
                }
                
                
                if(Introductory.isSelected()){
                    classification ="Introductory";
                }
                else if(PartTime.isSelected()){
                    classification ="Part-Time";
                }
                else if(RFT.isSelected()){
                    classification ="RFT";
                }
                else if(Temporary.isSelected()){
                    classification ="Temporary";
                }
                
                
                
                if(Single.isSelected()){
                    MaritalStatus ="Single";
                }
                else if(Married.isSelected()){
                    MaritalStatus ="Married";
                }
                else if(Widowed.isSelected()){
                    MaritalStatus ="Widowed";
                }
                
                else if(Seperated.isSelected()){
                    MaritalStatus="Seperated";
                }
                
                if(QWN.isSelected()){
                    TOE ="QWN";
                }
                else if(QWON.isSelected()){
                    TOE ="QWON";
                }
                
                else if(Terminated.isSelected()){
                    TOE ="Terminated";
                }
                
                else if(LaidOff.isSelected()){
                    TOE ="Laid-Off";
                }
                else if(EOA.isSelected()){
                    TOE ="EOA";
                }
                else{
                    TOE = null;
                }
                
                if(Yes.isSelected()){
                    Eligibility ="Yes";
                }
                else if(No.isSelected()){
                    Eligibility = "No";
                }
                else{
                    Eligibility = null;
                }
                
   


            // Establish a connection to the SQL database.
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hrdatavault",
                    "root",
                    "@forgotpassword123"
            );

            // Prepare the SQL INSERT query (assuming your table is called "Employee").
            String insertQuery = "UPDATE Employee SET Empname=?, Position=?, Department=?, ContactNo=?, PayRate=?, PR_Per=?, TaxExempt=?, Classification=?, MaritalStatus=?, Bonus=?, TOE=?, LDW=?, Eligibility=? WHERE Empno="+Index;
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, position);
            preparedStatement.setString(3, department);
            preparedStatement.setInt(4, contactNum);
            preparedStatement.setInt(5, payrate);
            preparedStatement.setString(6, per);
            preparedStatement.setString(7, TaxExempt);
            preparedStatement.setString(8, classification);
            preparedStatement.setString(9, MaritalStatus);
            preparedStatement.setInt(10, bonus);
            preparedStatement.setString(11, TOE);
            preparedStatement.setString(12, LDW);
            preparedStatement.setString(13, Eligibility);
            
            
            

            // Execute the INSERT query.
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data saved successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save data.");
            }

            // Close the database resources.
            preparedStatement.close();
            connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while saving data.");
        }
    }

    public void refreshTable() {
    // Clear the existing data from the table model
    DefaultTableModel model = (DefaultTableModel) DependentsTable.getModel();
    model.setRowCount(0);
    display_dependents();
    }
    
    private void deleteDependents(String DepSID) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrdatavault", "root", "@forgotpassword123");
        String deleteDependentsQuery = "DELETE FROM Dependents WHERE Dep_SID=?";
            try (PreparedStatement deleteDependentsStatement = connection.prepareStatement(deleteDependentsQuery)) {
                deleteDependentsStatement.setString(1, DepSID);  
                int rowsDeleted = deleteDependentsStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    
                    JOptionPane.showMessageDialog(null, "Dependents deleted successfully");
                    refreshTable();
                } else {
                
                    JOptionPane.showMessageDialog(null, "Error: Failed to delete dependents");
                }
            }
            
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
 }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        EmpNo = new javax.swing.JLabel();
        EmpName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Position = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Department = new javax.swing.JTextField();
        Per = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        PayRate = new javax.swing.JTextField();
        Label1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        NonExempt = new javax.swing.JCheckBox();
        Exempt = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Introductory = new javax.swing.JCheckBox();
        RFT = new javax.swing.JCheckBox();
        PartTime = new javax.swing.JCheckBox();
        Temporary = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        Seperated = new javax.swing.JRadioButton();
        Single = new javax.swing.JRadioButton();
        Delete = new javax.swing.JButton();
        Married = new javax.swing.JRadioButton();
        Widowed = new javax.swing.JRadioButton();
        Bonus = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();
        LastDateWork = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        QWN = new javax.swing.JRadioButton();
        QWON = new javax.swing.JRadioButton();
        EOA = new javax.swing.JRadioButton();
        LaidOff = new javax.swing.JRadioButton();
        Terminated = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        Yes = new javax.swing.JCheckBox();
        No = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Contact = new javax.swing.JTextField();
        Cancel = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Save2 = new javax.swing.JButton();
        Save3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DependentsTable = new javax.swing.JTable();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel1.setFocusable(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        EmpNo.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        EmpNo.setText("0000");
        jPanel1.add(EmpNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, 30));
        jPanel1.add(EmpName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 279, 30));

        jLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel2.setText("Employee Name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel3.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel3.setText("Position");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));
        jPanel1.add(Position, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 279, 30));

        jLabel4.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel4.setText("Department");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));
        jPanel1.add(Department, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 279, 30));
        jPanel1.add(Per, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 90, 30));

        jLabel5.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel5.setText("Per");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, -1, -1));
        jPanel1.add(PayRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 90, 30));

        Label1.setFont(new java.awt.Font("Poppins ExtraBold", 0, 14)); // NOI18N
        Label1.setText("Termination of Employment");
        jPanel1.add(Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 830, -1, 30));

        jLabel8.setText(".");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1070, 110, -1));

        jLabel9.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel9.setText("Pay Rate");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, -1, -1));

        NonExempt.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        NonExempt.setText("Non-Exempt");
        NonExempt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NonExemptActionPerformed(evt);
            }
        });
        jPanel1.add(NonExempt, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, -1, 20));

        Exempt.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        Exempt.setText("Exempt");
        Exempt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExemptActionPerformed(evt);
            }
        });
        jPanel1.add(Exempt, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, -1, 20));

        jLabel10.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel10.setText("Tax Exemption");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, -1, -1));

        jLabel11.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel11.setText("Marital Status");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, 20));

        Introductory.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        Introductory.setText("Introductory");
        Introductory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IntroductoryActionPerformed(evt);
            }
        });
        jPanel1.add(Introductory, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, -1, 20));

        RFT.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        RFT.setText("Regular Full Time");
        RFT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RFTActionPerformed(evt);
            }
        });
        jPanel1.add(RFT, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, -1, 20));

        PartTime.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        PartTime.setText("Part-Time");
        PartTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PartTimeActionPerformed(evt);
            }
        });
        jPanel1.add(PartTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, -1, 20));

        Temporary.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        Temporary.setText("Temporary");
        Temporary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TemporaryActionPerformed(evt);
            }
        });
        jPanel1.add(Temporary, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, -1, 20));

        jLabel12.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel12.setText("Eligible For Rehire?");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 920, -1, 20));

        Seperated.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        Seperated.setText("Seperated");
        Seperated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeperatedActionPerformed(evt);
            }
        });
        jPanel1.add(Seperated, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, -1, -1));

        Single.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        Single.setText("Single");
        Single.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SingleActionPerformed(evt);
            }
        });
        jPanel1.add(Single, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, -1, -1));

        Delete.setBackground(new java.awt.Color(102, 0, 102));
        Delete.setFont(new java.awt.Font("Poppins ExtraBold", 0, 12)); // NOI18N
        Delete.setForeground(new java.awt.Color(255, 255, 255));
        Delete.setText("Delete");
        Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteMouseClicked(evt);
            }
        });
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        jPanel1.add(Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, 90, 30));

        Married.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        Married.setText("Married");
        Married.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarriedActionPerformed(evt);
            }
        });
        jPanel1.add(Married, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, -1, -1));

        Widowed.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        Widowed.setText("Widowed");
        Widowed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WidowedActionPerformed(evt);
            }
        });
        jPanel1.add(Widowed, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, -1));
        jPanel1.add(Bonus, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 150, 30));

        jLabel13.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel13.setText("Bonus");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, -1, 30));

        Label2.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        Label2.setText("Update Employee");
        jPanel1.add(Label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        LastDateWork.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        LastDateWork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LastDateWorkActionPerformed(evt);
            }
        });
        jPanel1.add(LastDateWork, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 830, 160, 30));

        jLabel14.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel14.setText("Classification");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, -1));

        QWN.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        QWN.setText("Quit With Notice");
        QWN.setBorder(null);
        QWN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QWNActionPerformed(evt);
            }
        });
        jPanel1.add(QWN, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 870, -1, -1));

        QWON.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        QWON.setText("Quit Without Notice");
        QWON.setBorder(null);
        QWON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QWONActionPerformed(evt);
            }
        });
        jPanel1.add(QWON, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 870, -1, -1));

        EOA.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        EOA.setText("End of Assignment");
        EOA.setBorder(null);
        EOA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EOAActionPerformed(evt);
            }
        });
        jPanel1.add(EOA, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 890, -1, 30));

        LaidOff.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        LaidOff.setText("Laid Off");
        LaidOff.setBorder(null);
        LaidOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaidOffActionPerformed(evt);
            }
        });
        jPanel1.add(LaidOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 870, -1, -1));

        Terminated.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        Terminated.setText("Terminated");
        Terminated.setBorder(null);
        Terminated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TerminatedActionPerformed(evt);
            }
        });
        jPanel1.add(Terminated, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 870, -1, -1));

        jLabel15.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel15.setText("Last Date Worked");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 830, -1, 30));

        Yes.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        Yes.setText("Yes");
        Yes.setBorder(null);
        Yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YesActionPerformed(evt);
            }
        });
        jPanel1.add(Yes, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 920, -1, 20));

        No.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        No.setText("No");
        No.setBorder(null);
        No.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoActionPerformed(evt);
            }
        });
        jPanel1.add(No, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 920, -1, 20));

        jLabel6.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel6.setText("Employee Number:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 30));

        jLabel16.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel16.setText("Contact Number");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));
        jPanel1.add(Contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 279, 30));

        Cancel.setBackground(new java.awt.Color(102, 0, 102));
        Cancel.setFont(new java.awt.Font("Poppins ExtraBold", 0, 12)); // NOI18N
        Cancel.setForeground(new java.awt.Color(255, 255, 255));
        Cancel.setText("Cancel");
        Cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CancelMouseClicked(evt);
            }
        });
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });
        jPanel1.add(Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 980, 120, 40));

        Save.setBackground(new java.awt.Color(102, 0, 102));
        Save.setFont(new java.awt.Font("Poppins ExtraBold", 0, 12)); // NOI18N
        Save.setForeground(new java.awt.Color(255, 255, 255));
        Save.setText("Save");
        Save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveMouseClicked(evt);
            }
        });
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        jPanel1.add(Save, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 980, 120, 40));

        Save2.setBackground(new java.awt.Color(102, 0, 102));
        Save2.setFont(new java.awt.Font("Poppins ExtraBold", 0, 12)); // NOI18N
        Save2.setForeground(new java.awt.Color(255, 255, 255));
        Save2.setText("Add");
        Save2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Save2MouseClicked(evt);
            }
        });
        Save2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save2ActionPerformed(evt);
            }
        });
        jPanel1.add(Save2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 90, 30));

        Save3.setBackground(new java.awt.Color(102, 0, 102));
        Save3.setFont(new java.awt.Font("Poppins ExtraBold", 0, 12)); // NOI18N
        Save3.setForeground(new java.awt.Color(255, 255, 255));
        Save3.setText("Edit");
        Save3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Save3MouseClicked(evt);
            }
        });
        Save3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save3ActionPerformed(evt);
            }
        });
        jPanel1.add(Save3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 90, 30));

        DependentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DepSID", "EmpNo", "Name", "Relationship", "ContactNo", "Birthday", "Age", "Sex"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DependentsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        DependentsTable.setAutoscrolls(false);
        DependentsTable.setIntercellSpacing(new java.awt.Dimension(0, 2));
        jScrollPane1.setViewportView(DependentsTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 520, 320));

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void NonExemptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NonExemptActionPerformed
        if(NonExempt.isSelected()){
            Exempt.setSelected(false);
        }
    }//GEN-LAST:event_NonExemptActionPerformed

    private void ExemptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExemptActionPerformed
        if(Exempt.isSelected()){
            NonExempt.setSelected(false);
        }
    }//GEN-LAST:event_ExemptActionPerformed

    private void IntroductoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IntroductoryActionPerformed
    if(Introductory.isSelected()){
              PartTime.setSelected(false);
              RFT.setSelected(false);
              Temporary.setSelected(false);
          }
    }//GEN-LAST:event_IntroductoryActionPerformed

    private void RFTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RFTActionPerformed
    if(RFT.isSelected()){
               Introductory.setSelected(false);
               PartTime.setSelected(false);
               Temporary.setSelected(false);
           }
    }//GEN-LAST:event_RFTActionPerformed

    private void PartTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PartTimeActionPerformed
    if(PartTime.isSelected()){
               Introductory.setSelected(false);
               RFT.setSelected(false);
               Temporary.setSelected(false);
           }
    }//GEN-LAST:event_PartTimeActionPerformed

    private void TemporaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TemporaryActionPerformed
    if(Temporary.isSelected()){
               Introductory.setSelected(false);
               PartTime.setSelected(false);
               RFT.setSelected(false);
           }
    }//GEN-LAST:event_TemporaryActionPerformed

    private void QWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QWNActionPerformed
        if(QWN.isSelected()){
               QWON.setSelected(false);
               Terminated.setSelected(false);
               LaidOff.setSelected(false);
               EOA.setSelected(false);
           }
    }//GEN-LAST:event_QWNActionPerformed

    private void EOAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EOAActionPerformed
     if(EOA.isSelected()){
               QWN.setSelected(false);
               QWON.setSelected(false);
               Terminated.setSelected(false);
               LaidOff.setSelected(false);
           }
    }//GEN-LAST:event_EOAActionPerformed

    private void TerminatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerminatedActionPerformed
        if(Terminated.isSelected()){
                      QWN.setSelected(false);
                      QWON.setSelected(false);
                      LaidOff.setSelected(false);
                      EOA.setSelected(false);
                  }
    }//GEN-LAST:event_TerminatedActionPerformed

    private void QWONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QWONActionPerformed
        if(QWON.isSelected()){
                      QWN.setSelected(false);
                      Terminated.setSelected(false);
                      LaidOff.setSelected(false);
                      EOA.setSelected(false);
                  }
    }//GEN-LAST:event_QWONActionPerformed

    private void YesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YesActionPerformed
            if(Yes.isSelected()){
                          No.setSelected(false);
                      }
    }//GEN-LAST:event_YesActionPerformed

    private void NoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoActionPerformed
        if(No.isSelected()){
                      Yes.setSelected(false);
                  }
    }//GEN-LAST:event_NoActionPerformed

    private void LastDateWorkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastDateWorkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LastDateWorkActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelActionPerformed

    private void DeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseClicked
int selectedRow = DependentsTable.getSelectedRow();

    if (selectedRow >= 0) {
        String DepSID = (String) DependentsTable.getValueAt(selectedRow, 0);
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this dependent?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            deleteDependents(DepSID);
            refreshTable();
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please select a row in the Table");
    }
    }//GEN-LAST:event_DeleteMouseClicked

    private void CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelMouseClicked
       this.dispose();
    }//GEN-LAST:event_CancelMouseClicked

    private void SaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveMouseClicked

       saveDataToDatabase();
       this.dispose();
    }//GEN-LAST:event_SaveMouseClicked

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SaveActionPerformed

    private void Save2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Save2MouseClicked
    UpdateDependent add = new UpdateDependent();
        add.setPassedInteger(Index);
        add.setVisible(true);
    }//GEN-LAST:event_Save2MouseClicked

    private void Save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Save2ActionPerformed

    private void SingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SingleActionPerformed
      if(Single.isSelected()){
            Married.setSelected(false);
            Widowed.setSelected(false);
            Seperated.setSelected(false);
        }
    }//GEN-LAST:event_SingleActionPerformed

    private void MarriedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarriedActionPerformed
      if(Married.isSelected()){
            Single.setSelected(false);
            Widowed.setSelected(false);
            Seperated.setSelected(false);
        }
    }//GEN-LAST:event_MarriedActionPerformed

    private void WidowedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WidowedActionPerformed
      if(Widowed.isSelected()){
            Single.setSelected(false);
            Married.setSelected(false);
            Seperated.setSelected(false);
        }
    }//GEN-LAST:event_WidowedActionPerformed

    private void SeperatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeperatedActionPerformed
      if(Seperated.isSelected()){
            Single.setSelected(false);
            Married.setSelected(false);
            Widowed.setSelected(false);
        }
    }//GEN-LAST:event_SeperatedActionPerformed

    private void Save3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Save3MouseClicked
     int selectedRow = DependentsTable.getSelectedRow();
    
    if (selectedRow >= 0) {
        String selectedString = (String) DependentsTable.getValueAt(selectedRow, 0);
        UpdateDependent add = new UpdateDependent();
        add.setPassedString(selectedString);
        add.setVisible(true);
    }
    else{
        JOptionPane.showMessageDialog(null,"Please select a row in the Table");
    }

    }//GEN-LAST:event_Save3MouseClicked

    private void Save3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Save3ActionPerformed

    private void LaidOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaidOffActionPerformed
        if(LaidOff.isSelected()){
                       QWN.setSelected(false);
                       QWON.setSelected(false);
                       Terminated.setSelected(false);
                       EOA.setSelected(false);
                   }
    }//GEN-LAST:event_LaidOffActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UpdateEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateEmp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bonus;
    private javax.swing.JButton Cancel;
    private javax.swing.JTextField Contact;
    private javax.swing.JButton Delete;
    private javax.swing.JTextField Department;
    private javax.swing.JTable DependentsTable;
    private javax.swing.JRadioButton EOA;
    private javax.swing.JTextField EmpName;
    private javax.swing.JLabel EmpNo;
    private javax.swing.JCheckBox Exempt;
    private javax.swing.JCheckBox Introductory;
    private javax.swing.JLabel Label1;
    private javax.swing.JLabel Label2;
    private javax.swing.JRadioButton LaidOff;
    private javax.swing.JFormattedTextField LastDateWork;
    private javax.swing.JRadioButton Married;
    private javax.swing.JCheckBox No;
    private javax.swing.JCheckBox NonExempt;
    private javax.swing.JCheckBox PartTime;
    private javax.swing.JTextField PayRate;
    private javax.swing.JTextField Per;
    private javax.swing.JTextField Position;
    private javax.swing.JRadioButton QWN;
    private javax.swing.JRadioButton QWON;
    private javax.swing.JCheckBox RFT;
    private javax.swing.JButton Save;
    private javax.swing.JButton Save2;
    private javax.swing.JButton Save3;
    private javax.swing.JRadioButton Seperated;
    private javax.swing.JRadioButton Single;
    private javax.swing.JCheckBox Temporary;
    private javax.swing.JRadioButton Terminated;
    private javax.swing.JRadioButton Widowed;
    private javax.swing.JCheckBox Yes;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
