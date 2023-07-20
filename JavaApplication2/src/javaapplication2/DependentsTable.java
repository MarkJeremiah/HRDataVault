/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package javaapplication2;

import java.util.ArrayList;
import java.sql.*;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author MARK
 */
public class DependentsTable extends javax.swing.JInternalFrame {

    /**
     * Creates new form Menu1
     */
    
    public DependentsTable() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        display_dependents();              
           
    }
    
    public ArrayList<Dependents> dependentList(){
        ArrayList<Dependents> dependentList = new ArrayList<>();

        try {
        Class.forName("com.mysql.cj.jdbc.Driver");        
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrdatavault", "root", "mYsT4nd4rdQu3rYL4ngu4g3");
        System.out.print("Connected");
        String query1= "SELECT * FROM dependent_file";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query1);
        
        Dependents dependents;
        while(rs.next()){
           dependents = new Dependents(rs.getString("DepSID"), rs.getInt("EmpNo"),rs.getString("Dep_Name"), rs.getString("Relationship"),
                                        rs.getLong("Dep_Contact"), rs.getString("Birthday"),rs.getInt("Dep_Age"), rs.getString("Dep_Sex"));
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
    
    public void search(String str){
        DefaultTableModel model = (DefaultTableModel)DependentsTable.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        DependentsTable.setRowSorter(trs);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        DependentsTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(850, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        if (DependentsTable.getColumnModel().getColumnCount() > 0) {
            DependentsTable.getColumnModel().getColumn(0).setPreferredWidth(130);
            DependentsTable.getColumnModel().getColumn(7).setPreferredWidth(80);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 730, 530));

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jLabel1.setText("Search");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, -1, 30));

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
        getContentPane().add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 210, 30));

        jLabel2.setFont(new java.awt.Font("Poppins ExtraBold", 0, 24)); // NOI18N
        jLabel2.setText("Dependents Table");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchActionPerformed

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
        String str  = Search.getText();
        search(str);
    }//GEN-LAST:event_SearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DependentsTable;
    private javax.swing.JTextField Search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
