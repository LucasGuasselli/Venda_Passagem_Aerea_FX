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
public class Aviao {
  
    //atributos
    private int id;
    private int codigo;
    private String nome; 
    private int qtdeAssentos;

    //construtor
    public Aviao(int codigo, String nome, int qtdeAssentos) {
        this.codigo = codigo;
        this.nome = nome;
        this.qtdeAssentos = qtdeAssentos;
    }//fecha construtor
    
    public Aviao(int id,int codigo,String nome, int qtdeAssentos) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.qtdeAssentos = qtdeAssentos;
    }//fecha construtor

    //metodos 
    
    //SETS E GETS
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQtdeAssentos(int qtdeAssentos) {
        this.qtdeAssentos = qtdeAssentos;
    }    
    public void setId(int id) {
        this.id = id;
    }

    //TERMINA SETS    
    
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdeAssentos() {
        return qtdeAssentos;
    }
    public int getId() {
        return id;
    }

    //TERMINA GETS
   /* 
    private int increment(){
        return (codigo_autoIncrement++);
    }
   */  
    @Override
    public String toString(){
        return  (String.format("%-10s", "CODIGO DO AVIAO") + "\t"
                   + String.format("%-20s", "|NOME DO AVIAO") + "\t"
                      + String.format("%-15s", "|QUANTIDADE DE ASSENTOS"))
              + "\n" + ((String.format("%-10s", getCodigo()) + "\t"
                        + String.format("%-20s", "|" + getNome()) + "\t"
                         + String.format("%-15s", "|" + getQtdeAssentos()))) + "\n";
        }//fecha toString
 
}//fecha aviao
