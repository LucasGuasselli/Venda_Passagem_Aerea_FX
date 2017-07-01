/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TableView<Cliente> tableViewClientes;
    
    @FXML
    private TableColumn<Cliente, String> tableColumnNome;
    
    @FXML
    private TableColumn<Cliente, String> tableColumnRg;
     
    @FXML
    private TableColumn<Cliente, String> tableColumnTelefone;
    
    
    private List<Cliente> listaClientes;
    
    private ObservableList<Cliente> observableListClientes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaTableViewClientes();
        
        listaClientes = new ArrayList<>();
        
        listaClientes.add(new Cliente("nome","123456", "123456789"));
        listaClientes.add(new Cliente("teste","987654321", "987654321"));

    }//fecha initialize    

    private void carregaTableViewClientes() {
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        
        observableListClientes = FXCollections.observableArrayList(listaClientes);
        tableViewClientes.setItems(observableListClientes);
        
    }//fecha metodo carregaTableView
    
}//fecha classe 
