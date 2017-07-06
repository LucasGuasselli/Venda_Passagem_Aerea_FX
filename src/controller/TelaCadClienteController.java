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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Cliente;
import util.Digita;

/**
 *
 * @author Lucas Guasselli
 * @since 25/06/2017
 * @version 3.0
 * 
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
    private Digita d = new Digita();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private void onActionCadastrar(ActionEvent event) throws ClassNotFoundException, SQLException {      
        if(verificaCamposVazios() == true){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("CAMPOS VAZIOS");
            alert.setHeaderText(null);
            alert.setContentText("Voce deve preencher todos os campos!!");
                alert.showAndWait();
        }else{
            if(validaCampos() == true){
                if(cDAO.verificaClienteRg(tfRg.getText()) == false){
                    cadastrarCliente();
                }else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("CLIENTE JA EXISTE");
                    alert.setHeaderText(null);
                    alert.setContentText("Voce inseriu um rg ja cadastrado no banco de dados!!");
                        alert.showAndWait();
            }//fecha if-else             
            }else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("CAMPOS CHEIOS");
                alert.setHeaderText(null);
                alert.setContentText("Voce excedeu a quantidade de caracteres de algum campo!!");
                   alert.showAndWait();
            }//fecha if-else                
        }//fecha if-else
        
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
        
            Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmacao de cadastro");
                alert.setHeaderText(null);
                alert.setContentText("Voce tem certeza?");
                   Optional<ButtonType> result = alert.showAndWait();
        
            if (result.get() == ButtonType.OK){
                try{
                String nome = tfNome.getText();
                nome = nome.toLowerCase();
                nome = nome.substring(0,1).toUpperCase().concat(nome.substring(1));
                
                    cDAO.cadastrarCliente(new Cliente(nome, tfRg.getText(), tfTelefone.getText())); 
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("SUCESSO!");
                        alert.setHeaderText(null);
                        alert.setContentText("Cliente cadastrado com sucesso!");
                            alert.showAndWait();
                } catch (Exception e){
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("erro ao cadastrar cliente!");

                    Exception ex = new FileNotFoundException("erro ao cadastrar cliente");
                    alert.showAndWait();
            }//try-catch 
             
            } else {
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("CANCELADO!");
                alert.setHeaderText(null);
                alert.setContentText("cadastro cancelado com sucesso!");
                    alert.showAndWait();
            }//fecha if-else           
            
    }//fecha cadastrar Cliente
    
    private void limpar(){
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
