/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 1.0
 * @since 08/04/2017
 * 
 */
public class Opcao {

	private String texto;

	public Opcao(String string) {
		texto = string ;  
	}//fecha construtor

    public Opcao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//fecha construtor

	public String getTexto() {
		return texto;
	}//fecha getTexto

}//fecha classe