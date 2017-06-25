
package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import util.Digita;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 1.4
 * @since 16/04/2017
 * 
 */
public class Voo {
    
    //atributos
    private int idVoo;
    private Digita d = new Digita();
    private String origem;
    private String destino;
    private LocalDate dataVoo;
    private Aviao aviao = null;
    private int idAviao;
  
    private  boolean[] controlaAssentos; 
    private int meuAssento = 0;
    
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //construtor
    
    public Voo(String origem,String destino, String horarioVoo, Aviao aviao){
        this.origem = origem;
        this.destino = destino;
        this.dataVoo = LocalDate.parse(horarioVoo ,formatadorData);
        this.aviao = aviao;
       // this.controlaAssentos = new boolean[quantidade];
        this.idAviao = aviao.getId();        
    }//fecha construtor
    
    public Voo(int idVoo, String origem,String destino, LocalDate horarioVoo, Aviao aviao){
        this.idVoo = idVoo;
        this.origem = origem;
        this.destino = destino;
        this.dataVoo = horarioVoo;
        this.aviao = aviao;
       // this.controlaAssentos = new boolean[quantidade];
        this.idAviao = aviao.getId();        
    }//fecha construtor
          
    //SETS E GETS

    public void setControlaAssentos(boolean[] controlaAssentos) {
        this.controlaAssentos = controlaAssentos;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setMeuAssento(int meuAssento) {
        this.meuAssento = meuAssento;
    }
    
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void setDataVoo(LocalDate dataVoo) {
        this.dataVoo = dataVoo;
    }
    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }
    public void setIdVoo(int idVoo) {
        this.idVoo = idVoo;
    }
    //TERMINA SETS
    
    public boolean[] getControlaAssentos() {
        return controlaAssentos;
    }

    public String getOrigem() {
        return origem;
    }

    public int getMeuAssento() {
        return meuAssento;
    }
    
    public String getDestino() {
        return destino;
    }
    public LocalDate getDataVoo() {
        return dataVoo;
    }
    public Aviao getAviao() {
        return aviao;
    }
    public int getIdVoo() {
        return idVoo;
    }
    public int getIdAviao() {
        return idAviao;
    }
    //TERMINA GETS
    
   
    @Override
    public String toString(){
        return  (String.format("%-10s", "ORIGEM") + "\t"
                   + String.format("%-20s", "|DESTINO") + "\t"
                      + String.format("%-15s", "|DATA VOO"))
              + "\n" + ((String.format("%-10s", getOrigem()) + "\t"
                        + String.format("%-20s", "|" + getDestino()) + "\t"
                         + String.format("%-15s", "|" + getDataVoo()))) + "\n"
                          +  "\n" + getAviao();
        }//fecha toString
    
    
}//fecha classe
