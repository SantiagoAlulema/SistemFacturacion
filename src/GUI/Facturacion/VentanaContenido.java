/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Facturacion;

import Iconos.AWTUtilities;
import java.awt.Image;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class VentanaContenido extends javax.swing.JFrame {
    
 public static Facturacion panelcli;
    /**
     * Creates new form VentanaContenido
     */

    public VentanaContenido() {
        initComponents();
      this.setExtendedState(MAXIMIZED_BOTH);
      
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Button_Ventas = new javax.swing.JButton();
        Button_Cliente = new javax.swing.JButton();
        Button_Poductos = new javax.swing.JButton();
        Button_Cat_Dpt = new javax.swing.JButton();
        Button_Compras = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnbodega = new javax.swing.JButton();
        Contenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(53, 33, 89));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/img_AppWare.png"))); // NOI18N

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sistema de Facturacion");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(85, 55, 118));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        Button_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/compras.png"))); // NOI18N
        Button_Ventas.setText("Ventas");
        Button_Ventas.setToolTipText("");
        Button_Ventas.setBorder(null);
        Button_Ventas.setBorderPainted(false);
        Button_Ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_VentasActionPerformed(evt);
            }
        });

        Button_Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Clientes.png"))); // NOI18N
        Button_Cliente.setText("Clientes");
        Button_Cliente.setToolTipText("");
        Button_Cliente.setBorder(null);
        Button_Cliente.setBorderPainted(false);
        Button_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ClienteActionPerformed(evt);
            }
        });

        Button_Poductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ordering.png"))); // NOI18N
        Button_Poductos.setText("Productos");
        Button_Poductos.setToolTipText("");
        Button_Poductos.setBorder(null);
        Button_Poductos.setBorderPainted(false);
        Button_Poductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_PoductosActionPerformed(evt);
            }
        });

        Button_Cat_Dpt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Etiqueta.png"))); // NOI18N
        Button_Cat_Dpt.setText("Cat Dpt.");
        Button_Cat_Dpt.setToolTipText("");
        Button_Cat_Dpt.setBorder(null);
        Button_Cat_Dpt.setBorderPainted(false);
        Button_Cat_Dpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Cat_DptActionPerformed(evt);
            }
        });

        Button_Compras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Carrito-de-compras.png"))); // NOI18N
        Button_Compras.setText(" Compras");
        Button_Compras.setToolTipText("");
        Button_Compras.setBorder(null);
        Button_Compras.setBorderPainted(false);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Process-Accept.png"))); // NOI18N
        jButton6.setText(" Config");
        jButton6.setToolTipText("");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);

        btnbodega.setText("BODEGA");
        btnbodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbodegaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Button_Ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Poductos, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Cat_Dpt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Compras, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnbodega, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(332, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Button_Ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Poductos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Cat_Dpt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Compras, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnbodega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Contenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_VentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_VentasActionPerformed
       //verificar();
      // respustaHuesosComidos=0;
        panelcli= new Facturacion();
    //   System.gc();
       
      
       panelcli.setSize(Contenedor.getHeight()+800, Contenedor.getWidth());
 
        panelcli.setLocation(0,0);
        this.getContentPane().add(panelcli); 
       Contenedor.setFocusable(true);
        Contenedor.requestFocusInWindow();
       Contenedor.removeAll();
        Contenedor.add(panelcli);
        Contenedor.revalidate();
        Contenedor.repaint();
    }//GEN-LAST:event_Button_VentasActionPerformed

    private void Button_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ClienteActionPerformed
       PanelClietes panelcli= new PanelClietes();
    //   System.gc();
       
      
       panelcli.setSize(Contenedor.getHeight()+800, Contenedor.getWidth());
 
        panelcli.setLocation(0,0);
        this.getContentPane().add(panelcli); 
       Contenedor.setFocusable(true);
        Contenedor.requestFocusInWindow();
       Contenedor.removeAll();
        Contenedor.add(panelcli);
        Contenedor.revalidate();
        Contenedor.repaint();
    }//GEN-LAST:event_Button_ClienteActionPerformed

    private void Button_PoductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_PoductosActionPerformed
        PanelProductos panelpro= new PanelProductos();
    //   System.gc();
       
      
       panelpro.setSize(Contenedor.getHeight()+800, Contenedor.getWidth());
 
        panelpro.setLocation(0,0);
        this.getContentPane().add(panelpro); 
       Contenedor.setFocusable(true);
        Contenedor.requestFocusInWindow();
       Contenedor.removeAll();
        Contenedor.add(panelpro);
        Contenedor.revalidate();
        Contenedor.repaint();
    }//GEN-LAST:event_Button_PoductosActionPerformed

    private void Button_Cat_DptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Cat_DptActionPerformed
        PanelCategoria panelcat= new PanelCategoria();
    //   System.gc();
       
      
       panelcat.setSize(Contenedor.getHeight()+800, Contenedor.getWidth());
 
        panelcat.setLocation(0,0);
        this.getContentPane().add(panelcat); 
       Contenedor.setFocusable(true);
        Contenedor.requestFocusInWindow();
       Contenedor.removeAll();
        Contenedor.add(panelcat);
        Contenedor.revalidate();
        Contenedor.repaint();
    }//GEN-LAST:event_Button_Cat_DptActionPerformed

    private void btnbodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbodegaActionPerformed
        PanelBodega panelcat= new PanelBodega();
    //   System.gc();
       
      
       panelcat.setSize(Contenedor.getHeight()+800, Contenedor.getWidth());
 
        panelcat.setLocation(0,0);
        this.getContentPane().add(panelcat); 
       Contenedor.setFocusable(true);
        Contenedor.requestFocusInWindow();
       Contenedor.removeAll();
        Contenedor.add(panelcat);
        Contenedor.revalidate();
        Contenedor.repaint();
    }//GEN-LAST:event_btnbodegaActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaContenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaContenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaContenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaContenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaContenido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Cat_Dpt;
    private javax.swing.JButton Button_Cliente;
    private javax.swing.JButton Button_Compras;
    private javax.swing.JButton Button_Poductos;
    private javax.swing.JButton Button_Ventas;
    private javax.swing.JPanel Contenedor;
    private javax.swing.JButton btnbodega;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    // End of variables declaration//GEN-END:variables
}
