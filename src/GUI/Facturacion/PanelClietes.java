/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Facturacion;

import Controller.Facturacion.CedulaJpaController;
import Controller.Facturacion.PclienteJpaController;
import Controller.Facturacion.RolJpaController;
import Controller.Facturacion.RucJpaController;
import Controller.Facturacion.TiendaJpaController;
import Controller.Facturacion.TiporucJpaController;
import DAO.Facturacion.Cedula;
import DAO.Facturacion.Pcliente;
import DAO.Facturacion.Rol;
import DAO.Facturacion.Ruc;
import DAO.Facturacion.Tienda;
import DAO.Facturacion.Tiporuc;
import Funciones.Facturacio.FucionLlenarTablas;
import Funciones.Facturacio.ValidaciondeTipado;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago
 */
public class PanelClietes extends javax.swing.JPanel {
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
    /**
     * Creates new form PanelClietes
     */

    public PanelClietes() {
        initComponents();
        
         fecha= fecha + c1.get(Calendar.YEAR)+"/"  +  (c1.get(Calendar.MONTH)+1)+"/" +c1.get(Calendar.DATE);
         txt_FechaIngreso.setText(fecha);

         tt.llenarTabla(Table_Clientes);
         
       // System.err.println(c1.get(Calendar.DATE));      
      //  System.err.println(c1.get(Calendar.MONTH));  
      //  System.err.println(c1.get(Calendar.YEAR));  

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextField_BuscarCliente = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Label_NombreCliente = new javax.swing.JLabel();
        txt_PrimerNombre = new javax.swing.JTextField();
        Label_ApellidoCliente = new javax.swing.JLabel();
        txt_PrimerApellido = new javax.swing.JTextField();
        Label_DireccionCliente = new javax.swing.JLabel();
        txt_Direccion = new javax.swing.JTextField();
        Label_TelefonoCliente = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        Label_Pago = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        Button_GuardarCliente = new javax.swing.JButton();
        Button_CancelarCLT = new javax.swing.JButton();
        Label_NombreCliente16 = new javax.swing.JLabel();
        txt_SegundoNombre = new javax.swing.JTextField();
        Label_ApellidoCliente16 = new javax.swing.JLabel();
        txt_SegundoApellido = new javax.swing.JTextField();
        Label_NombreCliente17 = new javax.swing.JLabel();
        txtDocumentoNumer = new javax.swing.JTextField();
        Label_Pago31 = new javax.swing.JLabel();
        txt_FechaIngreso = new javax.swing.JTextField();
        Comb_TipoDocu = new javax.swing.JComboBox<>();
        comb_TipoRuc = new javax.swing.JComboBox<>();
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
        Button_Factura = new javax.swing.JButton();

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
                .addGap(414, 414, 414)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TextField_BuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Ingreso de Clientes");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Llenar campos del cliente");
        jLabel3.setToolTipText("");

        Label_NombreCliente.setText("Primer nombre *");
        Label_NombreCliente.setToolTipText("");

        Label_ApellidoCliente.setText("Primer Apellido *");

        Label_DireccionCliente.setText("Dirección");
        Label_DireccionCliente.setToolTipText("");

        Label_TelefonoCliente.setText("Telefono");

        Label_Pago.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_Pago.setText("Email");

