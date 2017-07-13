package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.AviaoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Lucas Guasselli
 * @since 25/06/2017
 * @version 3.0
 * 
 */
public class TelaPrincipalController implements Initializable {
    
    @FXML
    private AnchorPane painelTelaPrincipal;
    private AviaoDAO aDAO = new AviaoDAO();
    
    @FXML
    private void abrirTelaCadCliente(ActionEvent event) throws IOException {        
        Stage stage = new Stage();
        Parent cliente = FXMLLoader.load(this.getClass().getResource("/view/TelaCadCliente.fxml"));
        Scene scene = new Scene(cliente);
        stage.setScene(scene);
        scene.getStylesheets().add(TelaPrincipalController.class.getResource("/estilo/Cliente.css").toExternalForm());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelTelaPrincipal.getScene().getWindow());        
                
        stage.showAndWait();
    }//fecha abrirTelaCadCliente
    
       
    @FXML
    private void abrirTelaCadAviao(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent aviao = FXMLLoader.load(this.getClass().getResource("/view/TelaCadAviao.fxml"));
        stage.setScene(new Scene(aviao));
        aviao.getStylesheets().add(TelaPrincipalController.class.getResource("/estilo/Aviao.css").toExternalForm());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelTelaPrincipal.getScene().getWindow());
        stage.showAndWait();
    }//fecha abrirTelaCadAviao
    
    @FXML
    private void abrirTelaCadVoo(ActionEvent event) throws IOException {            
            Stage stage = new Stage();
            Parent voo = FXMLLoader.load(this.getClass().getResource("/view/TelaCadVoo.fxml"));
            Scene scene = new Scene(voo); 
            stage.setScene(scene);
            voo.getStylesheets().add(TelaPrincipalController.class.getResource("/estilo/Voo.css").toExternalForm());
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.initOwner(painelTelaPrincipal.getScene().getWindow());
                stage.showAndWait();
                
    }//fecha abrirTelaCadVoo
    
    @FXML
    private void abrirTelaEditaCliente(ActionEvent event) throws IOException {        
        Stage stage = new Stage();
        Parent cliente = FXMLLoader.load(this.getClass().getResource("/view/TelaEditaCliente.fxml"));
        Scene scene = new Scene(cliente);
        stage.setScene(scene);
        scene.getStylesheets().add(TelaPrincipalController.class.getResource("/estilo/Cliente.css").toExternalForm());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelTelaPrincipal.getScene().getWindow());        
                
        stage.showAndWait();
    }//fecha abrirTelaEditaCliente
    
    @FXML
    private void abrirTelaEditaAviao(ActionEvent event) throws IOException {        
        Stage stage = new Stage();
        Parent cliente = FXMLLoader.load(this.getClass().getResource("/view/TelaEditaAviao.fxml"));
        Scene scene = new Scene(cliente);
        stage.setScene(scene);
        scene.getStylesheets().add(TelaPrincipalController.class.getResource("/estilo/Aviao.css").toExternalForm());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelTelaPrincipal.getScene().getWindow());        
                
        stage.showAndWait();
    }//fecha abrirTelaEditaCliente
    
    @FXML
    private void abrirTelaDelCliente(ActionEvent event) throws IOException {        
        Stage stage = new Stage();
        Parent cliente = FXMLLoader.load(this.getClass().getResource("/view/TelaDelCliente.fxml"));
        Scene scene = new Scene(cliente);
        stage.setScene(scene);
        scene.getStylesheets().add(TelaPrincipalController.class.getResource("/estilo/Cliente.css").toExternalForm());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelTelaPrincipal.getScene().getWindow());        
                
        stage.showAndWait();
    }//fecha abrirTelaDelCliente
    
    @FXML
    private void abrirTelaDelAviao(ActionEvent event) throws IOException {        
        Stage stage = new Stage();
        Parent cliente = FXMLLoader.load(this.getClass().getResource("/view/TelaDelAviao.fxml"));
        Scene scene = new Scene(cliente);
        stage.setScene(scene);
        scene.getStylesheets().add(TelaPrincipalController.class.getResource("/estilo/Aviao.css").toExternalForm());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelTelaPrincipal.getScene().getWindow());        
                
        stage.showAndWait();
    }//fecha abrirTelaDelCliente
    
    @FXML
    private void abrirTelaListaCliente(ActionEvent event) throws IOException {        
        Stage stage = new Stage();
        Parent cliente = FXMLLoader.load(this.getClass().getResource("/view/TelaListaCliente.fxml"));
        Scene scene = new Scene(cliente);
        stage.setScene(scene);
 
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelTelaPrincipal.getScene().getWindow());        
                
        stage.showAndWait();
    }//fecha abrirTelaListaCliente
    
    @FXML
    private void abrirTelaListaAviao(ActionEvent event) throws IOException {        
        Stage stage = new Stage();
        Parent cliente = FXMLLoader.load(this.getClass().getResource("/view/TelaListaAviao.fxml"));
        Scene scene = new Scene(cliente);
        stage.setScene(scene);
 
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelTelaPrincipal.getScene().getWindow());        
                
        stage.showAndWait();
    }//fecha abrirTelaListaAviao
    
    @FXML
    private void sair(ActionEvent event){
        System.exit(0);
    }//fecha metodo sair
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }//fecha initilize    
   
       
}//fecha classe
