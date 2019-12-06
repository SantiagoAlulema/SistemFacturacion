/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones.Facturacio;
import java.math.BigInteger;
/**
 *
 * @author Santiago
 */
public class ValidaciondeTipado {
    public  boolean isNumeric(String cadena){
	try {
		Double.valueOf(cadena);
                System.out.println("pasa aqui");
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}
    
}
