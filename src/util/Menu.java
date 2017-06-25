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
public class Menu {
    Digita d = new Digita();
	Opcao[] opcoes = new Opcao[15];

	int numOpcoes = 0;

	public void addOption(Opcao opcao) {

		opcoes[numOpcoes] = opcao;
		numOpcoes++;
	}//fecha addOption

	public void show() {		
                System.out.println("\n###################################\n");
                    for (int i = 0; i < numOpcoes; i++) {
			
				if(i==numOpcoes-1){
					System.out.println("[" + "0" +  "] " +opcoes[i].getTexto());
				}else{
					System.out.println("["+ (i+1) + "] "+ opcoes[i].getTexto());
				}//fecha if-else
                    }//fecha for
		System.out.println("ESCOLHA UMA OPÇÃO: ");
	}//fecha show

	public int getOption() {
		int opcao = Integer.parseInt(d.digita(""));
		return opcao;		
	}//fecha getOption

}//fecha classe
