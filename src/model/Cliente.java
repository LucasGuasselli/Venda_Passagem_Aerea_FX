/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 1.0
 * @since 08/04/2017
 * 
 */
public class Cliente {
    
    //atributos
    private int id;
    private String nome;
    private String rg;
    private String telefone;
    
    //construtor
    
    public Cliente(){
    }
    
    public Cliente(String nome,String rg, String telefone){
        this.nome = nome;
        this.rg = rg;
        this.telefone = telefone;        
    }//fecha construtor
    
    public Cliente(int id, String nome,String rg, String telefone){
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.telefone = telefone;
        
    }//fecha construtor
    
    //metodos
    
    //SETS E GETS
    
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setRg(String rg){
        this.rg = rg;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    //TERMINAR SETS
    
    public String getNome(){
        return this.nome;
    }
    public String getRg(){
        return this.rg;
    }
    public String getTelefone(){
        return this.telefone;
    }
    public int getId() {
        return id;
    }

     //TERMINA GETS
   /* private int increment(){
        return (codigo_autoIncrement++);
    }//fecha increment
   */
    
    @Override
    public String toString(){
        return  (String.format("%-10s", "RG") + "\t"
                   + String.format("%-20s", "|NOME") + "\t"
                      + String.format("%-15s", "|TELEFONE"))
              + "\n" + ((String.format("%-10s", getRg()) + "\t"
                        + String.format("%-20s", "|" + getNome()) + "\t"
                         + String.format("%-15s", "|" + getTelefone()))) + "\n";
        }//fecha toString

}//fecha classe
