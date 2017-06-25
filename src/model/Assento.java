/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 1.8
 * @since 01/06/2017
 * 
 */
public class Assento {
    
    //atributos
    private int idAssento;
    private int idVoo;
    private int numAssento;
    private boolean disponibilidade = false;
    private String textoDisponibilidade;  
    
    public Assento(int idVoo, int numAssento, boolean disponibilidade){
        this.idVoo = idVoo;
        this.numAssento = numAssento;
        this.disponibilidade = disponibilidade;
        insereTxtDisp(this.disponibilidade);
    }//fecha  construtor
    
    public Assento(int idAssento,int idVoo, int numAssento, boolean disponibilidade){
        this.idAssento = idAssento;
        this.idVoo = idVoo;
        this.numAssento = numAssento;
        this.disponibilidade = disponibilidade;
        insereTxtDisp(this.disponibilidade);
    }//fecha  construtor
    //metodos  
    /*
       
    public void mostraAssentosDisponiveis(){
        for(int i = 0; i < controlaAssentos.length;i++){
            if(controlaAssentos[i] == false){
                System.out.println("Assento " + (i+1)+ ": " + "Disponivel");
            }//fecha if   
        }//fecha for
    }//fecha classe
    
    public void reservaAssento(){
      int assento;
      boolean loop = true;
        mostraAssentosDisponiveis();
            do{
            assento = Integer.parseInt(d.digita("Selecione o assento desejado: "));
                if(controlaAssentos[assento-1] == false){
                    controlaAssentos[assento-1] = true;
                    setMeuAssento(assento);
                    System.out.println("Assento reservado!");
                    loop = false;
                }else{
                    System.out.println("selecione um assento disponivel!");
                }//fecha if-else
            }while(loop == true);
   }//fecha metodo
    */
    
    public void setIdAssento(int idAssento) {
        this.idAssento = idAssento;
    }
    public void setIdVoo(int idVoo) {
        this.idVoo = idVoo;
    }
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }  
    public void setNumAssento(int numAssento) {
        this.numAssento = numAssento;
    }
    public void setTextoDisponibilidade(String textoDisponibilidade) {
        this.textoDisponibilidade = textoDisponibilidade;
    }
    public int getIdAssento() {
        return idAssento;
    }
    public int getIdVoo() {
        return idVoo;
    }
    public int getNumAssento() {
        return numAssento;
    }
    public String getTextoDisponibilidade() {
        return textoDisponibilidade;
    }   
    public boolean isDisponibilidade() {
        return disponibilidade;
    }
    
    private void insereTxtDisp(boolean disponibilidade) {
            if(disponibilidade == true){
                setTextoDisponibilidade("Ocupado");
            }else{
                setTextoDisponibilidade("Disponivel");
            }//fecha-if-else    
    }//fecha insereTxtDisp
    
}//fecha classe

