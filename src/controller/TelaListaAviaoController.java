/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AviaoDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.Aviao;

/**
 *
 * @author Lucas Guasselli
 * @since 03/07/2017
 * @version 3.2
 * 
 */
public class TelaListaAviaoController implements Initializable {

    @FXML
    AnchorPane telaListaAviao;
    
    @FXML
    private TableView<Aviao> tableViewAviao;
    
    @FXML
    private TableColumn<Aviao, String> tableColumnCodigo;
    
    @FXML
    private TableColumn<Aviao, String> tableColumnNome;
     
    @FXML
    private TableColumn<Aviao, String> tableColumnQtdeAssentos;
    private AviaoDAO aDAO = new AviaoDAO();
    
    private List<Aviao> listaAvioes;
    
    private ObservableList<Aviao> observableListAvioes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
        listaAvioes = new ArrayList<>();
        listaAvioes = aDAO.retornaListaAvioes();
        
        carregaTableViewAvioes();

        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("EXCEÇÃO");
                alert.setHeaderText(null);
                alert.setContentText("erro ao receber avioes do banco!");
                        e.printStackTrace();

                alert.showAndWait();
        }//try-catch
    }//fecha initialize    

    private void carregaTableViewAvioes() {
        tableColumnCodigo.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Aviao,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Aviao, String> linha) {
                final Aviao aviao = linha.getValue();
                final SimpleObjectProperty<String> simpleObject = 
                        new SimpleObjectProperty(
                            String.valueOf(aviao.getCodigo())
                        );
                return simpleObject;
            }
        });       
        
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
       tableColumnQtdeAssentos.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Aviao,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Aviao, String> linha) {
                final Aviao aviao = linha.getValue();
                final SimpleObjectProperty<String> simpleObject = 
                        new SimpleObjectProperty(
                            String.valueOf(aviao.getQtdeAssentos())
                        );
                return simpleObject;
            }
        });        
       
        observableListAvioes = FXCollections.observableArrayList(listaAvioes);
        tableViewAviao.setItems(observableListAvioes);
    }//fecha metodo carregaTableView
    
}//fecha classe
