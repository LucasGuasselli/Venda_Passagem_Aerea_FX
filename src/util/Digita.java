/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 1.0
 * @since 08/04/2017
 * 
 */
public class Digita {
    int limit;
    
    public String digita(String texto){
        Scanner ler = new Scanner(System.in);    
            System.out.print(texto);
                  return ler.nextLine();
            }//fecha Digita
    
    public String digitaRg(String texto){
        Scanner ler = new Scanner(System.in);    
        String rg = "";
        limit = 10;            
            do{
                System.out.print(texto);
                 rg = ler.nextLine();                         
            }while(rg.length() > limit || rg.length() <= 3);
                return rg;
    }//fecha Digita
    
    public int digitaCodigo(String texto){
        Scanner ler = new Scanner(System.in);    
        int codigo;
            do{
                System.out.print(texto);
                   codigo = ler.nextInt();                         
            }while(codigo < 0 || codigo > 999);
                return codigo;
    }//fecha Digita

    public String digitaNome(String texto) {
        Scanner ler = new Scanner(System.in);    
        String nome = "";
        limit = 30;            
            do{
                System.out.print(texto);
                   nome = ler.nextLine();                         
            }while(nome.length() > limit || nome.length() == 0);
                    nome = nome.toLowerCase();
                    nome = nome.substring(0,1).toUpperCase().concat(nome.substring(1));
                return nome;   
    }//fecha metodo

    public String digitaData(String texto) {
        Scanner ler = new Scanner(System.in);    
        String data = "00/00/0000";
        limit = 10;            
            do{
                System.out.print(texto);
                    data = ler.nextLine();                         
            }while(data.length() > limit || data.length() < limit);
                return data;   
    }//fecha metodo
    
    public String digitaTelefone(String texto){
        Scanner ler = new Scanner(System.in);    
        String telefone = "(00)00000-0000";
        limit = 14;            
            do{
                System.out.print(texto);
                    telefone = ler.nextLine();                         
            }while(telefone.length() > limit || telefone.length() < limit);
                return telefone;  
    }//fecha metodo    
   
}//fecha classe
