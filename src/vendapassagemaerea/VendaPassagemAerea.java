/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendapassagemaerea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lucas Guasselli
 * @since 25/06/2017
 * @version 3.0
 * 
 */
public class VendaPassagemAerea extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        scene.getStylesheets().add
        (VendaPassagemAerea.class.getResource("/estilo/estilo.css").toExternalForm());
        stage.show();
    }//fecha start

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }//fecha main   
    
}//fecha classe
