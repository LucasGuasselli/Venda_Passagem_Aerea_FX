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
import java.util.ArrayList;
import java.util.List;
import model.Assento;
import model.Cliente;
import model.Venda;
import model.Voo;
import persistencia.ConnectionFactory;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 2.0
 * @since 07/06/2017
 * 
 */
public class VendaDAO {
    
    private Connection conexao;
    private PreparedStatement comando;
    private ClienteDAO cDAO = new ClienteDAO();
    private VooDAO vDAO = new VooDAO();
    private AssentosDAO assDAO = new AssentosDAO();
    
    public Connection conectar(String sql) throws SQLException, ClassNotFoundException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }//fecha conectar

   public void cadastrarVenda(Venda venda) throws SQLException, ClassNotFoundException{
        
        //CADASTRANDO Venda       
        try{   
            String sql = "insert into venda(idCliente,idVoo, horaCompra,numAssento) values (?, ?, ?, ?)";
               conectar(sql);
                    comando.setInt(1,venda.getIdCliente());
                    comando.setInt(2,venda.getIdVoo());
                        Date dataSql = Date.valueOf(venda.getHoraCompra());
                        comando.setDate(3,dataSql);
                    comando.setInt(4,venda.getNumAssento());
                        
                 if(comando.executeUpdate()>0){
                     System.out.println("\nCadastro realizado com sucesso!");
                 }//fecha if
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao cadastrar voo!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
    }//fecha cadastrarVenda
   
        
    public boolean verificaExistVenda() throws SQLException, ClassNotFoundException {
        try{
            String sql = "SELECT * FROM venda";      
                conectar(sql);
            ResultSet resultado = comando.executeQuery();
                if(resultado.next()) {                
                    return true;
                }//fecha if
        }catch (SQLException e) {
                throw new SQLException("\nErro ao verificar venda!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally
                   return false;   
    }//fecha verificaExistVenda
    
    public List<Venda> retornaListaVenda() throws ClassNotFoundException, SQLException {
        List<Venda> listaVendas = new ArrayList<>();
        Cliente cliente = null;
        Voo voo = null;
        Assento assento = null;
        try {
            String sql = "SELECT * FROM venda";
            conectar(sql);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int idVenda = resultado.getInt("idVenda");
                int idCliente = resultado.getInt("idCliente");
                int idVoo = resultado.getInt("idVoo");
                Date horaSql = resultado.getDate("horaCompra");
                LocalDate horaCompra = horaSql.toLocalDate();
                int numAssento = resultado.getInt("numAssento");

                cliente = (cDAO.retornaClientePorId(idCliente));
                voo = vDAO.retornaVoo(idVoo);
                assento = assDAO.retornaAssento(voo, numAssento);
                
                Venda venda = new Venda(cliente,voo,assento);
                    listaVendas.add(venda);                    
            }//fecha while
            return listaVendas;
        }catch (SQLException e) {
                 throw new SQLException("\nErro ao procurar voos!");
        } finally {
            conexao.close();
            comando.close();
        }//fecha finally    
    }//fecha listarVendas
    
}//fecha classe
