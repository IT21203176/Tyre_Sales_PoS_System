/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.possystem;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Return extends javax.swing.JPanel {

    
    public Return() {
        
        initComponents();
        line_amount();
        lines_total();
        data_load();
        tb_load();
        
    }
    
    public void tb_load(){
       
       try {
           
           DefaultTableModel dt = (DefaultTableModel) jTable2.getModel();
           dt.setRowCount(0);
           
           Statement s = db.mycon().createStatement();
           ResultSet rs = s.executeQuery(" SELECT * FROM purchase_o");
           
           while (rs.next()) {               
               
               Vector v = new Vector();
               
               v.add(rs.getString(2));
               v.add(rs.getString(3));
               v.add(rs.getString(5));
               v.add(rs.getString(4));
               v.add(rs.getString(7));
               v.add(rs.getString(8));
               v.add(rs.getString(16));
               v.add(rs.getString(17));
               v.add(rs.getString(19));
               v.add(rs.getString(20));
               v.add(rs.getString(29));
               v.add(rs.getString(30));
               
               dt.addRow(v);
               
           }
           
       } catch (Exception e) {
           System.out.println(e);
       }
       
       try {
           
           DefaultTableModel dt = (DefaultTableModel) jTable4.getModel();
           dt.setRowCount(0);
           
           Statement s = db.mycon().createStatement();
           ResultSet rs = s.executeQuery(" SELECT * FROM cusorder");
           
           while (rs.next()) {               
               
               Vector v = new Vector();
               
               v.add(rs.getString(2));
               v.add(rs.getString(3));
               v.add(rs.getString(4));
               v.add(rs.getString(5));
               v.add(rs.getString(6));
               v.add(rs.getString(7));
               v.add(rs.getString(8));
               v.add(rs.getString(11));
               v.add(rs.getString(15));
               v.add(rs.getString(16));
               v.add(rs.getString(13));
               v.add(rs.getString(25));
               
               dt.addRow(v);
               
           }
           
       } catch (Exception e) {
           System.out.println(e);
       }
    
       
       try {
           
           DefaultTableModel dt = (DefaultTableModel) jTable5.getModel();
           dt.setRowCount(0);
           
           Statement s = db.mycon().createStatement();
           ResultSet rs = s.executeQuery(" SELECT * FROM cusorder");
           
           while (rs.next()) {               
               
               Vector v = new Vector();
               
               v.add(rs.getString(2));
               v.add(rs.getString(15));
               v.add(rs.getString(16));
               v.add(rs.getString(11));
               v.add(rs.getString(17));
               
               dt.addRow(v);
               
           }
           
       } catch (Exception e) {
           System.out.println(e);
       }    
    }
    
    public void data_load(){
        
        // load product description
        
        try {
            
            Statement s = db.mycon().createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM product");
            Vector v = new Vector();
            
            while (rs.next()) {                
                
                v.add(rs.getString("descrip"));
                
                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                pro_des.setModel(com);
                
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        // load product description on customer order
        
        try {
            
            Statement s = db.mycon().createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM product");
            Vector v = new Vector();
            
            while (rs.next()) {                
                
                v.add(rs.getString("descrip"));
                
                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                pro_des1.setModel(com);
                
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        // load last invoice number
        
        
        try {
            
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM extra WHERE exid = 1");
            
            if (rs.next()) {
                
                PO_id.setText(rs.getString("val"));
                
                
            }
            
        } catch (Exception e) {
        }
        
        // increment invoice number   
        
        int i  = Integer.valueOf(PO_id.getText());
        i++;
        PO_id.setText(String.valueOf(i));
        
        
        // load last invoice number on customer order
        
        
        try {
            
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM extra WHERE exid = 1");
            
            if (rs.next()) {
                
                CO_id.setText(rs.getString("val"));
                
                
            }
            
        } catch (Exception e) {
        }
        
        // increment invoice number   
        
        int y  = Integer.valueOf(CO_id.getText());
        y++;
        CO_id.setText(String.valueOf(y));
        
    }
    
    
    public void line_amount(){
        
        // purchase order
        
        try {
            
            Double unit_price = Double.valueOf(u_price.getText());
            Double line_price = 0.00;
            Double qty = Double.valueOf(pro_qty.getText());

            line_price = unit_price * qty;

            amount.setText(String.valueOf(line_price));
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        // customer order
        
        try {
            
            Double u_price = Double.valueOf(u_price1.getText());
            Double l_price = 0.00;
            Double pqty = Double.valueOf(pro_qty1.getText());

            l_price = u_price * pqty;

            amount1.setText(String.valueOf(l_price));
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }
    
    public void return_tot(){
        // order return
        
        try {
            
            Double u_p = Double.valueOf(C_returnup.getText());
            Double l_p = 0.00;
            Double pq = Double.valueOf(C_returnq.getText());

            l_p = u_p * pq;

            C_returntp.setText(String.valueOf(l_p));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void lines_total() {
        
        // p.o.
        int numofrow = jTable1.getRowCount();
        double total = 0.00;

        for (int i = 0; i < numofrow; i++) {
            Object value = jTable1.getValueAt(i, 4);

            if (value != null && !value.toString().isEmpty()) {
                double numericValue = Double.parseDouble(value.toString());
                total += numericValue;
            }
        }

        sub_amt.setText(Double.toString(total));
        //discount();
        
        
        //c.o.
        int numofrow1 = jTable3.getRowCount();
        double total1 = 0.00;

        for (int i = 0; i < numofrow1; i++) {
            Object value1 = jTable3.getValueAt(i, 3);

            if (value1 != null && !value1.toString().isEmpty()) {
                double numericValue1 = Double.parseDouble(value1.toString());
                total1 += numericValue1;
            }
        }

        sub_amt1.setText(Double.toString(total1));
        
        //c.o.
        int numofrow2 = jTable6.getRowCount();
        double total2 = 0.00;

        for (int i = 0; i < numofrow2; i++) {
            Object value2 = jTable6.getValueAt(i, 2);

            if (value2 != null && !value2.toString().isEmpty()) {
                double numericValue2 = Double.parseDouble(value2.toString());
                total2 += numericValue2;
            }
        }

        C_returntp2.setText(Double.toString(total2));
    }


    
    /*public void discount(){
        
        try {
            
            Double dts_precentage = Double.valueOf(discount_perc.getText());
            Double dts_price = 0.0;
            Double sub_bill = Double.valueOf(sub_amt.getText());
            
            dts_price = sub_bill * dts_precentage /100;
            
            discount_amt.setText(String.valueOf(dts_price));
        
        } catch (Exception e) {
            System.out.println(e);
        }
        
        //totccal();
        
    }*/
    
    
    public void tot(){ // total calculation
        
        //p.o.
        
        DecimalFormat df = new DecimalFormat("000.00");
        
        Double subtotal_amount = Double.valueOf(sub_amt.getText());
        Double ship_cost = Double.valueOf(ship_c.getText());
        Double taxperc = Double.valueOf(tax_perc.getText());
        Double discount = Double.valueOf(discount_perc.getText());
        
        // calculate tax amount
        Double tax_amount = subtotal_amount * taxperc / 100.00 ;
        tax_amt.setText(String.valueOf(tax_amount));
        
        // calculate discount amount
        Double discount_amount = subtotal_amount * discount / 100.00 ;
        discount_amt.setText(String.valueOf(discount_amount));
        
        // calculate shipping cost
        /*Double shipping_cost = Double.valueOf(ship_amt.getText());
        ship_amt.setText(ship_c.getText());*/
        
        Double shipping_cost = ship_cost;
        ship_amt.setText(String.valueOf(ship_cost));
        
        // Grand Total
        Double net_amount = (subtotal_amount + shipping_cost + tax_amount) - discount_amount;
        net_amt.setText(df.format(net_amount));
        
        
        
      
    }
    
    public void tot1(){
        // c.o.
        
        DecimalFormat df = new DecimalFormat("000.00");

                
        Double subtotal_amount1 = Double.valueOf(sub_amt1.getText());
        Double ship_cost1 = Double.valueOf(ship_c1.getText());
        Double discount1 = Double.valueOf(discount_perc1.getText());
        
        // calculate discount amount
        Double discount_amount1 = subtotal_amount1 * discount1 / 100.00 ;
        discount_amt1.setText(String.valueOf(discount_amount1));
        
        // calculate shipping cost
        /*Double shipping_cost = Double.valueOf(ship_amt.getText());
        ship_amt.setText(ship_c.getText());*/
        
        Double shipping_cost1 = ship_cost1;
        ship_amt1.setText(String.valueOf(ship_cost1));
        
        // Grand Total
        Double net_amount1 = (subtotal_amount1 + shipping_cost1) - discount_amount1;
        net_amt1.setText(df.format(net_amount1));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PO_id = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        PO_Date = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        search_id = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        V_name = new javax.swing.JTextField();
        V_com_name = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        V_address = new javax.swing.JTextArea();
        V_phone = new javax.swing.JTextField();
        V_mail = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        MY_name = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        MY_com_name = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MY_address = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        MY_phone = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        MY_mail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        ship_term = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        ship_mthd = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        deli_date = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        pro_des = new javax.swing.JComboBox<>();
        pro_code = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        pro_qty = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        u_price = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        amount = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        add_tb = new javax.swing.JButton();
        save = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        remove_all = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        sub_amt = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        discount_amt = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        tax_amt = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        ship_amt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        net_amt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        ship_c = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tax_perc = new javax.swing.JTextField();
        paid_amt5 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        discount_perc = new javax.swing.JTextField();
        paid_amt6 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        payment_status = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        notes = new javax.swing.JTextArea();
        add_tb1 = new javax.swing.JButton();
        update = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        search_vendor = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        CO_id = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        search_id1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        CO_Date = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        C_name = new javax.swing.JTextField();
        C_nic = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        C_address = new javax.swing.JTextArea();
        C_phone = new javax.swing.JTextField();
        C_mail = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        d_method = new javax.swing.JComboBox<>();
        d_no = new javax.swing.JTextField();
        d_ddate = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        Payment_method = new javax.swing.JComboBox<>();
        d_of_pay = new javax.swing.JTextField();
        Payment_s = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        pro_des1 = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        pro_qty1 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        u_price1 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        amount1 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        notes1 = new javax.swing.JTextArea();
        jPanel15 = new javax.swing.JPanel();
        sub_amt1 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        discount_amt1 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        ship_amt1 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        net_amt1 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        ship_c1 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        discount_perc1 = new javax.swing.JTextField();
        paid_amt8 = new javax.swing.JTextField();
        add_tb2 = new javax.swing.JButton();
        save1 = new javax.swing.JButton();
        remove1 = new javax.swing.JButton();
        remove_all1 = new javax.swing.JButton();
        add_tb3 = new javax.swing.JButton();
        update1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        search_customer = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        C_returnid = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        c_rdate = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        C_name1 = new javax.swing.JTextField();
        C_nic1 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        C_phone1 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        C_orderno = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        C_orderno1 = new javax.swing.JTextField();
        C_pdes = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        C_returnq = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        C_returnup = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        C_returntp = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        Addtb = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        reasons = new javax.swing.JComboBox<>();
        jPanel23 = new javax.swing.JPanel();
        reasons1 = new javax.swing.JComboBox<>();
        jLabel81 = new javax.swing.JLabel();
        C_returntp1 = new javax.swing.JTextField();
        save2 = new javax.swing.JButton();
        jLabel82 = new javax.swing.JLabel();
        C_returntp2 = new javax.swing.JTextField();

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Purchase Order ID :");

        PO_id.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PO_id.setText("01");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Date ");

        PO_Date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PO_Date.setText("mm/dd/yyyy");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Search (by ID)");

        search_id.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(PO_id, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addComponent(search_id, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(PO_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37)
                        .addComponent(search_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(PO_id)
                        .addComponent(jLabel4)
                        .addComponent(PO_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vendor ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Contact Person Name  :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Client Company Name :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Address                    :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Phone                      :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Email                       :");

        V_name.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        V_com_name.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        V_address.setColumns(20);
        V_address.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        V_address.setRows(5);
        jScrollPane1.setViewportView(V_address);

        V_phone.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        V_mail.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(V_name)
                    .addComponent(V_com_name)
                    .addComponent(jScrollPane1)
                    .addComponent(V_phone)
                    .addComponent(V_mail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(V_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(V_com_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(V_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(V_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ship To ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("Contact Person Name  :");

        MY_name.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Company Name          :");

        MY_com_name.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText("Address                     :");

        MY_address.setColumns(20);
        MY_address.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        MY_address.setRows(5);
        jScrollPane2.setViewportView(MY_address);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setText("Phone                        :");

        MY_phone.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel13.setText("Email                         :");

        MY_mail.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MY_mail)
                    .addComponent(MY_phone)
                    .addComponent(MY_com_name, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addComponent(MY_name, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(34, 34, 34))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(MY_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(MY_com_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(MY_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MY_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel14.setText("Shipping Terms :");

        ship_term.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel15.setText("Shipping Method :");

        ship_mthd.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel16.setText("Delivery Date :");

        deli_date.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        deli_date.setText("dd/mm/yyyy");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel17.setText("Product Description :");

        pro_des.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        pro_des.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pro_des.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pro_desActionPerformed(evt);
            }
        });

        pro_code.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        pro_code.setText("Code");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel19.setText("Qty :");

        pro_qty.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        pro_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pro_qtyKeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel20.setText("Unit Price :");

        u_price.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        u_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                u_priceKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel21.setText("Amount :");

        amount.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amountKeyReleased(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Description", "Product Code", "Qty", "Unit Price", "Line Total"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        add_tb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add_tb.setText("Add to Table");
        add_tb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_tbActionPerformed(evt);
            }
        });

        save.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        remove.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        remove.setText("Remove");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        remove_all.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        remove_all.setText("Remove All");
        remove_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_allActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        sub_amt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel22.setText("SUB TOTAL :");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel23.setText("DISCOUNT  :");

        discount_amt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel24.setText("TAX            :");

        tax_amt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel25.setText("SHIPPING    :");

        ship_amt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel26.setText("NET TOTAL :");

        net_amt.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        net_amt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                net_amtKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Shipping Cost");

        ship_c.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ship_c.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ship_cKeyReleased(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Tax");

        tax_perc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tax_perc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tax_percKeyReleased(evt);
            }
        });

        paid_amt5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        paid_amt5.setText("%");
        paid_amt5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paid_amt5KeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Discount");

        discount_perc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        discount_perc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discount_percKeyReleased(evt);
            }
        });

        paid_amt6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        paid_amt6.setText("%");
        paid_amt6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paid_amt6KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(net_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(ship_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(tax_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(discount_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(sub_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(discount_perc)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(paid_amt6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ship_c, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                    .addComponent(tax_perc, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(paid_amt5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(33, 33, 33))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(ship_c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(tax_perc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(paid_amt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(discount_perc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paid_amt6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(sub_amt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(discount_amt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(tax_amt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(ship_amt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(net_amt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel35.setText("Payment Status :");

        payment_status.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        payment_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paid", "Pending", "Partial" }));
        payment_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payment_statusActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel36.setText("Other Notes :");

        notes.setColumns(20);
        notes.setRows(5);
        jScrollPane5.setViewportView(notes);

        add_tb1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add_tb1.setText("Search");
        add_tb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_tb1ActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pro_des, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pro_code, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(ship_term, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(68, 68, 68)
                                        .addComponent(jLabel15)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pro_qty)
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(u_price, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(ship_mthd, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel21)
                                        .addGap(18, 18, 18)
                                        .addComponent(amount))
                                    .addComponent(deli_date, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(add_tb)
                                                .addGap(51, 51, 51)
                                                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, Short.MAX_VALUE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel35)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(payment_status, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38)))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(remove_all, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(update))
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(add_tb1)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel36)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(83, 83, 83)))
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(ship_term, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(ship_mthd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(deli_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(pro_des, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pro_code)
                    .addComponent(jLabel19)
                    .addComponent(pro_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(u_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel35)
                                    .addComponent(payment_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add_tb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(save)
                                    .addComponent(remove)
                                    .addComponent(remove_all)
                                    .addComponent(add_tb)
                                    .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Purchase Order", jPanel2);

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Search (by Vendor)");

        search_vendor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        search_vendor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_vendorKeyReleased(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Product Description :");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Description");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("dd/mm/yyyy");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Issued Date               :");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Quantity                     :");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("00");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(search_vendor, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(search_vendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PO_ID", "Issue Date", "Vendor", "V_Contact Person", "V_Phone", "V_Email", "Delivery Date", "Product Description", "Quantity", "Unit Price", "Net Total", "Payment Status"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("View P.O.", jPanel3);

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel38.setText("Customer Order ID :");

        CO_id.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CO_id.setText("01");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Search (by ID)");

        search_id1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Date ");

        CO_Date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CO_Date.setText("mm/dd/yyyy");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel38)
                .addGap(18, 18, 18)
                .addComponent(CO_id, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(209, 209, 209)
                .addComponent(jLabel39)
                .addGap(18, 18, 18)
                .addComponent(search_id1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel40)
                .addGap(18, 18, 18)
                .addComponent(CO_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40)
                        .addComponent(CO_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel39)
                        .addComponent(search_id1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38)
                        .addComponent(CO_id)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Customer Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel41.setText("Name                      :");

        C_name.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        C_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_nameActionPerformed(evt);
            }
        });

        C_nic.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel42.setText("NIC No                    :");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel43.setText("Address                   :");

        C_address.setColumns(20);
        C_address.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        C_address.setRows(5);
        jScrollPane6.setViewportView(C_address);

        C_phone.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        C_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_phoneActionPerformed(evt);
            }
        });

        C_mail.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel44.setText("Email                      :");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel45.setText("Phone                     :");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(C_name)
                    .addComponent(C_nic)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(C_phone)
                    .addComponent(C_mail))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(C_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(C_nic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel43)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(C_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Delivery", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel46.setText("Method           :");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel47.setText("Delivery No     :");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel48.setText("Delivery Date  :");

        d_method.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        d_method.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Drop Off", "Shipping", "Pick Up" }));

        d_no.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        d_ddate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        d_ddate.setText("mm/dd/yyyy");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(d_method, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(d_no)
                    .addComponent(d_ddate, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(d_method, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(d_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(d_ddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Payment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel49.setText("Method                  :");

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel50.setText("Payment Status       :");

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel51.setText("Date of Payment      :");

        Payment_method.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Payment_method.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Check", "Card", "Credit" }));
        Payment_method.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Payment_methodActionPerformed(evt);
            }
        });

        d_of_pay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        d_of_pay.setText("mm/dd/yyyy");

        Payment_s.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Payment_s.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paid", "Unpaid", "Partial" }));
        Payment_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Payment_sActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Payment_method, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Payment_s, 0, 242, Short.MAX_VALUE)
                    .addComponent(d_of_pay))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(Payment_method, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(Payment_s, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(d_of_pay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel52.setText("Product Description :");

        pro_des1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        pro_des1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pro_des1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pro_des1ActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel53.setText("Qty :");

        pro_qty1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        pro_qty1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pro_qty1KeyReleased(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel54.setText("Unit Price :");

        u_price1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        u_price1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                u_price1KeyReleased(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel55.setText("Amount :");

        amount1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        amount1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amount1KeyReleased(evt);
            }
        });

        jTable3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Description", "Qty", "Unit Price", "Line Total"
            }
        ));
        jScrollPane7.setViewportView(jTable3);

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel56.setText("Other Notes :");

        notes1.setColumns(20);
        notes1.setRows(5);
        jScrollPane8.setViewportView(notes1);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Amount", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        sub_amt1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        sub_amt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sub_amt1KeyReleased(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel57.setText("SUB TOTAL :");

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel58.setText("DISCOUNT  :");

        discount_amt1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        discount_amt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discount_amt1KeyReleased(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel60.setText("SHIPPING    :");

        ship_amt1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        ship_amt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ship_amt1KeyReleased(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel61.setText("NET TOTAL :");

        net_amt1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        net_amt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                net_amt1KeyReleased(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setText("Shipping Cost");

        ship_c1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ship_c1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ship_c1KeyReleased(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel64.setText("Discount");

        discount_perc1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        discount_perc1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discount_perc1KeyReleased(evt);
            }
        });

        paid_amt8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        paid_amt8.setText("%");
        paid_amt8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paid_amt8KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(net_amt1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(ship_amt1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(discount_amt1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(sub_amt1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(discount_perc1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(paid_amt8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(ship_c1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(ship_c1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(discount_perc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paid_amt8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(sub_amt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(discount_amt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(ship_amt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(net_amt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        add_tb2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add_tb2.setText("Add to Table");
        add_tb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_tb2ActionPerformed(evt);
            }
        });

        save1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        save1.setText("Save");
        save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save1ActionPerformed(evt);
            }
        });

        remove1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        remove1.setText("Remove");
        remove1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove1ActionPerformed(evt);
            }
        });

        remove_all1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        remove_all1.setText("Remove All");
        remove_all1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_all1ActionPerformed(evt);
            }
        });

        add_tb3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add_tb3.setText("Search");
        add_tb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_tb3ActionPerformed(evt);
            }
        });

        update1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        update1.setText("Update");
        update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(12, 12, 12)
                        .addComponent(pro_des1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pro_qty1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel54)
                        .addGap(18, 18, 18)
                        .addComponent(u_price1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel55)
                        .addGap(18, 18, 18)
                        .addComponent(amount1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane8))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(add_tb2)
                                .addGap(51, 51, 51)
                                .addComponent(save1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(remove1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(remove_all1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(add_tb3)
                                    .addComponent(update1))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(pro_des1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(pro_qty1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(u_price1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(amount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel56)))
                        .addGap(9, 9, 9)
                        .addComponent(add_tb3)
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(save1)
                            .addComponent(remove1)
                            .addComponent(remove_all1)
                            .addComponent(add_tb2)
                            .addComponent(update1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Customer Order", jPanel4);

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Search (by Customer)");

        search_customer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        search_customer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_customerKeyReleased(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setText("Product Description :");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("Description");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setText("dd/mm/yyyy");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setText("Issued Date               :");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel68.setText("Quantity                     :");

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setText("00");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel59)
                .addGap(18, 18, 18)
                .addComponent(search_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(search_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(jLabel65))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(jLabel66))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CO_ID", "Issue Date", "Customer Name", "C_NIC", "C_address", "C_Phone", "C_Email", "Delivery Date", "Product Description", "Quantity", "Net Total", "Payment Status"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("View C.O.", jPanel5);

        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel70.setText("Return ID :");

        C_returnid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        C_returnid.setText("01");

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setText("Date ");

        c_rdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        c_rdate.setText("mm/dd/yyyy");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel70)
                .addGap(18, 18, 18)
                .addComponent(C_returnid, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel72)
                .addGap(18, 18, 18)
                .addComponent(c_rdate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(c_rdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70)
                    .addComponent(C_returnid))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Customer Order Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel71.setText("Name          :");

        C_name1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        C_name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_name1ActionPerformed(evt);
            }
        });
        C_name1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                C_name1KeyReleased(evt);
            }
        });

        C_nic1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel73.setText("NIC No        :");

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel74.setText("Phone         :");

        C_phone1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        C_phone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_phone1ActionPerformed(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel75.setText("Order No     :");

        C_orderno.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Product Description", "Quantity", "Delivery Date", "Unit Price"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel74, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(C_name1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(C_nic1)
                    .addComponent(C_phone1)
                    .addComponent(C_orderno, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(C_name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(C_nic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel74)
                            .addComponent(C_phone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75)
                            .addComponent(C_orderno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Goods Returned", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel76.setText("Order No                    ");

        C_orderno1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        C_pdes.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel77.setText("Product Description");

        C_returnq.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        C_returnq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                C_returnqKeyReleased(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel78.setText("Return Quantity");

        C_returnup.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        C_returnup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                C_returnupKeyReleased(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel79.setText("Unit Price");

        C_returntp.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        C_returntp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                C_returntpKeyReleased(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel80.setText("Total Price");

        Addtb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Addtb.setText("Add");
        Addtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddtbActionPerformed(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Product Description", "Quantity", "Total Price"
            }
        ));
        jScrollPane11.setViewportView(jTable6);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(C_returnup, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C_returntp, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(C_orderno1)
                        .addComponent(C_pdes)
                        .addComponent(C_returnq, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Addtb))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(C_orderno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(C_pdes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(C_returnq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Addtb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(C_returnup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(C_returntp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 37, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reason for Return", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        reasons.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reasons.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Goods defective", "Goods wrongly ordered", "Goods wrongly delivered", "Other reason" }));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reasons, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reasons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Customer Ask For", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        reasons1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reasons1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit note", "Inspection", "Exchange", "Repair", "Quotation", "Modification" }));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reasons1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reasons1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel81.setText("Return Amount");

        C_returntp1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        save2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        save2.setText("Save");
        save2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save2ActionPerformed(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel82.setText("Sub Amount");

        C_returntp2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        C_returntp2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                C_returntp2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(C_returntp2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(C_returntp1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(save2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel82)
                                .addComponent(C_returntp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel81)
                                .addComponent(C_returntp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(save2)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Good Returns", jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ship_cKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ship_cKeyReleased
        tot();
    }//GEN-LAST:event_ship_cKeyReleased

    private void tax_percKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tax_percKeyReleased
        tot();
    }//GEN-LAST:event_tax_percKeyReleased

    private void paid_amt5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paid_amt5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_paid_amt5KeyReleased

    private void paid_amt6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paid_amt6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_paid_amt6KeyReleased

    private void discount_percKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discount_percKeyReleased
        tot();
    }//GEN-LAST:event_discount_percKeyReleased

    private void pro_desActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pro_desActionPerformed
        // load product information
        
        String des = pro_des.getSelectedItem().toString();
        
        try {

            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT product_id,costp FROM product WHERE descrip = '"+des+"' ");

            if (rs.next()) {

                pro_code.setText(rs.getString("product_id"));
                u_price.setText(rs.getString("costp"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_pro_desActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // purchase order data save in the db
        
        try {
            
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            
            int rc = dt.getRowCount();
            
            for (int i = 0; i < rc; i++) {
                
                String description = dt.getValueAt(i, 0).toString(); // get product description
                String code = dt.getValueAt(i, 1).toString(); // get product code
                String qty = dt.getValueAt(i, 2).toString(); // get quantity
                String unit_price = dt.getValueAt(i, 3).toString(); // get unit price
                String line_amount = dt.getValueAt(i, 4).toString(); // get line total
                
                String pono = PO_id.getText(); // p.o. id
                
                String date = PO_Date.getText(); // p.o. issue date
                
                String v_na = V_name.getText(); //vendor name
                String v_cna = V_com_name.getText(); // vendor company name
                String v_add = V_address.getText(); // vendor address
                String v_ph = V_phone.getText(); // vendor phone
                String v_ml = V_mail.getText(); // vendor mail
                
                String s_na = MY_name.getText(); //my name
                String s_cna = MY_com_name.getText(); // my company name
                String s_add = MY_address.getText(); // my address
                String s_ph = MY_phone.getText(); // my phone
                String s_ml = MY_mail.getText(); // my mail
                
                String s_term = ship_term.getText(); // shipping term
                String s_mthd = ship_mthd.getText(); // shipping method
                String s_del_date = deli_date.getText(); // delivery date
                
                String shipp = ship_c.getText(); // shipping
                String tx = tax_perc.getText(); // tax %
                String disc = discount_perc.getText(); // discount %
                
                String sub = sub_amt.getText(); // sub total
                String samount = ship_amt.getText(); // shipping amount
                String tamount = tax_amt.getText(); // tax amount
                String damount = discount_amt.getText(); // discount amount
                String net = net_amt.getText(); // net total
                
                String status = payment_status.getSelectedItem().toString(); // payment status
                String notes = this.notes.getText(); // notes
                
                // Insert Data
                Statement s = db.mycon().createStatement();
                s.executeUpdate(" INSERT INTO purchase_o (po_no,po_date,v_name,v_comname,v_address,v_phone,v_mail,s_name,s_comname,s_address,s_phone,s_mail,sh_term,sh_mthd,d_date,p_des,p_code,p_qty,p_uprice,p_lineamt,shipping,tax,discount,s_total,d_amt,t_amt,s_amt,n_total,p_status,notes) VALUES ('"+pono+"', '"+date+"', '"+v_na+"', '"+v_cna+"', '"+v_add+"', '"+v_ph+"', '"+v_ml+"', '"+s_na+"', '"+s_cna+"', '"+s_add+"', '"+s_ph+"', '"+s_ml+"', '"+s_term+"', '"+s_mthd+"', '"+s_del_date+"', '"+description+"', '"+code+"', '"+qty+"', '"+unit_price+"', '"+line_amount+"', '"+shipp+"', '"+tx+"', '"+disc+"', '"+sub+"', '"+damount+"', '"+tamount+"', '"+samount+"', '"+net+"', '"+status+"', '"+notes+"')");
                
            }
            
            JOptionPane.showMessageDialog(null, "Data Saved.");
            
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_saveActionPerformed

    private void amountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyReleased
        line_amount();
    }//GEN-LAST:event_amountKeyReleased

    private void add_tbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_tbActionPerformed
        // Total price cal

        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        Vector v = new Vector();

        v.add(pro_des.getSelectedItem().toString()); // product description
        v.add(pro_code.getText()); // product code
        v.add(pro_qty.getText()); // quantity
        v.add(u_price.getText()); // product unit price
        v.add(amount.getText()); // line amount

        dt.addRow(v);
        
        line_amount();
        lines_total();
    }//GEN-LAST:event_add_tbActionPerformed

    private void pro_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pro_qtyKeyReleased
        // TODO add your handling code here:
        line_amount();
    }//GEN-LAST:event_pro_qtyKeyReleased

    private void u_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_u_priceKeyReleased
        // TODO add your handling code here:
        line_amount();
    }//GEN-LAST:event_u_priceKeyReleased

    private void net_amtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_net_amtKeyReleased
        // TODO add your handling code here:
        tot();
    }//GEN-LAST:event_net_amtKeyReleased

    private void payment_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payment_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_payment_statusActionPerformed

    private void search_vendorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_vendorKeyReleased
        try {
            
            String vendor = search_vendor.getText();
            
            DefaultTableModel dt = (DefaultTableModel) jTable2.getModel();
            dt.setRowCount(0);
            Statement s = db.mycon().createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM purchase_o WHERE v_comname LIKE '%"+vendor+"%'");
            
            while (rs.next()) {                
                
                Vector v = new Vector();
                
                
               v.add(rs.getString(2));
               v.add(rs.getString(3));
               v.add(rs.getString(5));
               v.add(rs.getString(4));
               v.add(rs.getString(7));
               v.add(rs.getString(8));
               v.add(rs.getString(16));
               v.add(rs.getString(17));
               v.add(rs.getString(19));
               v.add(rs.getString(20));
               v.add(rs.getString(29));
               v.add(rs.getString(30));
                
                dt.addRow(v);
                
            }
            
        } catch (Exception e) {
            tb_load();
        }
        
      
       
    }//GEN-LAST:event_search_vendorKeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // mouse click get data to text field
        
        int r = jTable2.getSelectedRow();
        String id = jTable2.getValueAt(r, 0).toString();
        String issue_date = jTable2.getValueAt(r, 1).toString();
        String vendor = jTable2.getValueAt(r, 2).toString();
        String v_c_person = jTable2.getValueAt(r, 3).toString();
        String v_phone = jTable2.getValueAt(r, 4).toString();
        String v_email = jTable2.getValueAt(r, 5).toString();
        String d_date = jTable2.getValueAt(r, 6).toString();
        String description = jTable2.getValueAt(r, 7).toString();
        String qty = jTable2.getValueAt(r, 8).toString();
        String u_price = jTable2.getValueAt(r, 9).toString();
        String n_total = jTable2.getValueAt(r, 10).toString();
        String status = jTable2.getValueAt(r, 11).toString();
        
        
        
        
        jLabel30.setText(description);
        jLabel31.setText(issue_date);
        jLabel34.setText(qty);
        
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void add_tb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_tb1ActionPerformed
        String search = search_id.getText();
        
        try {
            Statement s = db.mycon().createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM purchase_o WHERE purchaseid = '"+search+"'");
            
            if (rs.next()) {
                
                PO_id.setText(rs.getString("po_no"));
                PO_Date.setText(rs.getString("po_date"));
                V_name.setText(rs.getString("v_name"));
                V_com_name.setText(rs.getString("v_comname"));
                V_address.setText(rs.getString("v_address"));
                V_phone.setText(rs.getString("v_phone"));
                V_mail.setText(rs.getString("v_mail"));
                MY_name.setText(rs.getString("s_name"));
                MY_com_name.setText(rs.getString("s_comname"));
                MY_address.setText(rs.getString("s_address"));
                MY_phone.setText(rs.getString("s_phone"));
                MY_mail.setText(rs.getString("s_mail"));
                ship_term.setText(rs.getString("sh_term"));
                ship_mthd.setText(rs.getString("sh_mthd"));
                deli_date.setText(rs.getString("d_date"));
                pro_des.setSelectedItem(rs.getString("p_des"));
                pro_code.setText(rs.getString("p_code"));
                pro_qty.setText(rs.getString("p_qty"));
                u_price.setText(rs.getString("p_uprice"));
                amount.setText(rs.getString("p_lineamt"));
                ship_c.setText(rs.getString("shipping"));
                tax_perc.setText(rs.getString("tax"));
                discount_perc.setText(rs.getString("discount"));
                sub_amt.setText(rs.getString("s_total"));
                discount_amt.setText(rs.getString("d_amt"));
                tax_amt.setText(rs.getString("t_amt"));
                ship_amt.setText(rs.getString("s_amt"));
                net_amt.setText(rs.getString("n_total"));
                payment_status.setSelectedItem(rs.getString("p_status"));
                notes.setText(rs.getString("notes"));
               
                
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_add_tb1ActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // update button code
        
        String id = search_id.getText();
        
                String pono = PO_id.getText(); // p.o. id
                
                String date = PO_Date.getText(); // p.o. issue date
                
                String v_na = V_name.getText(); //vendor name
                String v_cna = V_com_name.getText(); // vendor company name
                String v_add = V_address.getText(); // vendor address
                String v_ph = V_phone.getText(); // vendor phone
                String v_ml = V_mail.getText(); // vendor mail
                
                String s_na = MY_name.getText(); //my name
                String s_cna = MY_com_name.getText(); // my company name
                String s_add = MY_address.getText(); // my address
                String s_ph = MY_phone.getText(); // my phone
                String s_ml = MY_mail.getText(); // my mail
                
                String s_term = ship_term.getText(); // shipping term
                String s_mthd = ship_mthd.getText(); // shipping method
                String s_del_date = deli_date.getText(); // delivery date
                
                String p_descrip = pro_des.getSelectedItem().toString(); // sub total
                String code = pro_code.getText(); // shipping amount
                String pq = pro_qty.getText(); // tax amount
                String pup = u_price.getText(); // discount amount
                String plamt = amount.getText(); // net total
                
                String shipp = ship_c.getText(); // shipping
                String tx = tax_perc.getText(); // tax %
                String disc = discount_perc.getText(); // discount %
                
                String sub = sub_amt.getText(); // sub total
                String samount = ship_amt.getText(); // shipping amount
                String tamount = tax_amt.getText(); // tax amount
                String damount = discount_amt.getText(); // discount amount
                String net = net_amt.getText(); // net total
                
                String status = payment_status.getSelectedItem().toString(); // payment status
                String notes = this.notes.getText(); // notes
        
        
        try {
            
            Statement s = db.mycon().createStatement();
            s.executeUpdate(" UPDATE purchase_o SET po_no = '"+pono+"', po_date = '"+date+"', v_name = '"+v_na+"', v_comname = '"+v_cna+"', v_address = '"+v_add+"', v_phone = '"+v_ph+"', v_mail = '"+v_ml+"', s_name = '"+s_na+"', s_comname = '"+s_cna+"', s_address = '"+s_add+"', s_phone = '"+s_ph+"', s_mail = '"+s_ml+"', sh_term = '"+s_term+"', sh_mthd = '"+s_mthd+"', d_date = '"+s_del_date+"', p_des = '"+p_descrip+"', p_code = '"+code+"', p_qty = '"+pq+"', p_uprice = '"+pup+"', p_lineamt = '"+plamt+"', shipping = '"+shipp+"', tax = '"+tx+"', discount = '"+disc+"', s_total = '"+samount+"', d_amt = '"+damount+"', t_amt = '"+tamount+"', s_amt = '"+samount+"', n_total = '"+net+"', p_status = '"+status+"', notes = '"+notes+"' WHERE purchaseid = '"+id+"'   ");
                        JOptionPane.showMessageDialog(null, "Data Updated.");

            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        tb_load();
    }//GEN-LAST:event_updateActionPerformed

    private void C_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_phoneActionPerformed

    private void C_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_nameActionPerformed

    private void Payment_methodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Payment_methodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Payment_methodActionPerformed

    private void Payment_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Payment_sActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Payment_sActionPerformed

    private void pro_des1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pro_des1ActionPerformed
        // load product information for c.o.
        
        String des = pro_des1.getSelectedItem().toString();
        
        try {

            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT costp FROM product WHERE descrip = '"+des+"' ");

            if (rs.next()) {

                u_price1.setText(rs.getString("costp"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_pro_des1ActionPerformed

    private void pro_qty1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pro_qty1KeyReleased
        // TODO add your handling code here:
        line_amount();
    }//GEN-LAST:event_pro_qty1KeyReleased

    private void u_price1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_u_price1KeyReleased
        // TODO add your handling code here:
        line_amount();
    }//GEN-LAST:event_u_price1KeyReleased

    private void amount1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amount1KeyReleased
        line_amount();
    }//GEN-LAST:event_amount1KeyReleased

    private void net_amt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_net_amt1KeyReleased
        // TODO add your handling code here:
        tot1();
    }//GEN-LAST:event_net_amt1KeyReleased

    private void ship_c1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ship_c1KeyReleased
        // TODO add your handling code here:
        tot1();
    }//GEN-LAST:event_ship_c1KeyReleased

    private void discount_perc1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discount_perc1KeyReleased
        // TODO add your handling code here:
        tot1();
    }//GEN-LAST:event_discount_perc1KeyReleased

    private void paid_amt8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paid_amt8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_paid_amt8KeyReleased

    private void add_tb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_tb2ActionPerformed
        // Total price cal

        DefaultTableModel dt = (DefaultTableModel) jTable3.getModel();
        Vector v = new Vector();

        v.add(pro_des1.getSelectedItem().toString()); // product description
        v.add(pro_qty1.getText()); // quantity
        v.add(u_price1.getText()); // product unit price
        v.add(amount1.getText()); // line amount

        dt.addRow(v);
        
        line_amount();
        lines_total();
    }//GEN-LAST:event_add_tb2ActionPerformed

    private void save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save1ActionPerformed
        // customer order data save in the db
        
        try {
            
            DefaultTableModel dt = (DefaultTableModel) jTable3.getModel();
            
            int rc = dt.getRowCount();
            
            for (int i = 0; i < rc; i++) {
                
                String description1 = dt.getValueAt(i, 0).toString(); // get product description
                String qty1 = dt.getValueAt(i, 1).toString(); // get quantity
                String unit_price1 = dt.getValueAt(i, 2).toString(); // get unit price
                String line_amount1 = dt.getValueAt(i, 3).toString(); // get line total
                
                String cono = CO_id.getText(); // c.o. id
                
                String cdate = CO_Date.getText(); // c.o. issue date
                
                String c_na = C_name.getText(); //customer name
                String c_nic = C_nic.getText(); // customer nic
                String c_add = C_address.getText(); // customer address
                String c_ph = C_phone.getText(); // customer phone
                String c_ml = C_mail.getText(); // customer mail
                
                String c_dmthd = d_method.getSelectedItem().toString(); // delivery method
                String c_dno = d_no.getText(); // delivery no
                String c_ddate = d_ddate.getText(); // delivery date
                
                String c_pmthd = Payment_method.getSelectedItem().toString(); // payment method
                String c_psta = Payment_s.getSelectedItem().toString(); // payment status
                String c_pdate = d_of_pay.getText(); // payment date
                
                String shipp1 = ship_c1.getText(); // shipping
                String disc1 = discount_perc1.getText(); // discount %
                
                String sub1 = sub_amt1.getText(); // sub total
                String samount1 = ship_amt1.getText(); // shipping amount
                String damount1 = discount_amt1.getText(); // discount amount
                String net1 = net_amt1.getText(); // net total
                
                String notes = notes1.getText(); // notes
                
                // Insert Data
                Statement s = db.mycon().createStatement();
                s.executeUpdate(" INSERT INTO cusorder (co_id,c_iss_date,c_name,c_nic,c_address,c_phone,c_mail,d_method,delno,deldate,pay_mthd,pay_s,d_of_p,product,product_q,product_up,product_lp,o_note,ship_c,d_pc,subtl,shipp,damount,nettl) VALUES ('"+cono+"', '"+cdate+"', '"+c_na+"', '"+c_nic+"', '"+c_add+"', '"+c_ph+"', '"+c_ml+"', '"+c_dmthd+"', '"+c_dno+"', '"+c_ddate+"', '"+c_pmthd+"', '"+c_psta+"', '"+c_pdate+"', '"+description1+"', '"+qty1+"', '"+unit_price1+"', '"+line_amount1+"', '"+notes+"', '"+shipp1+"', '"+disc1+"', '"+sub1+"', '"+damount1+"', '"+samount1+"', '"+net1+"')");
                
            }
            
            JOptionPane.showMessageDialog(null, "Data Saved.");
            
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_save1ActionPerformed

    private void add_tb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_tb3ActionPerformed
        String search = search_id1.getText();
        
        try {
            Statement s = db.mycon().createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM cusorder WHERE cusoid = '"+search+"'");
            
            if (rs.next()) {
                
                CO_id.setText(rs.getString("co_id"));
                CO_Date.setText(rs.getString("c_iss_date"));
                C_name.setText(rs.getString("c_name"));
                C_nic.setText(rs.getString("c_nic"));
                C_address.setText(rs.getString("c_address"));
                C_phone.setText(rs.getString("c_phone"));
                C_mail.setText(rs.getString("c_mail"));
                d_method.setSelectedItem(rs.getString("d_method"));
                d_no.setText(rs.getString("delno"));
                d_ddate.setText(rs.getString("deldate"));
                Payment_method.setSelectedItem(rs.getString("pay_mthd"));
                Payment_s.setSelectedItem(rs.getString("pay_s"));
                d_of_pay.setText(rs.getString("d_of_p"));
                pro_des1.setSelectedItem(rs.getString("product"));
                pro_qty1.setText(rs.getString("product_q"));
                u_price1.setText(rs.getString("product_up"));
                amount1.setText(rs.getString("product_lp"));
                notes1.setText(rs.getString("o_note"));
                ship_c1.setText(rs.getString("ship_c"));
                discount_perc1.setText(rs.getString("d_pc"));
                sub_amt1.setText(rs.getString("subtl"));
                ship_amt1.setText(rs.getString("shipp"));
                discount_amt1.setText(rs.getString("damount"));
                net_amt1.setText(rs.getString("nettl"));
               
                
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_add_tb3ActionPerformed

    private void update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update1ActionPerformed
        // update button code
        
        String id = search_id1.getText();
        
                String cono = CO_id.getText(); // c.o. id
                
                String cdate = CO_Date.getText(); // c.o. issue date
                
                String c_na = C_name.getText(); //customer name
                String c_nic = C_nic.getText(); // customer nic
                String c_add = C_address.getText(); // customer address
                String c_ph = C_phone.getText(); // customer phone
                String c_ml = C_mail.getText(); // customer mail
                
                String c_dmthd = d_method.getSelectedItem().toString(); // delivery method
                String c_dno = d_no.getText(); // delivery no
                String c_ddate = d_ddate.getText(); // delivery date
                
                String c_pmthd = Payment_method.getSelectedItem().toString(); // payment method
                String c_psta = Payment_s.getSelectedItem().toString(); // payment status
                String c_pdate = d_of_pay.getText(); // payment date
                
                String shipp1 = ship_c1.getText(); // shipping
                String disc1 = discount_perc1.getText(); // discount %
                
                String sub1 = sub_amt1.getText(); // sub total
                String samount1 = ship_amt1.getText(); // shipping amount
                String damount1 = discount_amt1.getText(); // discount amount
                String net1 = net_amt1.getText(); // net total
                
                String notes = this.notes1.getText(); // notes
                
                String p_descrip = pro_des1.getSelectedItem().toString(); // sub total
                String pq = pro_qty1.getText(); // tax amount
                String pup = u_price1.getText(); // discount amount
                String plamt = amount1.getText(); // net total
                
                
        try {
            
            Statement s = db.mycon().createStatement();
            s.executeUpdate(" UPDATE cusorder SET co_id = '"+cono+"', c_iss_date = '"+cdate+"', c_name = '"+c_na+"', c_nic = '"+c_nic+"', c_address = '"+c_add+"', c_phone = '"+c_ph+"', c_mail = '"+c_ml+"', d_method = '"+c_dmthd+"', delno = '"+c_dno+"', deldate = '"+c_ddate+"', pay_mthd = '"+c_pmthd+"', pay_s = '"+c_psta+"', d_of_p = '"+c_pdate+"', product = '"+p_descrip+"', product_q = '"+pq+"', product_up = '"+pup+"', product_lp = '"+plamt+"', o_note = '"+notes+"', ship_c = '"+shipp1+"', d_pc = '"+disc1+"', subtl = '"+sub1+"', shipp = '"+samount1+"', damount = '"+damount1+"', nettl = '"+net1+"' WHERE cusoid = '"+id+"'   ");
                        JOptionPane.showMessageDialog(null, "Data Updated.");

            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        tb_load();
    }//GEN-LAST:event_update1ActionPerformed

    private void search_customerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_customerKeyReleased
        try {
            
            String customer = search_customer.getText();
            
            DefaultTableModel dt = (DefaultTableModel) jTable4.getModel();
            dt.setRowCount(0);
            Statement s = db.mycon().createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM cusorder WHERE c_name LIKE '%"+customer+"%'");
            
            while (rs.next()) {                
                
                Vector v = new Vector();
                
                
               v.add(rs.getString(2));
               v.add(rs.getString(3));
               v.add(rs.getString(4));
               v.add(rs.getString(5));
               v.add(rs.getString(6));
               v.add(rs.getString(7));
               v.add(rs.getString(8));
               v.add(rs.getString(11));
               v.add(rs.getString(15));
               v.add(rs.getString(16));
               v.add(rs.getString(25));
               v.add(rs.getString(13));
                
                dt.addRow(v);
                
            }
            
        } catch (Exception e) {
            tb_load();
        }
        
        
    }//GEN-LAST:event_search_customerKeyReleased

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // mouse click get data to text field
        
        int r = jTable4.getSelectedRow();
        String id = jTable4.getValueAt(r, 0).toString();
        String issue_date = jTable4.getValueAt(r, 1).toString();
        String c_name = jTable4.getValueAt(r, 2).toString();
        String c_nic = jTable4.getValueAt(r, 3).toString();
        String c_address = jTable4.getValueAt(r, 4).toString();
        String c_phone = jTable4.getValueAt(r, 5).toString();
        String c_email = jTable4.getValueAt(r, 6).toString();
        String deliveryd = jTable4.getValueAt(r, 7).toString();
        String description = jTable4.getValueAt(r, 8).toString();
        String qty = jTable4.getValueAt(r, 9).toString();
        String n_total = jTable4.getValueAt(r, 10).toString();
        String status = jTable4.getValueAt(r, 11).toString();
        
        
        
        
        jLabel65.setText(description);
        jLabel66.setText(issue_date);
        jLabel69.setText(qty);
    }//GEN-LAST:event_jTable4MouseClicked

    private void discount_amt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discount_amt1KeyReleased
        // TODO add your handling code here:
        tot1();
    }//GEN-LAST:event_discount_amt1KeyReleased

    private void ship_amt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ship_amt1KeyReleased
        // TODO add your handling code here:
        tot1();
    }//GEN-LAST:event_ship_amt1KeyReleased

    private void sub_amt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sub_amt1KeyReleased
        // TODO add your handling code here:
        tot1();
    }//GEN-LAST:event_sub_amt1KeyReleased

    private void C_name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_name1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_name1ActionPerformed

    private void C_phone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_phone1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_phone1ActionPerformed

    private void AddtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddtbActionPerformed
        // Total price cal

        DefaultTableModel dt = (DefaultTableModel) jTable6.getModel();
        Vector v = new Vector();

        v.add(C_pdes.getText()); // description
        v.add(C_returnq.getText()); // qty
        v.add(C_returntp.getText()); // line amount

        dt.addRow(v);
        
        return_tot();
        lines_total();
    }//GEN-LAST:event_AddtbActionPerformed

    private void save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save2ActionPerformed
        // customer order data save in the db
    try {
        DefaultTableModel dt = (DefaultTableModel) jTable6.getModel();
        int rc = dt.getRowCount();

        for (int i = 0; i < rc; i++) {
            String description2 = "";
            String qty2 = "";
            String line_amount2 = "";

            // Check if the value is not null and not empty before parsing
            if (dt.getValueAt(i, 0) != null && !dt.getValueAt(i, 0).toString().isEmpty()) {
                description2 = dt.getValueAt(i, 0).toString(); // get product description
            }

            if (dt.getValueAt(i, 1) != null && !dt.getValueAt(i, 1).toString().isEmpty()) {
                qty2 = dt.getValueAt(i, 1).toString(); // get quantity
            }

            if (dt.getValueAt(i, 2) != null && !dt.getValueAt(i, 2).toString().isEmpty()) {
                line_amount2 = dt.getValueAt(i, 2).toString(); // get line total
            }

            String rno = C_returnid.getText(); // c.o. id
            String rdate = c_rdate.getText(); // c.o. issue date
            String r_na = C_name1.getText(); // customer name
            String r_ph = C_phone1.getText(); // customer phone
            String reason = reasons.getSelectedItem().toString(); // delivery method
            String afor = reasons1.getSelectedItem().toString(); // delivery no
            String subamt = C_returntp2.getText(); // payment method
            String ramount = C_returntp1.getText(); // payment status

            // Insert Data
            Statement s = db.mycon().createStatement();
            String query = "INSERT INTO returngood (roid, rdate, rname, rphone, rpdes, rqty, ramount, reason, askfor, ramt, rsub) " +
                           "VALUES ('" + rno + "', '" + rdate + "', '" + r_na + "', '" + r_ph + "', '" + description2 + "', '" + qty2 + "', '" + line_amount2 + "', '" + reason + "', '" + afor + "', '" + ramount + "', '" + subamt + "')";
            s.executeUpdate(query);
        }

        JOptionPane.showMessageDialog(null, "Data Saved.");
    } catch (HeadlessException | SQLException e) {
        System.out.println(e);
    }
    }//GEN-LAST:event_save2ActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        // selected remove

        try {

            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            int rw = jTable1.getSelectedRow();
            //String id = dt.getValueAt(rw, 0).toString();
            dt.removeRow(rw);

        } catch (Exception e) {
        }

        lines_total();
        tot();
    }//GEN-LAST:event_removeActionPerformed

    private void remove_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_allActionPerformed
        // remove all

        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        dt.setRowCount(0);

        lines_total();
        tot();
    }//GEN-LAST:event_remove_allActionPerformed

    private void C_name1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_name1KeyReleased
        try {
            
            String customer = C_name1.getText();
            
            DefaultTableModel dt = (DefaultTableModel) jTable5.getModel();
            dt.setRowCount(0);
            Statement s = db.mycon().createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM cusorder WHERE c_name LIKE '%"+customer+"%'");
            
            while (rs.next()) {                
                
                Vector v = new Vector();
                
                
               v.add(rs.getString(2));
               v.add(rs.getString(15));
               v.add(rs.getString(16));
               v.add(rs.getString(11));
               v.add(rs.getString(17));
                
                dt.addRow(v);
                
            }
            
        } catch (Exception e) {
            tb_load();
        }
        
        try {
            String name = C_name1.getText();

            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT c_nic,c_phone FROM cusorder WHERE c_name = '"+name+"' ");

            if (rs.next()) {

                C_nic1.setText(rs.getString("c_nic"));
                C_phone1.setText(rs.getString("c_phone"));

            }

        } catch (SQLException e) {
            System.out.println(e);
           
        }
    }//GEN-LAST:event_C_name1KeyReleased

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
         // mouse click get data to text field
        
        int r = jTable5.getSelectedRow();
        String id = jTable5.getValueAt(r, 0).toString();
        String product = jTable5.getValueAt(r, 1).toString();
        String qty = jTable5.getValueAt(r, 2).toString();
        String date = jTable5.getValueAt(r, 3).toString();
        String up = jTable5.getValueAt(r, 4).toString();
        
        C_orderno.setText(id);
        C_orderno1.setText(id);
        C_pdes.setText(product);
        C_returnup.setText(up);
        
    }//GEN-LAST:event_jTable5MouseClicked

    private void C_returntpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_returntpKeyReleased
        // TODO add your handling code here:
        return_tot();
    }//GEN-LAST:event_C_returntpKeyReleased

    private void C_returnqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_returnqKeyReleased
        // TODO add your handling code here:
        return_tot();
    }//GEN-LAST:event_C_returnqKeyReleased

    private void C_returnupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_returnupKeyReleased
        // TODO add your handling code here:
        return_tot();
    }//GEN-LAST:event_C_returnupKeyReleased

    private void C_returntp2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_returntp2KeyReleased
        // TODO add your handling code here:
        lines_total();
    }//GEN-LAST:event_C_returntp2KeyReleased

    private void remove_all1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_all1ActionPerformed
        // remove all

        DefaultTableModel dt = (DefaultTableModel) jTable3.getModel();
        dt.setRowCount(0);

        lines_total();
        tot1();
    }//GEN-LAST:event_remove_all1ActionPerformed

    private void remove1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove1ActionPerformed
        // selected remove

        try {

            DefaultTableModel dt = (DefaultTableModel) jTable3.getModel();
            int rw = jTable3.getSelectedRow();
            //String id = dt.getValueAt(rw, 0).toString();
            dt.removeRow(rw);

        } catch (Exception e) {
        }

        lines_total();
        tot1();
    }//GEN-LAST:event_remove1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Addtb;
    private javax.swing.JTextField CO_Date;
    private javax.swing.JLabel CO_id;
    private javax.swing.JTextArea C_address;
    private javax.swing.JTextField C_mail;
    private javax.swing.JTextField C_name;
    private javax.swing.JTextField C_name1;
    private javax.swing.JTextField C_nic;
    private javax.swing.JTextField C_nic1;
    private javax.swing.JTextField C_orderno;
    private javax.swing.JTextField C_orderno1;
    private javax.swing.JTextField C_pdes;
    private javax.swing.JTextField C_phone;
    private javax.swing.JTextField C_phone1;
    private javax.swing.JLabel C_returnid;
    private javax.swing.JTextField C_returnq;
    private javax.swing.JTextField C_returntp;
    private javax.swing.JTextField C_returntp1;
    private javax.swing.JTextField C_returntp2;
    private javax.swing.JTextField C_returnup;
    private javax.swing.JTextArea MY_address;
    private javax.swing.JTextField MY_com_name;
    private javax.swing.JTextField MY_mail;
    private javax.swing.JTextField MY_name;
    private javax.swing.JTextField MY_phone;
    private javax.swing.JTextField PO_Date;
    private javax.swing.JLabel PO_id;
    private javax.swing.JComboBox<String> Payment_method;
    private javax.swing.JComboBox<String> Payment_s;
    private javax.swing.JTextArea V_address;
    private javax.swing.JTextField V_com_name;
    private javax.swing.JTextField V_mail;
    private javax.swing.JTextField V_name;
    private javax.swing.JTextField V_phone;
    private javax.swing.JButton add_tb;
    private javax.swing.JButton add_tb1;
    private javax.swing.JButton add_tb2;
    private javax.swing.JButton add_tb3;
    private javax.swing.JTextField amount;
    private javax.swing.JTextField amount1;
    private javax.swing.JTextField c_rdate;
    private javax.swing.JTextField d_ddate;
    private javax.swing.JComboBox<String> d_method;
    private javax.swing.JTextField d_no;
    private javax.swing.JTextField d_of_pay;
    private javax.swing.JTextField deli_date;
    private javax.swing.JTextField discount_amt;
    private javax.swing.JTextField discount_amt1;
    private javax.swing.JTextField discount_perc;
    private javax.swing.JTextField discount_perc1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField net_amt;
    private javax.swing.JTextField net_amt1;
    private javax.swing.JTextArea notes;
    private javax.swing.JTextArea notes1;
    private javax.swing.JTextField paid_amt5;
    private javax.swing.JTextField paid_amt6;
    private javax.swing.JTextField paid_amt8;
    private javax.swing.JComboBox<String> payment_status;
    private javax.swing.JLabel pro_code;
    private javax.swing.JComboBox<String> pro_des;
    private javax.swing.JComboBox<String> pro_des1;
    private javax.swing.JTextField pro_qty;
    private javax.swing.JTextField pro_qty1;
    private javax.swing.JComboBox<String> reasons;
    private javax.swing.JComboBox<String> reasons1;
    private javax.swing.JButton remove;
    private javax.swing.JButton remove1;
    private javax.swing.JButton remove_all;
    private javax.swing.JButton remove_all1;
    private javax.swing.JButton save;
    private javax.swing.JButton save1;
    private javax.swing.JButton save2;
    private javax.swing.JTextField search_customer;
    private javax.swing.JTextField search_id;
    private javax.swing.JTextField search_id1;
    private javax.swing.JTextField search_vendor;
    private javax.swing.JTextField ship_amt;
    private javax.swing.JTextField ship_amt1;
    private javax.swing.JTextField ship_c;
    private javax.swing.JTextField ship_c1;
    private javax.swing.JTextField ship_mthd;
    private javax.swing.JTextField ship_term;
    private javax.swing.JTextField sub_amt;
    private javax.swing.JTextField sub_amt1;
    private javax.swing.JTextField tax_amt;
    private javax.swing.JTextField tax_perc;
    private javax.swing.JTextField u_price;
    private javax.swing.JTextField u_price1;
    private javax.swing.JButton update;
    private javax.swing.JButton update1;
    // End of variables declaration//GEN-END:variables
}
