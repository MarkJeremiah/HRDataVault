/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication2;

import java.util.Random;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author MARK
 */
public class UpdateEmp extends javax.swing.JFrame {

    /**
     * Creates new form AddEmp
     */
    private int Index;
 
    public UpdateEmp() {
        initComponents();
 
       /* int no = Integer.parseInt(EmpNo.getText());
        System.out.print(no); 
        Ito yung code if ever gusto mo kunin yung VALUE na nasa loob ng EmpNo na jLabel
        Importante toh sa index nung SQL data
        */
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
                
                EmpName.setText(name);
                Position.setText(position);
                Department.setText(department);
                Contact.setText(String.valueOf(contactnum));
                PayRate.setText(String.valueOf(payrate));
                Per.setText(per);
                Bonus.setText(String.valueOf(bonus));
                
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
            }
            // Close the database resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPassedInteger(Integer empno) {
        EmpNo.setText(empno.toString());
        Index = empno;
        // Call the method to fetch data from the database and update the JTextField
        fetchDataFromEmployee();
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
                    classification ="Seperated";
                }
                
                
   


            // Establish a connection to the SQL database.
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hrdatavault",
                    "root",
                    "@forgotpassword123"
            );

            // Prepare the SQL INSERT query (assuming your table is called "Employee").
            String insertQuery = "UPDATE Employee SET Empname=?, Position=?, Department=?, ContactNo=?, PayRate=?, PR_Per=?, TaxExempt=?, Classification=?, MaritalStatus=?, Bonus=? WHERE Empno="+Index;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Contact = new javax.swing.JTextField();
        Cancel = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Save2 = new javax.swing.JButton();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 540, 310));

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
        jPanel1.add(Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 90, 30));

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

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jFormattedTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 830, 160, 30));

        jLabel14.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel14.setText("Classification");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, -1));

        jRadioButton5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jRadioButton5.setText("Quit With Notice");
        jRadioButton5.setBorder(null);
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 870, -1, -1));

        jRadioButton6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jRadioButton6.setText("Quit Without Notice");
        jRadioButton6.setBorder(null);
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 870, -1, -1));

        jRadioButton7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jRadioButton7.setText("End of Assignment");
        jRadioButton7.setBorder(null);
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 890, -1, 30));

        jRadioButton8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jRadioButton8.setText("Laid Off");
        jRadioButton8.setBorder(null);
        jPanel1.add(jRadioButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 870, -1, -1));

        jRadioButton9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jRadioButton9.setText("Terminated");
        jRadioButton9.setBorder(null);
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 870, -1, -1));

        jLabel15.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel15.setText("Last Date Worked");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 830, -1, 30));

        jCheckBox7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jCheckBox7.setText("Yes");
        jCheckBox7.setBorder(null);
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 920, -1, 20));

        jCheckBox8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jCheckBox8.setText("No");
        jCheckBox8.setBorder(null);
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 920, -1, 20));

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
        Save2.setText("Edit");
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

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelActionPerformed

    private void DeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseClicked

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
        // TODO add your handling code here:
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
    private javax.swing.JTextField EmpName;
    private javax.swing.JLabel EmpNo;
    private javax.swing.JCheckBox Exempt;
    private javax.swing.JCheckBox Introductory;
    private javax.swing.JLabel Label1;
    private javax.swing.JLabel Label2;
    private javax.swing.JRadioButton Married;
    private javax.swing.JCheckBox NonExempt;
    private javax.swing.JCheckBox PartTime;
    private javax.swing.JTextField PayRate;
    private javax.swing.JTextField Per;
    private javax.swing.JTextField Position;
    private javax.swing.JCheckBox RFT;
    private javax.swing.JButton Save;
    private javax.swing.JButton Save2;
    private javax.swing.JRadioButton Seperated;
    private javax.swing.JRadioButton Single;
    private javax.swing.JCheckBox Temporary;
    private javax.swing.JRadioButton Widowed;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JFormattedTextField jFormattedTextField1;
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
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
