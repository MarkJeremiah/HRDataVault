/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author MARK
 */
public class DeparmentsTable extends javax.swing.JFrame {
    private String index;

    public void setPassedString(String department) {
        Department.setText(department + " Department");
        index = department;
        employeeList();
    }
      
    public DeparmentsTable() {
        initComponents();
        display_employee();
        
    }
     public ArrayList<Employee> employeeList(){
        ArrayList<Employee> employeeList = new ArrayList<>();
        
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");        
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrdatavault", "root", "@forgotpassword123");
        
        String retrieve = "SELECT * FROM employee WHERE Department=?";
        PreparedStatement statement = connection.prepareStatement(retrieve);
        statement.setString(1, index);
        ResultSet rs = statement.executeQuery();
        
        Employee employee;
        while(rs.next()){
           employee = new Employee(rs.getInt("EmpNo"), rs.getString("EmpName"), rs.getLong("ContactNo"), rs.getString("Position"), 
                                        rs.getString("Department"), rs.getInt("PayRate"), rs.getString("PR_Per"), rs.getString("TaxExempt"),
                                          rs.getString("Classification"), rs.getString("MaritalStatus"), rs.getInt("Bonus"), rs.getString("TOE"), rs.getString("LDW"), rs.getString("Eligibility"));
           employeeList.add(employee);
        }
     
        
        
        } catch (Exception e) {
            System.out.print(e);
        }
        return employeeList;
    }
    
    public void display_employee(){
        ArrayList<Employee> list = employeeList();
        DefaultTableModel model = (DefaultTableModel)EmployeeTable.getModel();
        Object [] row = new Object [14];
        for(int i = 0; i<list.size(); i++){
            row[0] = list.get(i).getEmpNo();
            row[1] = list.get(i).getEmpName();
            row[2] = list.get(i).getContactNo();
            row[3] = list.get(i).getPosition();
            row[4] = list.get(i).getDepartment();
            row[5] = list.get(i).getPayRate();
            row[6] = list.get(i).getPR_Per();
            row[7] = list.get(i).getTaxExempt();
            row[8] = list.get(i).getClassification();
            row[9] = list.get(i).getMaritalStatus();
            row[10] = list.get(i).getBonus();
            row[11] = list.get(i).getTOE();
            row[12] = list.get(i).getLDW();
            row[13] = list.get(i).getEligibility();
            model.addRow(row);
            
        }
            
        
    }  
    
    public void search(String str){
        DefaultTableModel model = (DefaultTableModel)EmployeeTable.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        EmployeeTable.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        EmployeeTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        Department = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(362, 30));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(856, 652));

        jPanel1.setPreferredSize(new java.awt.Dimension(856, 652));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(102, 0, 102));
        jButton1.setFont(new java.awt.Font("Poppins ExtraBold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Go Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 120, 40));

        EmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EmpNo", "EmpName", "Contact No", "Position", "Department", "PayRate", "PR_Per", "TaxExempt", "Classification", "MaritalStatus", "Bonus", "TOE", "LDW", "Eligibility"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        EmployeeTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        EmployeeTable.setAutoscrolls(false);
        EmployeeTable.setGridColor(new java.awt.Color(255, 255, 255));
        EmployeeTable.setIntercellSpacing(new java.awt.Dimension(0, 2));
        EmployeeTable.setSelectionBackground(new java.awt.Color(255, 51, 255));
        EmployeeTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(EmployeeTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 730, 470));

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Search");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, -1, 30));

        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });
        jPanel1.add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 210, 30));

        Department.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        Department.setForeground(new java.awt.Color(255, 255, 255));
        Department.setText("Department");
        jPanel1.add(Department, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication2/Emp_T.jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 860, 710));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchActionPerformed

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
        String str  = Search.getText();
        search(str);
    }//GEN-LAST:event_SearchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DeparmentsTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeparmentsTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeparmentsTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeparmentsTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeparmentsTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Department;
    private javax.swing.JTable EmployeeTable;
    private javax.swing.JTextField Search;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
