/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.swing.JOptionPane;


/**
 *
 * @author MARK
 */
public class AddDependent extends javax.swing.JFrame {

    /**
     * Creates new form AddEmp
     */
    private String SID;
    
    public AddDependent() {
        initComponents();
        Random random = new Random();
        int deptsid = random.nextInt(10) * 1000 + random.nextInt(10) * 100 + random.nextInt(10) * 10 + random.nextInt(10);
        System.out.println(deptsid);
        DeptSID.setText("D"+String.valueOf(deptsid)); //Inassign ko yung random integer sa text

       /* int no = Integer.parseInt(EmpNo.getText());
        System.out.print(no); 
        Ito yung code if ever gusto mo kunin yung VALUE na nasa loob ng EmpNo na jLabel
        Importante toh sa index nung SQL data
        */
    }
 
    public void setPassedInteger(Integer empno) {
        empNo.setText(String.valueOf(empno));
        // Call the method to fetch data from the database and update the JTextField

    }

 
    public void saveDataToDatabase() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrdatavault", "root", "@forgotpassword123");

        // Prepare an UPDATE SQL statement to update the data in the database
        String updateQuery = ("INSERT into dependents(Dep_Name, Relationship,Dep_Contact,Dep_Birthday,Dep_Age,Dep_Sex,Dep_SID,EmpNo)VALUES(?,?,?,?,?,?,?,?)");
        PreparedStatement statement = connection.prepareStatement(updateQuery);

        // Set the parameters for the update statement
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(jDateChooser1.getDate());
        
        statement.setString(1, Name.getText());
        statement.setString(2, Relationship.getText());
        statement.setString(4, formattedDate);
        statement.setInt(5, Integer.parseInt(AGE.getText()));
        
            
            Long num;
            if (ContactNum.getText().isEmpty()) {
                num = null;
                statement.setNull(3, java.sql.Types.BIGINT); // Set the parameter as null in the prepared statement
            } else {
                try {
                    num = Long.parseLong(ContactNum.getText());
                    statement.setLong(3, num);
                } catch (NumberFormatException e) {
      
                    e.printStackTrace();
                }
            }
        
        
        // Get the selected sex from the radio buttons
        String sex = "";
        if (Male.isSelected()) {
            sex = "Male";
        } else if (Female.isSelected()) {
            sex = "Female";
        }
        statement.setString(6, sex);
        
        statement.setString(7, DeptSID.getText());
        statement.setInt(8, Integer.parseInt(empNo.getText()));


     int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Data updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update data!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Close the database resources
        statement.close();
        connection.close();

    } catch (Exception ex) {
        ex.printStackTrace();
        // Handle any exceptions or display error messages as needed
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
        DeptSID = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Relationship = new javax.swing.JTextField();
        Contact = new javax.swing.JLabel();
        ContactNum = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Male = new javax.swing.JRadioButton();
        Female = new javax.swing.JRadioButton();
        Label2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        empNo = new javax.swing.JLabel();
        AGE = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        Save2 = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setUndecorated(true);

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(241, 239, 239));
        jPanel1.setFocusable(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DeptSID.setFont(new java.awt.Font("Poppins ExtraBold", 0, 18)); // NOI18N
        DeptSID.setText("D3245");
        jPanel1.add(DeptSID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 110, 30));
        jPanel1.add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 250, 30));

        jLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel2.setText("Name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        jLabel3.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel3.setText("Relationship");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));
        jPanel1.add(Relationship, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 250, 30));

        Contact.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        Contact.setText("Contact Number");
        jPanel1.add(Contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));
        jPanel1.add(ContactNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 250, 30));

        jLabel9.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel9.setText("Birthday");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, -1));

        jLabel11.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel11.setText("Sex");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, 30));

        Male.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        Male.setText("Male");
        Male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaleActionPerformed(evt);
            }
        });
        jPanel1.add(Male, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, -1, 30));

        Female.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        Female.setText("Female");
        Female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FemaleActionPerformed(evt);
            }
        });
        jPanel1.add(Female, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, -1, 30));

        Label2.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        Label2.setForeground(new java.awt.Color(102, 0, 102));
        Label2.setText("Add Dependent");
        jPanel1.add(Label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Poppins ExtraBold", 0, 18)); // NOI18N
        jLabel6.setText("Employee ID:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, 30));

        jLabel16.setFont(new java.awt.Font("Poppins ExtraBold", 0, 18)); // NOI18N
        jLabel16.setText("Dependent SID:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 140, 30));

        empNo.setFont(new java.awt.Font("Poppins ExtraBold", 0, 18)); // NOI18N
        empNo.setText("0000");
        jPanel1.add(empNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, -1, 30));

        AGE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AGEActionPerformed(evt);
            }
        });
        jPanel1.add(AGE, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 50, 30));

        jLabel17.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel17.setText("Age");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, -1, 30));

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
        jPanel1.add(Save2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, -280, 90, 30));

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
        jPanel1.add(Save, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 120, 40));

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
        jPanel1.add(Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, 120, 40));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 250, 30));

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Save2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Save2MouseClicked

    }//GEN-LAST:event_Save2MouseClicked

    private void Save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Save2ActionPerformed

    private void SaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveMouseClicked
        String sex="";
        if(Male.isSelected()){
            sex = "male";
        }
        else if(Female.isSelected()){
            sex = "female";
        }
        else{
            sex = null;
        }
        
        
        if(Name.getText().isEmpty()|| Relationship.getText().isEmpty()|| Relationship.getText().isEmpty()|| AGE.getText().isEmpty() || sex == null) {
          JOptionPane.showMessageDialog(this, "Please fill out all the required attributes of the dependents");
       }else{
           saveDataToDatabase();
           this.dispose(); 
        }
        
     
    }//GEN-LAST:event_SaveMouseClicked

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
  
    }//GEN-LAST:event_SaveActionPerformed

    private void CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelMouseClicked
      this.dispose();
    }//GEN-LAST:event_CancelMouseClicked

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelActionPerformed

    private void MaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaleActionPerformed
       if(Male.isSelected()){
            Female.setSelected(false);
        }
    }//GEN-LAST:event_MaleActionPerformed

    private void FemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FemaleActionPerformed
        if(Female.isSelected()){
            Male.setSelected(false);
        }
    }//GEN-LAST:event_FemaleActionPerformed

    private void AGEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AGEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AGEActionPerformed

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
            java.util.logging.Logger.getLogger(AddDependent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDependent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDependent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDependent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDependent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AGE;
    private javax.swing.JButton Cancel;
    private javax.swing.JLabel Contact;
    private javax.swing.JTextField ContactNum;
    private javax.swing.JLabel DeptSID;
    private javax.swing.JRadioButton Female;
    private javax.swing.JLabel Label2;
    private javax.swing.JRadioButton Male;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Relationship;
    private javax.swing.JButton Save;
    private javax.swing.JButton Save2;
    private javax.swing.JLabel empNo;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
