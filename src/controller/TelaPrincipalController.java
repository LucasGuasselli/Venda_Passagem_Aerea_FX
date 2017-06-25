package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    @FXML
    private void abrirTelaCadCliente(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/TelaCadCliente.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(painelTelaPrincipal.getScene().getWindow());
        stage.showAndWait();

    }//fecha abrirTelaCadCliente
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }//fecha initilize    
    
}//fecha classe
