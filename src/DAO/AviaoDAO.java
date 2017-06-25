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
import java.util.ArrayList;
import java.util.List;
import model.Aviao;
import persistencia.ConnectionFactory;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 1.6
 * @since 20/05/2017
 * 
 */

public class AviaoDAO {
    
    private Connection conexao;
    private PreparedStatement comando;

    public Connection conectar(String sql) throws SQLException, ClassNotFoundException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }//fecha conectar    
    
    public void cadastrarAviao(Aviao aviao) throws SQLException, ClassNotFoundException{
        
        //CADASTRANDO aviao        
        try{   
            String sql = "insert into aviao(codigo, nome, qtdeAssentos) VALUES (?, ?, ?)";
               conectar(sql);
                    comando.setInt(1, aviao.getCodigo());
                    comando.setString(2,aviao.getNome());
                    comando.setInt(3,aviao.getQtdeAssentos());
                    
                if(comando.executeUpdate()>0){
                     System.out.println("\nCadastro realizado com sucesso!");
                }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao cadastrar aviao!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally           
    }//fecha cadastrarAviao

    public void editarAviao(Aviao aviao) throws SQLException, ClassNotFoundException{
        try{
            String sql = "UPDATE aviao SET codigo=?, nome=?, qtdeAssentos=?"
                    + "WHERE idAviao=?";

            conectar(sql);
            comando.setInt(1, aviao.getCodigo());
            comando.setString(2, aviao.getNome());
            comando.setInt(3, aviao.getQtdeAssentos());
            comando.setInt(4, aviao.getId());
            comando.executeUpdate();      
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao editar aviao!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha editarAviao
    
    public void deletarAviao(Aviao aviao) throws SQLException, ClassNotFoundException{
        
        //DELETANDO Aviao
        try{
                String sql = "delete from aviao where codigo = ?";
                    conectar(sql); 
                    
                comando.setInt(1,aviao.getCodigo());
                if(comando.executeUpdate()>0){
                     System.out.println("\nAviao deletado com sucesso!");
                }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao deletar aviao!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally            
    }//fecha deletarAviao

    public Aviao retornaAviaoByCod(int _codigo) throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM aviao WHERE idAviao = ?";
            conectar(sql);
                   comando.setInt(1,_codigo);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                int id = resultado.getInt("idAviao");
                int codigo = resultado.getInt("codigo");
                String nome = resultado.getString("nome");
                int qtdeAssentos = resultado.getInt("qtdeAssentos");
                
                Aviao avi = new Aviao(id, codigo, nome, qtdeAssentos);
                return avi;
            }//terminar caminho feliz (IF)
        }catch (SQLException e) {
                 e.printStackTrace();
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally        
        return (null);   
    }//fecha procurarAviaoByCid
    
    public List<Aviao> retornaAviaoPorNome(String _nome) throws ClassNotFoundException, SQLException {
        List<Aviao> listaAvioes = new ArrayList<>();
        String sql = "SELECT * FROM aviao WHERE nome LIKE ?";
        try {
            conectar(sql);
            comando.setString(1, "%" + _nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("idAviao");
                int codigo = resultado.getInt("codigo");
                String nome = resultado.getString("nome");
                int qtdeAssentos = resultado.getInt("qtdeAssentos");               

                Aviao avi = new Aviao(id, codigo, nome, qtdeAssentos);
                    listaAvioes.add(avi);                    
            }//fecha while
            return listaAvioes;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar aviao!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha metodo listarPorNome
    
    public List<Aviao> retornaListaAvioes() throws ClassNotFoundException, SQLException {
        List<Aviao> listaAvioes = new ArrayList<>();
        String sql = "SELECT * FROM aviao";

        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            
            while (resultado.next()) {
                int id = resultado.getInt("idAviao");
                int codigo = resultado.getInt("codigo");
                String nome = resultado.getString("nome");
                int qtdeAssentos= resultado.getInt("qtdeAssentos");               

                Aviao avi = new Aviao(id, codigo, nome, qtdeAssentos);
                    listaAvioes.add(avi);                    
            }//fecha while
            return listaAvioes;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar aviao!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally    
    }//fecha listarClientes
    
    public boolean verificaAviaoByCod(int _codigo) throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM aviao WHERE idAviao = ?";      
                conectar(sql);
                comando.setInt(1, _codigo);
            ResultSet resultado = comando.executeQuery();
                if(resultado.next()) {                
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar aviao!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                return false;   
    }//fecha verifricaAviaoByCod
    
    public boolean verificaAviaoNome(String _nome) throws ClassNotFoundException, SQLException {
        try{
            String sql = "SELECT * FROM aviao WHERE nome = ?";      
                conectar(sql);
                comando.setString(1, "%" + _nome + "%");
            ResultSet resultado = comando.executeQuery();
                if (resultado.next()) {                
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar aviao!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                return false; 
    }//fecha metodo verificaNome
    
    public boolean verificaExistAviao() throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM aviao";      
                conectar(sql);
            ResultSet resultado = comando.executeQuery();
                if(resultado.next()) {                
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar aviao!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                return false;   
    }//fecha verificaExistAviao
}//fecha classe
