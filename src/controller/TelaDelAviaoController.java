/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AviaoDAO;
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
import model.Aviao;
import util.Digita;

/**
 *
 * @author Lucas Guasselli
 * @since 01/07/2017
 * @version 3.1
 * 
 */
public class TelaDelAviaoController implements Initializable {

    @FXML
    AnchorPane telaDelAviao;
    
    @FXML
    private TextField tfCodigo;
    
    private AviaoDAO aDAO = new AviaoDAO();
    private Digita d = new Digita();
    private Aviao avi = null;
    
    @FXML
    private void onActionDeletar(ActionEvent event) throws ClassNotFoundException, SQLException {      
        if(verificaCamposVazios() == true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CAMPO VAZIO");
            alert.setHeaderText(null);
            alert.setContentText("Voce deve preencher o campo CODIGO!!");
                alert.showAndWait();
        }else{
            if(validaCampos() == true ){
                if(!aDAO.verificaAviaoByCod(Integer.parseInt(tfCodigo.getText()))){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("aviao nao existe!!");
                    Exception ex = new Exception("aviao nao existe!!");

                    alert.showAndWait();              
                }else{        
                    avi = aDAO.retornaAviaoByCod(Integer.parseInt(tfCodigo.getText()));
                    deletarAviao(avi);
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
    
    private void deletarAviao(Aviao aviao){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacao de exclusao");
                alert.setHeaderText(null);
                alert.setContentText("Voce tem certeza?");
                   Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
                try{
                    aDAO.deletarAviao(aviao);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCESSO!");
                    alert.setHeaderText(null);
                    alert.setContentText("Aviao deletado com sucesso!");
                       alert.showAndWait();
                } catch (Exception e){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("erro ao deletar aviao!");

                    Exception ex = new FileNotFoundException("erro ao deletar aviao");
                    alert.showAndWait();
            }//try-catch 
        }//fecha if      
    }//fecha deletarAviao
    
    @FXML
    private void onActionLimpar(ActionEvent event){
        limpar();        
    }//fecha onActionLimpar
    
    private void limpar(){        
        tfCodigo.setText("");        
    }//fecha limpar
    
    private boolean verificaCamposVazios() {
        if(tfCodigo.getText().isEmpty()){
            return true;
        }//fecha if  
        return false;
    }//fecha verificaCampos Vazios
    
    @FXML
    private void tratarBotaoVoltar(ActionEvent event) throws IOException {
        System.out.println("Voltar");
        Stage stage = (Stage) telaDelAviao.getScene().getWindow();
        stage.close();
    }//fecha botao voltar
    
    private boolean validaCampos(){
        try{
        if(d.validaCodigo(tfCodigo.getText()) == true){
            return true;
        }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("DIGITE UM NUMERO INTEIRO");
                    Exception ex = new Exception("DIGITE UM NUMERO INTEIRO");

                    alert.showAndWait();                    
        }//fecha try-catch
        return false;
    }//fecha validaCampos
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }//fecha initialize    
    
}//fecha classe
