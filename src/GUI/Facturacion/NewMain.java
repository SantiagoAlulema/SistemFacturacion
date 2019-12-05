/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Facturacion;

import Controller.Facturacion.CedulaJpaController;
import Controller.Facturacion.PclienteJpaController;
import DAO.Facturacion.Cedula;
import DAO.Facturacion.Pcliente;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Pcliente cc = new Pcliente();
            cc.setPrimerNombre("safaf");
            
            PclienteJpaController mm= new PclienteJpaController();
            
            mm.create(cc);
            
            Cedula cd = new Cedula();
            cd.setCedula("0105784847");
            cd.setIdPCliente(cc);
            
            CedulaJpaController cpp= new CedulaJpaController();
            
            cpp.create(cd);
        } catch (Exception ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
