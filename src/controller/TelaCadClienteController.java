/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ClienteDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.Cliente;

/**
 * FXML Controller class
 *
 * @author lhries
 */
public class TelaCadClienteController implements Initializable {
    @FXML    
    private AnchorPane telaCadCliente;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfRg;
    @FXML
    private TextField tfTelefone;
    private ClienteDAO cDAO = new ClienteDAO();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private void onActionCadastrar(ActionEvent event) {      
        cadastrarCliente();
        
    }//fecha handle event
    
    @FXML
    private void onActionLimpar(ActionEvent event){
        limpar();        
    }//fecha onActionLimpar
    
    @FXML
    private void tratarBotaoVoltar(ActionEvent event) throws IOException {
        System.out.println("Voltar");
        Stage stage = (Stage) telaCadCliente.getScene().getWindow();
        stage.close();
    }//fecha botao voltar
    
    private void cadastrarCliente(){
        try{
                  cDAO.cadastrarCliente(new Cliente(tfNome.getText(), tfRg.getText(), tfTelefone.getText())); 
            } catch (Exception e){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setHeaderText("aaaaa");
                    alert.setContentText("Erro ao cadastrar Cliente!");

                    alert.showAndWait(); 
            }//try-catch 
        Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("SUCESSO!");
                    alert.setHeaderText(null);
                    alert.setContentText("Cliente cadastrado com sucesso!");

                    alert.showAndWait(); 
    }//fecha cadastrar Cliente
    
    private void limpar(){
        tfNome.setText(null);
        tfRg.setText(null);
        tfTelefone.setText(null);
    }//fecha limpar
}//fecha classe
