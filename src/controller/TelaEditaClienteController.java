/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Cliente;
import util.Digita;

/**
 *
 * @author Lucas Guasselli
 * @since 03/07/2017
 * @version 3.2
 * 
 */
public class TelaEditaClienteController implements Initializable {
    
    @FXML
    AnchorPane telaEditaCliente;
    @FXML
    private TextField tfRg_alterar;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfRg;
    @FXML
    private TextField tfTelefone;    
    @FXML
    private TableView<Cliente> tableViewClientes;    
    @FXML
    private TableColumn<Cliente, String> tableColumnNome;    
    @FXML
    private TableColumn<Cliente, String> tableColumnRg;     
    @FXML
    private TableColumn<Cliente, String> tableColumnTelefone;
    private ClienteDAO cDAO = new ClienteDAO();
    private Digita d = new Digita();
    private Cliente cli = null;
    
    private List<Cliente> listaClientes;    
    private ObservableList<Cliente> observableListClientes;
    
    @FXML
    private void onActionEditar(ActionEvent event) throws ClassNotFoundException, SQLException {      
        if(verificaCamposVazios() == true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CAMPOS VAZIOS");
            alert.setHeaderText(null);
            alert.setContentText("Voce deve preencher todos os campos!!");
                alert.showAndWait();
        }else{
            if(!cDAO.verificaClienteRg(tfRg_alterar.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setHeaderText(null);
                    alert.setContentText("Cliente nao cadastrado!!");
                        alert.showAndWait();                
            }else{
                if(validaCampos() == true){
                    editarCliente();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("CAMPOS CHEIOS");
                    alert.setHeaderText(null);
                    alert.setContentText("Voce excedeu a quantidade de caracteres de algum campo!!");
                        alert.showAndWait();
            }//fecha if-else                
        }//fecha if-else
        }//fecha if-else
    }//fecha handle event
    
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
    
    private void editarCliente(){             
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacao");
                alert.setHeaderText(null);
                alert.setContentText("Voce tem certeza sobre editar este cliente?");
                   Optional<ButtonType> result = alert.showAndWait();
        
            if (result.get() == ButtonType.OK){
                try{
                cli = (cDAO.retornaClientePorRg(tfRg_alterar.getText()));

                String nome = tfNome.getText();
                nome = nome.toLowerCase();
                nome = nome.substring(0,1).toUpperCase().concat(nome.substring(1));
                
                  cDAO.editarCliente(new Cliente(cli.getId(),nome, tfRg.getText(), tfTelefone.getText())); 
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCESSO!");
                    alert.setHeaderText(null);
                    alert.setContentText("Cliente editado com sucesso!");
                       alert.showAndWait();
                } catch (Exception e){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("erro ao editar cliente!");

                    alert.showAndWait();
            }//try-catch 
             
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CANCELADO!");
                alert.setHeaderText(null);
                alert.setContentText("edicao cancelada com sucesso!");
                    alert.showAndWait();
            }//fecha if-else           
            
    }//fecha editar Cliente
    
    @FXML
    private void onActionLimpar(ActionEvent event){
        limpar();        
    }//fecha onActionLimpar
    
    @FXML
    private void tratarBotaoVoltar(ActionEvent event) throws IOException {
        System.out.println("Voltar");
        Stage stage = (Stage) telaEditaCliente.getScene().getWindow();
        stage.close();
    }//fecha botao voltar
    
    private void limpar(){
        tfRg_alterar.setText("");
        tfNome.setText("");
        tfRg.setText("");
        tfTelefone.setText("");
    }//fecha limpar

    private boolean verificaCamposVazios() {
        if(tfNome.getText().isEmpty() || tfRg.getText().isEmpty() || tfTelefone.getText().isEmpty()){
            return true;
        }//fecha if  
        return false;
    }//fecha verificaCampos Vazios
    
    private boolean validaCampos(){        
        if(d.validaNome(tfNome.getText()) == true && 
                d.validaRg(tfRg.getText()) == true && 
                d.validaTelefone(tfTelefone.getText()) == true){
            return true;
        }//fecha -if                            
        return false;
    }//fecha validaCampos
}//fecha classe
