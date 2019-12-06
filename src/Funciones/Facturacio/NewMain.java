/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones.Facturacio;

import Controller.Facturacion.RolJpaController;
import Controller.Facturacion.UsuarioJpaController;
import DAO.Facturacion.Rol;
import DAO.Facturacion.Usuario;
import static Funciones.Facturacio.Encriptacion.Encriptar;
import java.awt.RenderingHints.Key;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Aluas
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, Exception {
       
      
        
        try {
            //        try {
//            // Generamos una clave de 128 bits adecuada para AES
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            keyGenerator.init(128);
//            SecretKey key = keyGenerator.generateKey();
//            
//            // Alternativamente, una clave que queramos que tenga al menos 16 bytes
//            // y nos quedamos con los bytes 0 a 15
//            key = new SecretKeySpec("una clave de 16 bytes".getBytes(),  0, 16, "AES");
//            
//            // Ver como se puede guardar esta clave en un fichero y recuperarla
//            // posteriormente en la clase RSAAsymetricCrypto.java
//            
//            // Texto a encriptar
//            String texto = "hola";
//            
//            // Se obtiene un cifrador AES
//            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
//            
//            try {
//                // Se inicializa para encriptacion y se encripta el texto,
//                // que debemos pasar como bytes.
//                aes.init(Cipher.ENCRYPT_MODE, (java.security.Key) key);
//            } catch (InvalidKeyException ex) {
//                Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            byte[] encriptado = aes.doFinal(texto.getBytes());
//            
//            // Se escribe byte a byte en hexadecimal el texto
//            // encriptado para ver su pinta.
//            for (byte b : encriptado) {
//                System.out.print(Integer.toHexString(0xFF & b));
//            }
//            System.out.println();
//            
//            // Se iniciliza el cifrador para desencriptar, con la
//            // misma clave y se desencripta
//            aes.init(Cipher.DECRYPT_MODE, key);
//            byte[] desencriptado = aes.doFinal(encriptado);
//            
//            // Texto obtenido, igual al original.
//            System.out.println(new String(desencriptado));
//        } catch (NoSuchPaddingException ex) {
//            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
//        }

            Rol rol = new  Rol();
            rol.setNombre("Administrador");
            rol.setDetalle("Acceso a toda la aplicacion");
            
            RolJpaController conrol = new RolJpaController();
            conrol.create(rol);
       
            UsuarioJpaController controlador =  new UsuarioJpaController();
            byte[] contrase = Encriptar("admin");
            Usuario usuario = new Usuario();
            usuario.setCedula("admin");
            usuario.setPrimerNombre("Santiago");
            usuario.setSegundoNombre("Alberto");
            usuario.setPrimerApellido("Alulema");
            usuario.setSegundoApellido("Asqui");
            usuario.setFechaNacimiento(new java.sql.Date(1992, 8, 9));
            usuario.setTelefono("0962664669");
            usuario.setEstado("Ac");
            usuario.setPassword(contrase);
            usuario.setIdRol(rol);
        
            
            controlador.create(usuario);
           //controlador.create(usuario);
           
        Encriptar("hola");
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
  
    
}
