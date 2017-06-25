/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import model.Voo;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 1.4
 * @since 16/04/2017
 * 
 */
public class VerificaDatas {
    //atributos
    LocalDate hoje = LocalDate.now();
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    int soma;
    
    //construtos
    public VerificaDatas(){
    
    }//fecha construtor
    
    //metodo verifica se data recebida é posterior a data atual
    public boolean verificaDataAnterior(String data){
       
        LocalDate dataX = LocalDate.parse(data,formatadorData);
        
        Period periodo = Period.between(hoje, dataX);
        soma = (periodo.getYears() + periodo.getMonths() + periodo.getDays());
            if(soma < 0){
                return true;
            }//fecha if
            return false;
    }//fecha metodo
    
    public int retornaPeriodoEmDias(LocalDate data1, LocalDate data2){
        Period periodo = Period.between(data1, data2);
        int soma = (periodo.getYears() + periodo.getMonths() + periodo.getDays());
            return soma;
    }//fecha metodo
   
    public LocalDate transformaData(String data){
        LocalDate novaData = LocalDate.parse(data, formatadorData);
            return novaData;
    }//fecha metodo
}//fecha classe
