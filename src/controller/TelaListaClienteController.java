/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDAO;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Cliente;

/**
 *
 * @author Lucas Guasselli
 * @since 29/06/2017
 * @version 3.0
 * 
 */
public class TelaListaClienteController implements Initializable {

    @FXML
    AnchorPane telaListaCliente;
    
    @FXML
    private TableView<Cliente> tableViewClientes;
    
    @FXML
    private TableColumn<Cliente, String> tableColumnNome;
    
    @FXML
    private TableColumn<Cliente, String> tableColumnRg;
     
    @FXML
    private TableColumn<Cliente, String> tableColumnTelefone;
    private ClienteDAO cDAO = new ClienteDAO();
    
    private List<Cliente> listaClientes;
    
    private ObservableList<Cliente> observableListClientes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        listaClientes = cDAO.retornaListaClientes();            
                carregaTableViewClientes(); 
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("EXCEÇÃO");
                alert.setHeaderText(null);
                alert.setContentText("erro ao receber clientes do banco!");

                Exception ex = new FileNotFoundException("erro ao receber clientes do banco");
                alert.showAndWait();
        }//try-catch
               
    }//fecha initialize    

    private void carregaTableViewClientes() {
        tableColumnRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));           
        
        observableListClientes = FXCollections.observableArrayList(listaClientes);
        tableViewClientes.setItems(observableListClientes);
    }//fecha metodo carregaTableView
    
}//fecha classe 
