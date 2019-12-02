/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones.Facturacio;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
public class Encriptacion {
    
     public static byte[] Encriptar(String texto) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException{
            // Generamos una clave de 128 bits adecuada para AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey key = keyGenerator.generateKey();
            
            // Alternativamente, una clave que queramos que tenga al menos 16 bytes
            // y nos quedamos con los bytes 0 a 15
            key = new SecretKeySpec("una clave de 16 bytes".getBytes(),  0, 16, "AES");
            
            // Ver como se puede guardar esta clave en un fichero y recuperarla
            // posteriormente en la clase RSAAsymetricCrypto.java
            
            // Texto a encriptar
     
            
            // Se obtiene un cifrador AES
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            
            try {
                // Se inicializa para encriptacion y se encripta el texto,
                // que debemos pasar como bytes.
                aes.init(Cipher.ENCRYPT_MODE, (java.security.Key) key);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] encriptado = aes.doFinal(texto.getBytes());
            
            // Se escribe byte a byte en hexadecimal el texto
            // encriptado para ver su pinta.
            for (byte b : encriptado) {
                System.out.print(Integer.toHexString(0xFF & b));
            }
            System.out.println();
            
            
            
            //Desencriptar(encriptado);
           return encriptado;
            
        }
    
    public static String Desencriptar(byte[] encriptado) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
           KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey key = keyGenerator.generateKey();
            key = new SecretKeySpec("una clave de 16 bytes".getBytes(),  0, 16, "AES");
                  
            // Se obtiene un cifrador AES
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
             
          
            
            // Se iniciliza el cifrador para desencriptar, con la
            // misma clave y se desencripta
            aes.init(Cipher.DECRYPT_MODE, key);
            byte[] desencriptado = aes.doFinal(encriptado);
            
            // Texto obtenido, igual al original.
          //  System.out.println(new String(desencriptado));
            return new String(desencriptado);
            
    }
    
}
