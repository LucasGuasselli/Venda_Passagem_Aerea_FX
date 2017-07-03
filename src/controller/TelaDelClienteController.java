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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Cliente;
import util.Digita;

/**
 *
 * @author Lucas Guasselli
 * @since 01/07/2017
 * @version 3.1
 * 
 */
public class TelaDelClienteController implements Initializable {

    @FXML
    private AnchorPane telaDelCliente;
    
    @FXML
    private TextField tfRg;
    private ClienteDAO cDAO = new ClienteDAO();
    private Digita d = new Digita();
    private Cliente c = null;
    
    @FXML
    private void onActionDeletar(ActionEvent event) throws ClassNotFoundException, SQLException {      
        if(verificaCamposVazios() == true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CAMPO VAZIO");
            alert.setHeaderText(null);
            alert.setContentText("Voce deve preencher o campo RG!!");
                alert.showAndWait();
        }else{
            if(d.validaRg(tfRg.getText()) == true ){
                if(!cDAO.verificaClienteRg(tfRg.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("cliente nao existe!!");
                    Exception ex = new Exception("cliente nao existe!!");

                    alert.showAndWait();              
                }else{        
                    c = cDAO.retornaClientePorRg(tfRg.getText());
                    deletarCliente(c);
                }//if-else
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("CAMPOS CHEIOS");
                alert.setHeaderText(null);
                alert.setContentText("Voce excedeu a quantidade de caracteres de algum campo!!");
                   alert.showAndWait();
            }//fecha if-else                
        }//fecha if-else
        
    }//fecha handle event
    
    private void deletarCliente(Cliente cliente){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacao de exclusao");
                alert.setHeaderText(null);
                alert.setContentText("Voce tem certeza?");
                   Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
                try{
                    cDAO.deletarCliete(cliente);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCESSO!");
                    alert.setHeaderText(null);
                    alert.setContentText("Cliente deletado com sucesso!");
                       alert.showAndWait();
                } catch (Exception e){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("erro ao deletar cliente!");

                    Exception ex = new FileNotFoundException("erro ao deletar cliente");
                    alert.showAndWait();
            }//try-catch 
        }//fecha if      
    }//fecha deletarCliente
    
    @FXML
    private void onActionLimpar(ActionEvent event){
        limpar();        
    }//fecha onActionLimpar
    
    private void limpar(){        
        tfRg.setText("");        
    }//fecha limpar
    
    private boolean verificaCamposVazios() {
        if(tfRg.getText().isEmpty()){
            return true;
        }//fecha if  
        return false;
    }//fecha verificaCampos Vazios
    
    @FXML
    private void tratarBotaoVoltar(ActionEvent event) throws IOException {
        System.out.println("Voltar");
        Stage stage = (Stage) telaDelCliente.getScene().getWindow();
        stage.close();
    }//fecha botao voltar
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  //fecha initialize  
    
}//fecha classe
