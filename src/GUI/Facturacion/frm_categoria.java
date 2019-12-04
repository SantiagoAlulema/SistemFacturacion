
package GUI.Facturacion;

import Controller.Facturacion.CategoriaJpaController;
import Controller.Facturacion.exceptions.IllegalOrphanException;
import Controller.Facturacion.exceptions.NonexistentEntityException;
import DAO.Facturacion.Categoria;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class frm_categoria extends javax.swing.JPanel {
    CategoriaJpaController controlador = new CategoriaJpaController();
    DefaultTableModel modelo;

    public frm_categoria() {
        initComponents();
        LLenarTablaAuto();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        txtidcategoria = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtdetalle = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel4.setText("Detalle");

        btnguardar.setText("GUARDAR");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnactualizar.setText("ACTUALIZAR");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btneliminar.setText("ELIMINAR");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        jLabel5.setText("CATEGORIA");

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla1);

        jLabel1.setText("ID Categoria");

        jLabel2.setText("Nombre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnactualizar)
                                .addGap(28, 28, 28)
                                .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdetalle)
                            .addComponent(txtnombre)
                            .addComponent(txtidcategoria))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(152, 152, 152))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtidcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneliminar)
                    .addComponent(btnactualizar)
                    .addComponent(btnguardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        try {
        controlador.create(new Categoria(1, txtnombre.getText(), txtdetalle.getText()));
            
            LLenarTablaAuto();
            limpiar();
        } catch (Exception ex) {
            //Logger.getLogger(frm_categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        try {
            Categoria obj = new Categoria(Integer.valueOf(txtidcategoria.getText()), txtnombre.getText(), txtdetalle.getText());
            controlador.edit(obj);
            enableboton();
            limpiar();
        } catch (NonexistentEntityException ex) {
           // Logger.getLogger(frm_categoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            //Logger.getLogger(frm_categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        
        try {
            controlador.destroy(Integer.valueOf(txtidcategoria.getText()));
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(frm_categoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(frm_categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
            LLenarTablaAuto();
            enableboton();
            limpiar();
        
    }//GEN-LAST:event_btneliminarActionPerformed
  public void enableboton(){
        btnguardar.setEnabled(true);
            btnactualizar.setEnabled(false);
            btneliminar.setEnabled(false);
    }
    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
            btnguardar.setEnabled(false);
            btnactualizar.setEnabled(true);
            btneliminar.setEnabled(true);
        try {
            int clic_tabla = 0;
            
            clic_tabla = this.tabla1.rowAtPoint(evt.getPoint());
            
            String string1 = ""+tabla1.getValueAt(clic_tabla, 0);
            String string2 = ""+tabla1.getValueAt(clic_tabla, 1);
            String string3 =""+ tabla1.getValueAt(clic_tabla, 2);
            
            txtidcategoria.setText(String.valueOf(string1));
            txtnombre.setText(string2);
            txtdetalle.setText(string3);
            
            
           
        } catch (Exception ex) {
            //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabla1MouseClicked
public void limpiar(){
    txtnombre.setText("");
    txtidcategoria.setText("");
}
    public void LLenarTablaAuto(){
     
         
    try {
        modelo = (new DefaultTableModel(null, new String [] {"Id Categoria", "Nombre", "Detalle"}){
        Class[] types = new Class [] {
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class,
        };
        boolean[] canEdit = new boolean [] {false,false,false};
    @Override
    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }
    @Override
    public boolean isCellEditable(int rowIndex, int colIndex){
        return canEdit [colIndex];
    }
    

    });
    tabla1.setModel(modelo);
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,e.toString()+"error2");

    }        
      try{
          Object O[]= null;
          System.out.println("formularios.Categoria.LLenarTablaAuto()");
          List<DAO.Facturacion.Categoria> lista = controlador.findCategoriaEntities();
          System.err.println(lista.toString());
          for (int i = 0; i < lista.size(); i++) {
              modelo.addRow(O);
              modelo.setValueAt(lista.get(i).getIdCategoria(), i, 0);
              modelo.setValueAt(lista.get(i).getNombre(), i, 1);
              modelo.setValueAt(lista.get(i).getDetalle(), i, 2);

 
          }
      }catch(Exception ex){
        //  JOptionPane.showMessageDialog(/this/, ex);
      }
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField txtdetalle;
    private javax.swing.JTextField txtidcategoria;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
