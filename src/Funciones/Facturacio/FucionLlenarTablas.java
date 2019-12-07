/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones.Facturacio;

import Controller.Facturacion.CedulaJpaController;
import Controller.Facturacion.PclienteJpaController;
import Controller.Facturacion.ProductoJpaController;
import Controller.Facturacion.RucJpaController;
import Controller.Facturacion.TiporucJpaController;
import DAO.Facturacion.Bodega;
import DAO.Facturacion.Cedula;
import DAO.Facturacion.Pcliente;
import DAO.Facturacion.Producto;
import DAO.Facturacion.Ruc;
import java.awt.Button;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Santiago
 */
public class FucionLlenarTablas {
     DefaultTableModel modelo1;
     PclienteJpaController controladorCliente = new PclienteJpaController();
     RucJpaController controladorRuc = new RucJpaController();
     TiporucJpaController ControladorTipoRuc = new TiporucJpaController();
     CedulaJpaController controladorCedula = new CedulaJpaController();
     ProductoJpaController controladorProductor = new ProductoJpaController();
    public void llenarTabla(JTable tabla){
        System.out.println("Funciones.Facturacio.FucionLlenarTablas.llenarTabla()");
          try {
                modelo1 = (new DefaultTableModel(null, new String [] {"Identificacion","Tipo Identificacion","Nombres", "Apellidos","Telefono","Direccion","Fecha Ingreso", "Email"}){
                Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.sql.Date.class,
                java.lang.String.class,
                    Button.class,
                Button.class
                };
                boolean[] canEdit = new boolean [] {false,false,false,false,false,false,false,false};
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex){
                return canEdit [colIndex];
            }


            });
            tabla.setModel(modelo1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.toString()+"error2");

            }        
              try{
                  Object O[]= null;
                  List<Pcliente> lista =controladorCliente.findPclienteEntities();

                  for (int i = 0; i < lista.size(); i++) {
                      modelo1.addRow(O);
                
                      List<Cedula> celi = lista.get(i).getCedulaList();
                      List<Ruc> ruclis = lista.get(i).getRucList();
                      
                      if (celi.size() != 0) {
                          modelo1.setValueAt(celi.get(0).getCedula(), i, 0);
                          modelo1.setValueAt("Cedula", i, 1);
                       }else{
                          modelo1.setValueAt(ruclis.get(0).getRuc(), i, 0);
                          modelo1.setValueAt(ruclis.get(0).getIdTipoRuc().getNombre()    , i, 1);
                      }
                      
                      modelo1.setValueAt(lista.get(i).getPrimerNombre() + " " +lista.get(i).getSegundoNombre() , i, 2);
                      modelo1.setValueAt(lista.get(i).getPrimerApellido() + " " +lista.get(i).getSegundoApellido() , i, 3);
                      modelo1.setValueAt(lista.get(i).getTelefono() , i, 4);
                      modelo1.setValueAt(lista.get(i).getDireccion() , i, 5);
                      modelo1.setValueAt(lista.get(i).getFechaIngreso(), i, 6);
                      modelo1.setValueAt(lista.get(i).getEmail(), i, 7);
                  

                  }
                  tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
              }catch(Exception ex){
                //  JOptionPane.showMessageDialog(/*this*/, ex);
                  System.out.println("Error  " +ex);
              }

     
        
        
    }
    
     public void llenarTablaDocumento(JTable tabla, int id){
        System.out.println("Funciones.Facturacio.FucionLlenarTablas.llenarTabla()");
          try {
                modelo1 = (new DefaultTableModel(null, new String [] {"Identificacion","Tipo Identificacion","Nombres", "Apellidos","Telefono","Direccion","Fecha Ingreso", "Email"}){
                Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.sql.Date.class,
                java.lang.String.class,
                    Button.class,
                Button.class
                };
                boolean[] canEdit = new boolean [] {false,false,false,false,false,false,false,false};
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex){
                return canEdit [colIndex];
            }


            });
            tabla.setModel(modelo1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.toString()+"error2");

            }        
              try{
                  Object O[]= null;
                  Pcliente lista =controladorCliente.findPcliente(id);
                
                

                //  for (int i = 0; i < lista.size(); i++) {
                      modelo1.addRow(O);
                
                      List<Cedula> celi = lista.getCedulaList();
                      List<Ruc> ruclis = lista.getRucList();
                      
                      if (celi.size() != 0) {
                          modelo1.setValueAt(celi.get(0).getCedula(), 0, 0);
                          modelo1.setValueAt("Cedula", 0, 1);
                       }else{
                          modelo1.setValueAt(ruclis.get(0).getRuc(), 0, 0);
                          modelo1.setValueAt(ruclis.get(0).getIdTipoRuc().getNombre()    , 0, 1);
                      }
                      
                      modelo1.setValueAt(lista.getPrimerNombre() + " " +lista.getSegundoNombre() , 0, 2);
                      modelo1.setValueAt(lista.getPrimerApellido() + " " +lista.getSegundoApellido() , 0, 3);
                      modelo1.setValueAt(lista.getTelefono() , 0, 4);
                      modelo1.setValueAt(lista.getDireccion() , 0, 5);
                      modelo1.setValueAt(lista.getFechaIngreso(), 0, 6);
                      modelo1.setValueAt(lista.getEmail(), 0, 7);
                  

                //  }
                  tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
              }catch(Exception ex){
                //  JOptionPane.showMessageDialog(/*this*/, ex);
                  System.out.println("Error  " +ex);
              }
  
    }
    public void llenarTablaApellido(JTable tabla, String id){
        System.out.println("Funciones.Facturacio.FucionLlenarTablas.llenarTabla()");
          try {
                modelo1 = (new DefaultTableModel(null, new String [] {"Identificacion","Tipo Identificacion","Nombres", "Apellidos","Telefono","Direccion","Fecha Ingreso", "Email"}){
                Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.sql.Date.class,
                java.lang.String.class,
                    Button.class,
                Button.class
                };
                boolean[] canEdit = new boolean [] {false,false,false,false,false,false,false,false};
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex){
                return canEdit [colIndex];
            }


            });
            tabla.setModel(modelo1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.toString()+"error2");

            }        
             try{
                  Object O[]= null;
                  List<Pcliente> lista =controladorCliente.buscarApellidos(id);
                  System.out.println("email " + lista.size());

                  for (int i = 0; i < lista.size(); i++) {
                      modelo1.addRow(O);
                
                      List<Cedula> celi = lista.get(i).getCedulaList();
                      List<Ruc> ruclis = lista.get(i).getRucList();
                      
                      if (celi.size() != 0) {
                          modelo1.setValueAt(celi.get(0).getCedula(), i, 0);
                          modelo1.setValueAt("Cedula", i, 1);
                       }else{
                          modelo1.setValueAt(ruclis.get(0).getRuc(), i, 0);
                          modelo1.setValueAt(ruclis.get(0).getIdTipoRuc().getNombre()    , i, 1);
                      }
                      
                      modelo1.setValueAt(lista.get(i).getPrimerNombre() + " " +lista.get(i).getSegundoNombre() , i, 2);
                      modelo1.setValueAt(lista.get(i).getPrimerApellido() + " " +lista.get(i).getSegundoApellido() , i, 3);
                      modelo1.setValueAt(lista.get(i).getTelefono() , i, 4);
                      modelo1.setValueAt(lista.get(i).getDireccion() , i, 5);
                      modelo1.setValueAt(lista.get(i).getFechaIngreso(), i, 6);
                      modelo1.setValueAt(lista.get(i).getEmail(), i, 7);
                  

                  }
                  tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
              }catch(Exception ex){
                //  JOptionPane.showMessageDialog(/*this*/, ex);
                  System.out.println("Error  " +ex);
              }
  
    }
    
    public void LLenarTablaProductos(JTable tabla){
      try {
                modelo1 = (new DefaultTableModel(null, new String [] {"Id Producto","Cod Barras","Cod Produc", "Nombre","Detalle","Stock","Estado", "Precio Compra","Precio Venta","Bodega","Provedor","Categoria"}){
                Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                    
                };
                boolean[] canEdit = new boolean [] {false,false,false,false,false,false,false,false,false,false,false,false};
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex){
                return canEdit [colIndex];
            }


            });
            tabla.setModel(modelo1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.toString()+"error2");

            }        
             try{
                  Object O[]= null;
                  List<Producto> lista =controladorProductor.findProductoEntities();
               //   System.out.println("email " + lista.size());

                  for (int i = 0; i < lista.size(); i++) {
                      modelo1.addRow(O);
                                     
                      modelo1.setValueAt(lista.get(i).getIdProducto(), i, 0);
                      modelo1.setValueAt(lista.get(i).getCodBarras(), i, 1);
                      modelo1.setValueAt(lista.get(i).getCodProducto(), i, 2);
                      modelo1.setValueAt(lista.get(i).getNombre(), i, 3);
                      modelo1.setValueAt(lista.get(i).getDetalle(), i, 4);
                      modelo1.setValueAt(lista.get(i).getStock(), i, 5);
                      modelo1.setValueAt(lista.get(i).getEstado(), i, 6);
                      modelo1.setValueAt(lista.get(i).getPrecioCompra()+"", i, 7);
                      modelo1.setValueAt(lista.get(i).getPrecioVenta()+"", i, 8);
                      modelo1.setValueAt(lista.get(i).getIdBodega().getDetalle(), i, 9);
                      modelo1.setValueAt(lista.get(i).getIdProvedor().getNombre(), i, 10);
                      modelo1.setValueAt(lista.get(i).getIdCategoria().getNombre(), i, 11);
                      
               //       modelo1.setValueAt(lista.get(i).getPrimerApellido() + " " +lista.get(i).getSegundoApellido() , i, 3);
               //       modelo1.setValueAt(lista.get(i).getTelefono() , i, 4);
               //       modelo1.setValueAt(lista.get(i).getDireccion() , i, 5);
               //       modelo1.setValueAt(lista.get(i).getFechaIngreso(), i, 6);
               //       modelo1.setValueAt(lista.get(i).getEmail(), i, 7);
                  

                  }
                  tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
              }catch(Exception ex){
                //  JOptionPane.showMessageDialog(/*this*/, ex);
                  System.out.println("Error  " +ex);
              }
}
    
    public void LLenarTablaProductosXNombre(JTable tabla, String Nombreprod){
      try {
                modelo1 = (new DefaultTableModel(null, new String [] {"Id Producto","Cod Barras","Cod Produc", "Nombre","Detalle","Stock","Estado", "Precio Compra","Precio Venta","Bodega","Provedor","Categoria"}){
                Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                    
                };
                boolean[] canEdit = new boolean [] {false,false,false,false,false,false,false,false,false,false,false,false};
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex){
                return canEdit [colIndex];
            }


            });
            tabla.setModel(modelo1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.toString()+"error2");

            }        
             try{
                  Object O[]= null;
                  List<Producto> lista =controladorProductor.BuscaXNombre(Nombreprod);
               //   System.out.println("email " + lista.size());

                  for (int i = 0; i < lista.size(); i++) {
                      modelo1.addRow(O);
                                     
                      modelo1.setValueAt(lista.get(i).getIdProducto(), i, 0);
                      modelo1.setValueAt(lista.get(i).getCodBarras(), i, 1);
                      modelo1.setValueAt(lista.get(i).getCodProducto(), i, 2);
                      modelo1.setValueAt(lista.get(i).getNombre(), i, 3);
                      modelo1.setValueAt(lista.get(i).getDetalle(), i, 4);
                      modelo1.setValueAt(lista.get(i).getStock(), i, 5);
                      modelo1.setValueAt(lista.get(i).getEstado(), i, 6);
                      modelo1.setValueAt(lista.get(i).getPrecioCompra()+"", i, 7);
                      modelo1.setValueAt(lista.get(i).getPrecioVenta()+"", i, 8);
                      modelo1.setValueAt(lista.get(i).getIdBodega().getDetalle(), i, 9);
                      modelo1.setValueAt(lista.get(i).getIdProvedor().getNombre(), i, 10);
                      modelo1.setValueAt(lista.get(i).getIdCategoria().getNombre(), i, 11);
                      
               //       modelo1.setValueAt(lista.get(i).getPrimerApellido() + " " +lista.get(i).getSegundoApellido() , i, 3);
               //       modelo1.setValueAt(lista.get(i).getTelefono() , i, 4);
               //       modelo1.setValueAt(lista.get(i).getDireccion() , i, 5);
               //       modelo1.setValueAt(lista.get(i).getFechaIngreso(), i, 6);
               //       modelo1.setValueAt(lista.get(i).getEmail(), i, 7);
                  

                  }
                  tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
              }catch(Exception ex){
                //  JOptionPane.showMessageDialog(/*this*/, ex);
                  System.out.println("Error  " +ex);
              }
}
    
     public void LLenarTablaProductosXCodProducto(JTable tabla, String idproducto){
      try {
                modelo1 = (new DefaultTableModel(null, new String [] {"Id Producto","Cod Barras","Cod Produc", "Nombre","Detalle","Stock","Estado", "Precio Compra","Precio Venta","Bodega","Provedor","Categoria"}){
                Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                    
                };
                boolean[] canEdit = new boolean [] {false,false,false,false,false,false,false,false,false,false,false,false};
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex){
                return canEdit [colIndex];
            }


            });
            tabla.setModel(modelo1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.toString()+"error2");

            }        
             try{
                  Object O[]= null;
                  List<Producto> lista =controladorProductor.BuscaXCodigoProducto(idproducto);
               //   System.out.println("email " + lista.size());

                  for (int i = 0; i < lista.size(); i++) {
                      modelo1.addRow(O);
                                     
                      modelo1.setValueAt(lista.get(i).getIdProducto(), i, 0);
                      modelo1.setValueAt(lista.get(i).getCodBarras(), i, 1);
                      modelo1.setValueAt(lista.get(i).getCodProducto(), i, 2);
                      modelo1.setValueAt(lista.get(i).getNombre(), i, 3);
                      modelo1.setValueAt(lista.get(i).getDetalle(), i, 4);
                      modelo1.setValueAt(lista.get(i).getStock(), i, 5);
                      modelo1.setValueAt(lista.get(i).getEstado(), i, 6);
                      modelo1.setValueAt(lista.get(i).getPrecioCompra()+"", i, 7);
                      modelo1.setValueAt(lista.get(i).getPrecioVenta()+"", i, 8);
                      modelo1.setValueAt(lista.get(i).getIdBodega().getDetalle(), i, 9);
                      modelo1.setValueAt(lista.get(i).getIdProvedor().getNombre(), i, 10);
                      modelo1.setValueAt(lista.get(i).getIdCategoria().getNombre(), i, 11);
                      
               //       modelo1.setValueAt(lista.get(i).getPrimerApellido() + " " +lista.get(i).getSegundoApellido() , i, 3);
               //       modelo1.setValueAt(lista.get(i).getTelefono() , i, 4);
               //       modelo1.setValueAt(lista.get(i).getDireccion() , i, 5);
               //       modelo1.setValueAt(lista.get(i).getFechaIngreso(), i, 6);
               //       modelo1.setValueAt(lista.get(i).getEmail(), i, 7);
                  

                  }
                  tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
              }catch(Exception ex){
                //  JOptionPane.showMessageDialog(/*this*/, ex);
                  System.out.println("Error  " +ex);
              }
}
}
