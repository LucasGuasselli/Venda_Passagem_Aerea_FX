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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Aviao;
import util.Digita;

/**
 *
 * @author lucas
 */
public class TelaCadAviaoController implements Initializable {
    
    @FXML
    private AnchorPane telaCadAviao;

    @FXML
    private TextField tfCodigo;
    
    @FXML
    private TextField tfNome;
    
    @FXML
    private TextField tfQtdeAssentos;
    private AviaoDAO aDAO = new AviaoDAO();
    private Digita d = new Digita();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void onActionCadastrar(ActionEvent event) throws SQLException, ClassNotFoundException {      
        if(verificaCamposVazios() == true){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("CAMPOS VAZIOS");
            alert.setHeaderText(null);
            alert.setContentText("Voce deve preencher todos os campos!!");
                alert.showAndWait();
        }else{
            if(validaCampos() == true){
                if(aDAO.verificaAviaoByCod(Integer.parseInt(tfCodigo.getText())) == false){
                    cadastrarAviao();
                }else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("aviao ja existe!!");
                    Exception ex = new Exception("aviao nao existe!!");

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
    
    private void cadastrarAviao(){             
        
            Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmacao de cadastro");
                alert.setHeaderText(null);
                alert.setContentText("Voce tem certeza?");
                   Optional<ButtonType> result = alert.showAndWait();
        
            if (result.get() == ButtonType.OK){
                try{
                    String nome =  tfNome.getText(); 
                    nome = nome.toLowerCase();
                    nome = nome.substring(0,1).toUpperCase().concat(nome.substring(1));
                                           
                aDAO.cadastrarAviao(new Aviao(Integer.parseInt(tfCodigo.getText()),nome, Integer.parseInt(tfQtdeAssentos.getText()))); 
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("SUCESSO!");
                    alert.setHeaderText(null);
                    alert.setContentText("Aviao cadastrado com sucesso!");
                       alert.showAndWait();
                } catch (Exception e){
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("erro ao cadastrar aviao!");

                    Exception ex = new FileNotFoundException("erro ao cadastrar aviao");
                    alert.showAndWait();
            }//try-catch 
             
            } else {
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("CANCELADO!");
                alert.setHeaderText(null);
                alert.setContentText("cadastro cancelado com sucesso!");
                    alert.showAndWait();
            }//fecha if-else           
            
    }//fecha cadastrar Aviao
    
    @FXML
    private void tratarBotaoVoltar(ActionEvent event) throws IOException {
        System.out.println("Voltar");
        Stage stage = (Stage) telaCadAviao.getScene().getWindow();
        stage.close();
    }//fecha voltar
    
    @FXML
    private void onActionLimpar(ActionEvent event){
        limpar();        
    }//fecha onActionLimpar
    
    private void limpar(){
        tfCodigo.setText("");
        tfNome.setText("");
        tfQtdeAssentos.setText("");
    }//fecha limpar
    
    private boolean verificaCamposVazios() {
        if(tfCodigo.getText().isEmpty() || tfNome.getText().isEmpty() || tfQtdeAssentos.getText().isEmpty()){
            return true;
        }//fecha if  
        return false;
    }//fecha verificaCampos Vazios
    
    private boolean validaCampos(){
        try{
        if(d.validaCodigo(tfCodigo.getText()) == true && 
                d.validaNome(tfNome.getText()) == true && 
                d.validaQtdeAssentos(tfQtdeAssentos.getText()) == true){
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
    
}//fecha classe
