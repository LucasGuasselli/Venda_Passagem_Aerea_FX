/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 1.5
 * @since 19/04/2017
 * 
 */
public class Venda {
    //atributos
    private int idCliente;
    private int idVoo;
    private LocalDate horaCompra;
    private int numAssento;
    
    //construtor
    public Venda(Cliente cliente, Voo voo, Assento assento){
        this.idCliente = cliente.getId();
        this.idVoo = voo.getIdVoo();
        this.horaCompra = LocalDate.now(); 
        this.numAssento = assento.getNumAssento();
    }//fecha construtor

    //metodos
    
    //SETS E GETS   

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public void setIdVoo(int idVoo) {
        this.idVoo = idVoo;
    }   
    public void setNumAssento(int numAssento) {
        this.numAssento = numAssento;
    }
    
    public int getIdCliente() {
        return idCliente;
    }
    public int getIdVoo() {
        return idVoo;
    }
    public int getNumAssento() {
        return numAssento;
    }

    public LocalDate getHoraCompra() {
        return horaCompra;
    }
    
      //TERMINA GET
    
}//fecha classe
