/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.possystem;


public class Home extends javax.swing.JFrame {

    JpanelLoader jpload = new JpanelLoader();
   
    public Home() {
        initComponents();
        this.setExtendedState(Home.MAXIMIZED_BOTH);
        
    }

    Home(String un) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        this();
        jLabel1.setText("Login User : " + un);
    }

    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        home_btn_grp = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Product = new javax.swing.JToggleButton();
        Supplier = new javax.swing.JToggleButton();
        Inquiries = new javax.swing.JToggleButton();
        Invoice = new javax.swing.JToggleButton();
        Returns = new javax.swing.JToggleButton();
        Customer = new javax.swing.JToggleButton();
        Reports = new javax.swing.JToggleButton();
        Stock = new javax.swing.JToggleButton();
        Employee = new javax.swing.JToggleButton();
        returnnote = new javax.swing.JToggleButton();
        panel_load = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Product.setBackground(new java.awt.Color(204, 204, 204));
        home_btn_grp.add(Product);
        Product.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Product.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\possystem\\img\\wheel.png"));
        Product.setText("Product");
        Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductActionPerformed(evt);
            }
        });

        Supplier.setBackground(new java.awt.Color(204, 204, 204));
        home_btn_grp.add(Supplier);
        Supplier.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Supplier.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\possystem\\img\\supplier.png"));
        Supplier.setText("Supplier");
        Supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplierActionPerformed(evt);
            }
        });

        Inquiries.setBackground(new java.awt.Color(204, 204, 204));
        home_btn_grp.add(Inquiries);
        Inquiries.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Inquiries.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\possystem\\img\\pricing.png"));
        Inquiries.setText("Sales");
        Inquiries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InquiriesActionPerformed(evt);
            }
        });

        Invoice.setBackground(new java.awt.Color(204, 204, 204));
        home_btn_grp.add(Invoice);
        Invoice.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Invoice.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\possystem\\img\\invoice.png"));
        Invoice.setText("Invoice");
        Invoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InvoiceActionPerformed(evt);
            }
        });

        Returns.setBackground(new java.awt.Color(204, 204, 204));
        home_btn_grp.add(Returns);
        Returns.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Returns.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\possystem\\img\\received.png"));
        Returns.setText("GReceiveN");
        Returns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnsActionPerformed(evt);
            }
        });

        Customer.setBackground(new java.awt.Color(204, 204, 204));
        home_btn_grp.add(Customer);
        Customer.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Customer.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\possystem\\img\\male.png"));
        Customer.setText("Customer");
        Customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerActionPerformed(evt);
            }
        });

        Reports.setBackground(new java.awt.Color(204, 204, 204));
        home_btn_grp.add(Reports);
        Reports.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Reports.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\possystem\\img\\report.png"));
        Reports.setText("Reports");
        Reports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportsActionPerformed(evt);
            }
        });

        Stock.setBackground(new java.awt.Color(204, 204, 204));
        home_btn_grp.add(Stock);
        Stock.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Stock.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\possystem\\img\\ready-stock.png"));
        Stock.setText("Stock");
        Stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockActionPerformed(evt);
            }
        });

        Employee.setBackground(new java.awt.Color(204, 204, 204));
        home_btn_grp.add(Employee);
        Employee.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        Employee.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\possystem\\img\\participant.png"));
        Employee.setText("Employee");
        Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmployeeActionPerformed(evt);
            }
        });

        returnnote.setBackground(new java.awt.Color(204, 204, 204));
        home_btn_grp.add(returnnote);
        returnnote.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        returnnote.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mycompany\\possystem\\img\\return.png"));
        returnnote.setText("GReturnN");
        returnnote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnnoteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(returnnote, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Employee, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Stock, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reports, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Returns, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inquiries, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Product, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(Product)
                .addGap(18, 18, 18)
                .addComponent(Employee)
                .addGap(18, 18, 18)
                .addComponent(Supplier)
                .addGap(18, 18, 18)
                .addComponent(Customer)
                .addGap(18, 18, 18)
                .addComponent(Inquiries)
                .addGap(18, 18, 18)
                .addComponent(Stock)
                .addGap(18, 18, 18)
                .addComponent(Invoice)
                .addGap(18, 18, 18)
                .addComponent(Returns)
                .addGap(18, 18, 18)
                .addComponent(Reports)
                .addGap(18, 18, 18)
                .addComponent(returnnote)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_load.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panel_loadLayout = new javax.swing.GroupLayout(panel_load);
        panel_load.setLayout(panel_loadLayout);
        panel_loadLayout.setHorizontalGroup(
            panel_loadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1284, Short.MAX_VALUE)
        );
        panel_loadLayout.setVerticalGroup(
            panel_loadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(1220, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem1.setText("jMenuItem1");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("jMenuItem2");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("View");

        jMenuItem3.setText("jMenuItem3");
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("jMenuItem4");
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Report");

        jMenuItem5.setText("jMenuItem5");
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_load, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_load, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerActionPerformed
        // Customer Load
        
        Customer cus = new Customer();
        jpload.jPanelLoader(panel_load, cus);
    }//GEN-LAST:event_CustomerActionPerformed

    private void SupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplierActionPerformed
        // Supplier Load
        
        Supplier sp = new Supplier();
        jpload.jPanelLoader(panel_load, sp);
    }//GEN-LAST:event_SupplierActionPerformed

    private void ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductActionPerformed
        // Product Load
        
        Product pro = new Product();
        jpload.jPanelLoader(panel_load, pro);
    }//GEN-LAST:event_ProductActionPerformed

    private void InquiriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InquiriesActionPerformed
        
        sale sl = new sale();
        
        jpload.jPanelLoader(panel_load, sl);
        
    }//GEN-LAST:event_InquiriesActionPerformed

    private void InvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InvoiceActionPerformed
        
        Invoice inv = new Invoice();
        jpload.jPanelLoader(panel_load, inv);
        
    }//GEN-LAST:event_InvoiceActionPerformed

    private void StockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockActionPerformed
        
        Stock st = new Stock();
        jpload.jPanelLoader(panel_load, st);
        
    }//GEN-LAST:event_StockActionPerformed

    private void EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmployeeActionPerformed
        
        Employee emp = new Employee();
        jpload.jPanelLoader(panel_load, emp);
        
    }//GEN-LAST:event_EmployeeActionPerformed

    private void ReturnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnsActionPerformed
        
        Grn gr = new Grn();
        jpload.jPanelLoader(panel_load, gr);
        
    }//GEN-LAST:event_ReturnsActionPerformed

    private void returnnoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnnoteActionPerformed
        
        Return grn = new Return();
        jpload.jPanelLoader(panel_load, grn);
        
    }//GEN-LAST:event_returnnoteActionPerformed

    private void ReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportsActionPerformed
        
        reports rp = new reports();
        jpload.jPanelLoader(panel_load, rp);
        
    }//GEN-LAST:event_ReportsActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Customer;
    private javax.swing.JToggleButton Employee;
    private javax.swing.JToggleButton Inquiries;
    private javax.swing.JToggleButton Invoice;
    private javax.swing.JToggleButton Product;
    private javax.swing.JToggleButton Reports;
    private javax.swing.JToggleButton Returns;
    private javax.swing.JToggleButton Stock;
    private javax.swing.JToggleButton Supplier;
    private javax.swing.ButtonGroup home_btn_grp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panel_load;
    private javax.swing.JToggleButton returnnote;
    // End of variables declaration//GEN-END:variables
}
