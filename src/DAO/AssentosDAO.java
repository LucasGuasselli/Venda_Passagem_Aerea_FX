/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Assento;
import model.Voo;
import persistencia.ConnectionFactory;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 1.8
 * @since 01/06/2017
 * 
 */
public class AssentosDAO {
    //atributos
   private AviaoDAO aDAO = new AviaoDAO();
    private Connection conexao;
    private PreparedStatement comando;
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Connection conectar(String sql) throws SQLException, ClassNotFoundException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }//fecha conectar
    
    public void cadastrarAssento(Voo voo,int _numAssento) throws SQLException, ClassNotFoundException{
    
    //CADASTRANDO ASSENTOS       
        try{          
            String sql = "insert into assentos(idVoo,disponibilidade, numAssento) values(?, ?, ?)";
               conectar(sql);
                    comando.setInt(1,voo.getIdVoo());
                    comando.setBoolean(2,false);           
                    comando.setInt(3,_numAssento);
                    comando.executeUpdate();
               
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao cadastrar assentos!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha cadastraAssentos
       
    public Assento retornaAssento(Voo voo, int _numAssento) throws ClassNotFoundException, SQLException {
        Assento assento = null;
        String sql = "SELECT * FROM assentos WHERE idVoo = ? and numAssento = ?";
        try {
            conectar(sql);
            comando.setInt(1,voo.getIdVoo());
            comando.setInt(2,_numAssento);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int idAssento = resultado.getInt("idAssento");
                int idVoo = resultado.getInt("idVoo");
                boolean disponibilidade = resultado.getBoolean("disponibilidade");
                int numAssento = resultado.getInt("numAssento");
     
                assento = new Assento(idAssento, idVoo,numAssento, disponibilidade);                                 
            }//fecha while
            return assento;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar assento!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally   
    }//fecha listarAssento
    
    public List<Assento> retornaListaAssentos(Voo voo) throws ClassNotFoundException, SQLException {
        List<Assento> listaAssentos = new ArrayList<>();
        Assento assento = null;
        String sql = "SELECT * FROM assentos WHERE idVoo = ?";
        try {
            conectar(sql);
            comando.setInt(1,voo.getIdVoo());

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int idVoo = resultado.getInt("idVoo");
                boolean disponibilidade = resultado.getBoolean("disponibilidade");
                int numAssento = resultado.getInt("numAssento");
     
                assento = new Assento(idVoo,numAssento, disponibilidade); 
                    listaAssentos.add(assento);
            }//fecha while
            return listaAssentos;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar assento!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally   
    }//fecha listarAssento
    
    public boolean verificaDisponibilidadeAssento(int _numAssento) throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM assentos WHERE numAssento = ? AND disponibilidade = false";      
                conectar(sql);
            comando.setInt(1,_numAssento);
            ResultSet resultado = comando.executeQuery();
                if(resultado.next()) {                
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar assento!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                   return false;   
    }//fecha verificaDisponibilidadeAssento
    
    public void editarDisponibilidadeAssento(Assento assento,int _numAssento) throws SQLException, ClassNotFoundException{
        try{
            String sql = "UPDATE assentos SET idVoo = ?, disponibilidade = ?, numAssento = ? "
                    + "WHERE idAssento = ? AND numAssento = ?";

            conectar(sql);
            comando.setInt(1,assento.getIdVoo());
            comando.setBoolean(2, true);
            comando.setInt(3, assento.getNumAssento());
            comando.setInt(4, assento.getIdAssento());
            comando.setInt(5, _numAssento);
            comando.executeUpdate();
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao editar assento!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha editarCliente
    
}//fecha classe
