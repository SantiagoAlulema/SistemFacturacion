
package PantallasEmergentes.Facturacion;

import Controller.Facturacion.ProductoJpaController;
import DAO.Facturacion.Producto;
import Funciones.Facturacio.FucionLlenarTablas;
import static GUI.Facturacion.VentanaContenido.panelcli;
import java.awt.event.WindowEvent;

public class pantallabuscarproductos extends javax.swing.JDialog {
    ProductoJpaController controlador_producto = new ProductoJpaController();
    Producto productomostrar = new Producto();
    FucionLlenarTablas tt = new FucionLlenarTablas();
    String idProductoRecuperado = "";
     String[] productoFactura;
      int clic_tabla = 0;
    // ProductoJpaController controladorProducto = new ProductoJpaController();
    
    public pantallabuscarproductos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         tt.LLenarTablaProductos(Table_Productos);
         setLocationRelativeTo(null);
         CatidadSpinner.setValue(1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextField_BuscarCliente = new javax.swing.JTextField();
        CatidadSpinner = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_Productos =   Table_Productos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        Button_PrimeroCLT = new javax.swing.JButton();
        Button_AnteriorCLT = new javax.swing.JButton();
        Button_SiguienteCLT = new javax.swing.JButton();
        Button_UltimoCLT = new javax.swing.JButton();
        Label_Paginas = new javax.swing.JLabel();
        Button_UltimoCLT1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Productos");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Buscar Producto");

        TextField_BuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextField_BuscarClienteKeyPressed(evt);
            }
        });

        CatidadSpinner.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Cantidad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(197, 197, 197)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(TextField_BuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CatidadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(TextField_BuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(CatidadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        Table_Productos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Table_Productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Table_Productos.setRowHeight(20);
        Table_Productos.setSelectionBackground(new java.awt.Color(102, 204, 255));
        Table_Productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_ProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Table_Productos);

        Button_PrimeroCLT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Left-12.png"))); // NOI18N
        Button_PrimeroCLT.setText("Primero");

        Button_AnteriorCLT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Left.png"))); // NOI18N
        Button_AnteriorCLT.setText("Anterior ");

        Button_SiguienteCLT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Right.png"))); // NOI18N
        Button_SiguienteCLT.setText("Siguiente");

        Button_UltimoCLT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Right-12.png"))); // NOI18N
        Button_UltimoCLT.setText("Ultimo");

        Label_Paginas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Label_Paginas.setForeground(new java.awt.Color(0, 153, 153));
        Label_Paginas.setText("Page");

        Button_UltimoCLT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Agregar.png"))); // NOI18N
        Button_UltimoCLT1.setText("AGREGAR");
        Button_UltimoCLT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_UltimoCLT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addGap(14, 14, 14))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(257, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(Label_Paginas))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Button_PrimeroCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_AnteriorCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_SiguienteCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_UltimoCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66)
                .addComponent(Button_UltimoCLT1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_UltimoCLT1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(Label_Paginas)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Button_UltimoCLT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Button_SiguienteCLT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Button_AnteriorCLT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Button_PrimeroCLT, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(66, 66, 66))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Table_ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_ProductosMouseClicked
        try {
           
           productoFactura = new String[6];
            

            clic_tabla = this.Table_Productos.rowAtPoint(evt.getPoint());

            productoFactura[0] = ""+Table_Productos.getValueAt(clic_tabla, 0);
            productoFactura[1] = ""+Table_Productos.getValueAt(clic_tabla, 2);
            productoFactura[2] = ""+Table_Productos.getValueAt(clic_tabla, 4);
            productoFactura[3] = ""+Table_Productos.getValueAt(clic_tabla, 8);
          
         
            // String string2 = ""+Table_Clientes.getValueAt(clic_tabla, 1);
            //   String string3 =""+ Table_Clientes.getValueAt(clic_tabla, 2);
            //   String string4 = ""+Table_Clientes.getValueAt(clic_tabla, 3);
            //   String string5 = ""+Table_Clientes.getValueAt(clic_tabla, 4);
            //    String string6 = ""+Table_Clientes.getValueAt(clic_tabla, 5);
            //    String string7 = ""+Table_Clientes.getValueAt(clic_tabla, 6);
            //    String string8 = ""+Table_Clientes.getValueAt(clic_tabla, 7);

          //  idProductoRecuperado = string1;

        } catch (Exception ex) {
            //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Table_ProductosMouseClicked

    private void Button_UltimoCLT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_UltimoCLT1ActionPerformed
        productoFactura[4] = CatidadSpinner.getValue().toString();
        panelcli.llenartablaProductos(productoFactura);
        CatidadSpinner.setValue(1);
       // this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_Button_UltimoCLT1ActionPerformed

    private void TextField_BuscarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_BuscarClienteKeyPressed
         if (evt.getKeyCode() == 10) {
            if(controlador_producto.BuscaXCodigoProducto(TextField_BuscarCliente.getText()).size() > 0){
                System.out.println("aqui entra a codigo producto");
                tt.LLenarTablaProductosXCodProducto(Table_Productos,TextField_BuscarCliente.getText());
            }
            if(controlador_producto.BuscaXNombre(TextField_BuscarCliente.getText()).size() > 0){
                System.out.println("aqui entra Xnombre");
                tt.LLenarTablaProductosXNombre(Table_Productos,TextField_BuscarCliente.getText());
            }
 
       }
    }//GEN-LAST:event_TextField_BuscarClienteKeyPressed

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
            java.util.logging.Logger.getLogger(pantallabuscarproductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pantallabuscarproductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pantallabuscarproductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pantallabuscarproductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                pantallabuscarproductos dialog = new pantallabuscarproductos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_AnteriorCLT;
    private javax.swing.JButton Button_PrimeroCLT;
    private javax.swing.JButton Button_SiguienteCLT;
    private javax.swing.JButton Button_UltimoCLT;
    private javax.swing.JButton Button_UltimoCLT1;
    private javax.swing.JSpinner CatidadSpinner;
    private javax.swing.JLabel Label_Paginas;
    private javax.swing.JTable Table_Productos;
    private javax.swing.JTextField TextField_BuscarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
