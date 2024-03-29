/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PantallasEmergentes.Facturacion;

import Controller.Facturacion.CedulaJpaController;
import Controller.Facturacion.PclienteJpaController;
import Controller.Facturacion.RucJpaController;
import Controller.Facturacion.TiporucJpaController;
import DAO.Facturacion.Cedula;
import DAO.Facturacion.Pcliente;
import DAO.Facturacion.Ruc;
import Funciones.Facturacio.FucionLlenarTablas;
import Funciones.Facturacio.ValidaciondeTipado;
import GUI.Facturacion.Facturacion;
import static GUI.Facturacion.VentanaContenido.panelcli;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;


public class PantallaBuscarClienteDialog extends javax.swing.JDialog {
   
 Pcliente nuevoCliente ;
    String numeroDocumento;
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = new GregorianCalendar();
    String fecha = "";
    PclienteJpaController Controlador_Cliente = new PclienteJpaController();
    TiporucJpaController controlador_TipoRuc =  new TiporucJpaController();
    RucJpaController controlador_Ruc = new RucJpaController();
    CedulaJpaController controlador_cedula = new CedulaJpaController();
    FucionLlenarTablas tt = new FucionLlenarTablas();
    ValidaciondeTipado tipos = new ValidaciondeTipado();
    Pcliente clientemostrar = new Pcliente();
    String idClienteRecuperado = "";
    Facturacion aa = new Facturacion();
    /**
     * Creates new form PantallaBuscarClienteDialog
     */
    public PantallaBuscarClienteDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tt.llenarTabla(Table_Clientes);
        setLocationRelativeTo(null);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_Clientes =   Table_Clientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        Button_AnteriorCLT = new javax.swing.JButton();
        Button_SiguienteCLT = new javax.swing.JButton();
        Button_PrimeroCLT = new javax.swing.JButton();
        Button_UltimoCLT = new javax.swing.JButton();
        Label_Paginas = new javax.swing.JLabel();
        Button_UltimoCLT1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextField_BuscarCliente = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        Table_Clientes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Table_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Table_Clientes.setRowHeight(20);
        Table_Clientes.setSelectionBackground(new java.awt.Color(102, 204, 255));
        Table_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_ClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Table_Clientes);

        Button_AnteriorCLT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Left.png"))); // NOI18N
        Button_AnteriorCLT.setText("Anterior ");

        Button_SiguienteCLT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Right.png"))); // NOI18N
        Button_SiguienteCLT.setText("Siguiente");

        Button_PrimeroCLT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Left-12.png"))); // NOI18N
        Button_PrimeroCLT.setText("Primero");

        Button_UltimoCLT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Right-12.png"))); // NOI18N
        Button_UltimoCLT.setText("Ultimo");

        Label_Paginas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Label_Paginas.setForeground(new java.awt.Color(0, 153, 153));
        Label_Paginas.setText("Page");

        Button_UltimoCLT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Agregar.png"))); // NOI18N
        Button_UltimoCLT1.setText("ACEPTAR");
        Button_UltimoCLT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_UltimoCLT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2)
                .addGap(10, 10, 10))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(209, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(Label_Paginas)
                        .addGap(217, 217, 217))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(Button_PrimeroCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_AnteriorCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_SiguienteCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_UltimoCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addComponent(Button_UltimoCLT1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label_Paginas)
                .addGap(4, 4, 4)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Button_UltimoCLT)
                        .addComponent(Button_UltimoCLT1))
                    .addComponent(Button_SiguienteCLT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Button_AnteriorCLT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Button_PrimeroCLT, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Clientes");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Buscar Cliente");

        TextField_BuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextField_BuscarClienteKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(127, 127, 127)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(TextField_BuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(348, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(TextField_BuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Table_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_ClientesMouseClicked
        try {
            int clic_tabla = 0;

            clic_tabla = this.Table_Clientes.rowAtPoint(evt.getPoint());

            String string1 = ""+Table_Clientes.getValueAt(clic_tabla, 0);
    

            idClienteRecuperado = string1;

        } catch (Exception ex) {
            //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Table_ClientesMouseClicked

    private void Button_UltimoCLT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_UltimoCLT1ActionPerformed
        panelcli.llenarDatos(idClienteRecuperado);
        this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_Button_UltimoCLT1ActionPerformed

    private void TextField_BuscarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_BuscarClienteKeyPressed
        if (evt.getKeyCode() == 10) {
         //   if (combo_TipoBusqueda.getSelectedItem().equals("Documento")) {
                if (tipos.isNumeric(TextField_BuscarCliente.getText()) == true){
                    if(controlador_cedula.findCedula(TextField_BuscarCliente.getText()) != null){
                        Cedula cedu =controlador_cedula.findCedula(TextField_BuscarCliente.getText()) ;
                        System.out.println("cedu " + cedu.getIdPCliente().getIdPCliente());
                        tt.llenarTablaDocumento(Table_Clientes, cedu.getIdPCliente().getIdPCliente());
                    }
                    if(controlador_Ruc.findRuc(TextField_BuscarCliente.getText()) != null){
                       Ruc idRuc =controlador_Ruc.findRuc(TextField_BuscarCliente.getText()) ;
                        tt.llenarTablaDocumento(Table_Clientes, idRuc.getIdPCliente().getIdPCliente());
                    }
                }else{
                  tt.llenarTablaApellido(Table_Clientes, TextField_BuscarCliente.getText());
                }

            //}
          
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
            java.util.logging.Logger.getLogger(PantallaBuscarClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaBuscarClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaBuscarClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaBuscarClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PantallaBuscarClienteDialog dialog = new PantallaBuscarClienteDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Label_Paginas;
    private javax.swing.JTable Table_Clientes;
    private javax.swing.JTextField TextField_BuscarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
