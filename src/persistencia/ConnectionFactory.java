
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lucas Guasselli de Moraes
 * @version 1.6
 * @since 20/05/2017
 * 
 */
public class ConnectionFactory {
  
        private static String url = "jdbc:postgresql://localhost:5432/postgres";
        private static String usuario = "postgres";
        private static String senha = "123456";
             
        public ConnectionFactory(){
        
        }//fecha construtor
        
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
               Connection conexao = null;

        try{
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
      
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro de Sistema - Classe do Driver Nao Encontrada!");
            throw new BDException(ex);
        } catch (SQLException ex) {
            throw new SQLException("Erro de Sistema - Problema na conexão do banco de dados");
        }//fecha try-catch
            return (conexao);
    }//fecha metodo
}//fecha classe