        Button_GuardarCliente.setText("Guardar");
        Button_GuardarCliente.setBorder(null);
        Button_GuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_GuardarClienteActionPerformed(evt);
            }
        });

        Button_CancelarCLT.setText("Cancelar");
        Button_CancelarCLT.setBorder(null);
        Button_CancelarCLT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_CancelarCLTActionPerformed(evt);
            }
        });

        Label_NombreCliente16.setText("Segundo nombre *");
        Label_NombreCliente16.setToolTipText("");

        Label_ApellidoCliente16.setText("Segundo Apellido *");

        Label_NombreCliente17.setText("Ingrese el numero");
        Label_NombreCliente17.setToolTipText("");

        Label_Pago31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_Pago31.setText("Fecha Ingreso (yy/mm/dd)");

        txt_FechaIngreso.setEnabled(false);

        Comb_TipoDocu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cedula", "RUC" }));
        Comb_TipoDocu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Comb_TipoDocuItemStateChanged(evt);
            }
        });

        comb_TipoRuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ruc Natural", "Ruc Empresarial" }));
        comb_TipoRuc.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(txtDocumentoNumer, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(comb_TipoRuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_PrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Label_DireccionCliente)
                                        .addComponent(txt_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Label_Pago)
                                        .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Label_Pago31)
                                        .addComponent(Label_TelefonoCliente)
                                        .addComponent(txt_SegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_FechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel3)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_PrimerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Label_ApellidoCliente)
                                        .addComponent(Label_NombreCliente)
                                        .addComponent(Label_NombreCliente17))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Label_NombreCliente16)
                                        .addComponent(Label_ApellidoCliente16)
                                        .addComponent(txt_SegundoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(Comb_TipoDocu, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(199, Short.MAX_VALUE))))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(Button_GuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(Button_CancelarCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Comb_TipoDocu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(Label_NombreCliente17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDocumentoNumer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comb_TipoRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_NombreCliente)
                    .addComponent(Label_NombreCliente16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_PrimerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SegundoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_ApellidoCliente)
                    .addComponent(Label_ApellidoCliente16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_PrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_DireccionCliente)
                    .addComponent(Label_TelefonoCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_Pago)
                    .addComponent(Label_Pago31))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_FechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_CancelarCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_GuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

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

        Button_Factura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Imprimir.png"))); // NOI18N
        Button_Factura.setText("Factura");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2)
                .addGap(10, 10, 10))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(Button_PrimeroCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_AnteriorCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_SiguienteCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_UltimoCLT, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                        .addComponent(Button_Factura))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(Label_Paginas)))
                .addGap(28, 28, 28))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label_Paginas)
                .addGap(4, 4, 4)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Button_UltimoCLT)
                        .addComponent(Button_Factura))
                    .addComponent(Button_SiguienteCLT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Button_AnteriorCLT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Button_PrimeroCLT, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Comb_TipoDocuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Comb_TipoDocuItemStateChanged
       
        switch(evt.getItem().toString()){
                case "Cedula":
                    comb_TipoRuc.setEnabled(false);
                    break;
                case "RUC":
                    comb_TipoRuc.setEnabled(true);
                    break;
        }  
    }//GEN-LAST:event_Comb_TipoDocuItemStateChanged

    private void Button_GuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_GuardarClienteActionPerformed
        java.util.Date d = new java.util.Date();  
        java.sql.Date date2 = new java.sql.Date(d.getTime());
        nuevoCliente = new Pcliente(txt_PrimerNombre.getText(), txt_SegundoNombre.getText(), txt_PrimerApellido.getText(), txt_SegundoApellido.getText(), txt_telefono.getText(), txt_Direccion.getText(), "Ac", c1.getTime(), txt_Email.getText());
      if((Comb_TipoDocu.getSelectedItem().toString()).endsWith("Cedula")){
             try {
             Controlador_Cliente.create(nuevoCliente);
            Cedula cedulaNueva = new Cedula();
            cedulaNueva.setCedula(txtDocumentoNumer.getText());
            cedulaNueva.setIdPCliente(nuevoCliente);
            controlador_cedula.create(cedulaNueva);
           } catch (Exception ex) {
               Logger.getLogger(PanelClietes.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else{
            Tiporuc tipoRuc;
            if((comb_TipoRuc.getSelectedItem().toString()).equals("Ruc Empresarial")){
                tipoRuc = controlador_TipoRuc.findTiporuc(1);
            }else{
                tipoRuc = controlador_TipoRuc.findTiporuc(2);
            }
            Ruc ruc = new Ruc();
            ruc.setIdPCliente(nuevoCliente);
            ruc.setIdTipoRuc(tipoRuc);
            ruc.setRuc(txtDocumentoNumer.getText());
            
           try {
               Controlador_Cliente.create(nuevoCliente);
               controlador_Ruc.create(ruc);
           } catch (Exception ex) {
               Logger.getLogger(PanelClietes.class.getName()).log(Level.SEVERE, null, ex);
           }
            
       }
        tt.llenarTabla(Table_Clientes);
        vaciarTexfield();
    }//GEN-LAST:event_Button_GuardarClienteActionPerformed

    private void Button_CancelarCLTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_CancelarCLTActionPerformed
       vaciarTexfield();
    }//GEN-LAST:event_Button_CancelarCLTActionPerformed

    private void TextField_BuscarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_BuscarClienteKeyPressed
        if (evt.getKeyCode() == 10) {
            if (tipos.isNumeric(TextField_BuscarCliente.getText()) == true){
                if(controlador_cedula.findCedula(TextField_BuscarCliente.getText()) != null){
                    Cedula cedu =controlador_cedula.findCedula(TextField_BuscarCliente.getText()) ;
                    tt.llenarTablaDocumento(Table_Clientes, cedu.getIdPCliente().getIdPCliente());
                }if(controlador_Ruc.findRuc(TextField_BuscarCliente.getText()) != null){
                    Ruc idRuc =controlador_Ruc.findRuc(TextField_BuscarCliente.getText()) ;
                    tt.llenarTablaDocumento(Table_Clientes, idRuc.getIdPCliente().getIdPCliente());
                } 
               }else{
                tt.llenarTablaApellido(Table_Clientes, TextField_BuscarCliente.getText());
            }
        }     
    }//GEN-LAST:event_TextField_BuscarClienteKeyPressed
    
    public void vaciarTexfield(){
        txtDocumentoNumer.setText("");
        txt_PrimerApellido.setText("");
        txt_Direccion.setText("");
        txt_Email.setText("");
        txt_PrimerNombre.setText("");
        txt_SegundoNombre.setText("");
        txt_SegundoApellido.setText("");
        txt_telefono.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_AnteriorCLT;
    private javax.swing.JButton Button_CancelarCLT;
    private javax.swing.JButton Button_Factura;
    private javax.swing.JButton Button_GuardarCliente;
    private javax.swing.JButton Button_PrimeroCLT;
    private javax.swing.JButton Button_SiguienteCLT;
    private javax.swing.JButton Button_UltimoCLT;
    private javax.swing.JComboBox<String> Comb_TipoDocu;
    private javax.swing.JLabel Label_ApellidoCliente;
    private javax.swing.JLabel Label_ApellidoCliente16;
    private javax.swing.JLabel Label_DireccionCliente;
    private javax.swing.JLabel Label_NombreCliente;
    private javax.swing.JLabel Label_NombreCliente16;
    private javax.swing.JLabel Label_NombreCliente17;
    private javax.swing.JLabel Label_Paginas;
    private javax.swing.JLabel Label_Pago;
    private javax.swing.JLabel Label_Pago31;
    private javax.swing.JLabel Label_TelefonoCliente;
    private javax.swing.JTable Table_Clientes;
    private javax.swing.JTextField TextField_BuscarCliente;
    private javax.swing.JComboBox<String> comb_TipoRuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtDocumentoNumer;
    private javax.swing.JTextField txt_Direccion;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_FechaIngreso;
    private javax.swing.JTextField txt_PrimerApellido;
    private javax.swing.JTextField txt_PrimerNombre;
    private javax.swing.JTextField txt_SegundoApellido;
    private javax.swing.JTextField txt_SegundoNombre;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
