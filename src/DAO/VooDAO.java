/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Aviao;
import model.Voo;
import persistencia.ConnectionFactory;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 1.7
 * @since 27/05/2017
 * 
 */
public class VooDAO {
    private AviaoDAO aDAO = new AviaoDAO();
    private Connection conexao;
    private PreparedStatement comando;
    private LocalDate dataVoo;
    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    
    public Connection conectar(String sql) throws SQLException, ClassNotFoundException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }//fecha conectar
    
    public void cadastrarVoo(Voo voo) throws SQLException, ClassNotFoundException{
        
        //CADASTRANDO Voo        
        try{   
            String sql = "insert into voo(origem,destino, dataVoo, idAviao) values (?, ?, ?,?)";
               conectar(sql);
                    comando.setString(1,voo.getOrigem());
                    comando.setString(2,voo.getDestino());
                        Date dataSql = Date.valueOf(voo.getDataVoo());
                        comando.setDate(3,dataSql);
                    comando.setInt(4,voo.getIdAviao());
                        
                 if(comando.executeUpdate()>0){
                     System.out.println("\nCadastro realizado com sucesso!");
                 }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao cadastrar voo!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha cadastrarVoo
    
    public List<Voo> retornaListaVoos() throws ClassNotFoundException, SQLException {
        List<Voo> listaVoos = new ArrayList<>();        
        try {
            String sql = "SELECT * FROM voo";
            conectar(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int idVoo = resultado.getInt("idVoo");
                String origem = resultado.getString("origem");
                String destino = resultado.getString("destino");
                Date dataSql = resultado.getDate("dataVoo");
                LocalDate horarioVoo = dataSql.toLocalDate();
                int idAviao = resultado.getInt("idAviao");   
                Aviao aviao = (aDAO.retornaAviaoByCod(idAviao));
                
                Voo voo = new Voo(idVoo, origem, destino, horarioVoo, aviao);
                    listaVoos.add(voo);                    
            }//fecha while
            return listaVoos;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar voos!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally    
    }//fecha listarVoos
    
    public List<Voo> retornalistarVoos(int _id) throws ClassNotFoundException, SQLException {
        List<Voo> listaVoos = new ArrayList<>();        
        try {
            String sql = "SELECT * FROM voo WHERE idAviao = ?";
            conectar(sql);
            comando.setInt(1,_id);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int idVoo = resultado.getInt("idVoo");
                String origem = resultado.getString("origem");
                String destino = resultado.getString("destino");
                Date dataSql = resultado.getDate("dataVoo");
                LocalDate horarioVoo = dataSql.toLocalDate();
                int id = resultado.getInt("id");   
                Aviao aviao = (aDAO.retornaAviaoByCod(id));
                
                Voo voo = new Voo(idVoo, origem, destino, horarioVoo, aviao);
                    listaVoos.add(voo);                    
            }//fecha while
            return listaVoos;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar voos!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally    
    }//fecha listarVoos
    
    public Voo retornaVoo(String _dataVoo, String _origem, String _destino) throws ClassNotFoundException, SQLException {
        Voo voo = null;       
        try {
            String sql = "SELECT * FROM voo WHERE origem = ? AND destino = ? AND dataVoo = ?";
            conectar(sql);
            comando.setString(1,_origem);
            comando.setString(2,_destino);
            dataVoo = LocalDate.parse(_dataVoo ,formatadorData);
            Date _dataSql = Date.valueOf(dataVoo);
                    comando.setDate(3,_dataSql);
            ResultSet resultado = comando.executeQuery();
        
            while (resultado.next()) {
                int idVoo = resultado.getInt("idVoo");
                String origem = resultado.getString("origem");
                String destino = resultado.getString("destino");
                Date dataSql = resultado.getDate("dataVoo");
                LocalDate horarioVoo = dataSql.toLocalDate();
                int idAviao = resultado.getInt("idAviao");   
                Aviao aviao = (aDAO.retornaAviaoByCod(idAviao));
                
               voo = new Voo(idVoo, origem, destino, horarioVoo, aviao);                              
            }//fecha while
            return voo; 
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao retornar voo!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha metodo retornaVoo
    
    public Voo retornaVoo(int _idVoo) throws ClassNotFoundException, SQLException {
        Voo voo = null;       
        try {
            String sql = "SELECT * FROM voo WHERE idVoo = ?";
            conectar(sql);
            comando.setInt(1,_idVoo);
            
            ResultSet resultado = comando.executeQuery();
        
            while (resultado.next()) {
                int idVoo = resultado.getInt("idVoo");
                String origem = resultado.getString("origem");
                String destino = resultado.getString("destino");
                Date dataSql = resultado.getDate("dataVoo");
                LocalDate horarioVoo = dataSql.toLocalDate();
                int idAviao = resultado.getInt("idAviao");   
                Aviao aviao = (aDAO.retornaAviaoByCod(idAviao));
                
               voo = new Voo(idVoo, origem, destino, horarioVoo, aviao);                              
            }//fecha while
            return voo; 
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao retornar voo!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha metodo retornaVoo
    
    public boolean verificaDataAviao(int codigoAviao,String dataVoo) throws ClassNotFoundException, SQLException {
            try{               
                String sql = "SELECT * from voo WHERE idAviao = ? ";
                conectar(sql);
                    comando.setInt(1,codigoAviao);                    
                ResultSet resultado = comando.executeQuery();
                
                if(resultado.next()){
                    Date dataSql = resultado.getDate("dataVoo");
                    LocalDate horarioVoo = dataSql.toLocalDate();
                    LocalDate _dataVoo = LocalDate.parse(dataVoo ,formatadorData);
                        if(horarioVoo.equals(_dataVoo)){
                            return true;
                        }else{
                            return false;
                        }//fecha if-else
                }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao verificar data do aviao!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
        return false;            
    }//fecha verificaDataAviao    
    
    public boolean verificaExistVoo() throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM voo";      
                conectar(sql);
            ResultSet resultado = comando.executeQuery();
                if(resultado.next()) {                
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar voo!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                return false;   
    }//fecha verifricaExistVoo
    
    public boolean verificaExistVoo(int idVoo) throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM voo WHERE idVoo = ?";      
                conectar(sql);
            comando.setInt(1, idVoo);
            ResultSet resultado = comando.executeQuery();
                if(resultado.next()) {                
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar voo!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                return false;   
    }//fecha verifricaExistVoo
    
    }//fecha classe
