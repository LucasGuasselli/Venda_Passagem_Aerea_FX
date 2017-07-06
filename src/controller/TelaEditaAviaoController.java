/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AviaoDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import model.Aviao;
import util.Digita;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class TelaEditaAviaoController implements Initializable {

    @FXML
    AnchorPane telaEditaAviao;
    @FXML
    private TextField tfCodigo_alterar;
    @FXML
    private TextField tfCodigo;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfQtdeAssentos;    
    @FXML
    private TableView<Aviao> tableViewAvioes;    
    @FXML
    private TableColumn<Aviao, String> tableColumnCodigo;    
    @FXML
    private TableColumn<Aviao, String> tableColumnNome;     
    @FXML
    private TableColumn<Aviao, String> tableColumnQtdeAssentos;
    private AviaoDAO aDAO = new AviaoDAO();
    private Digita d = new Digita();
    private Aviao avi = null;
    
    private List<Aviao> listaAvioes;    
    private ObservableList<Aviao> observableListAvioes;
    
    @FXML
    private void onActionEditar(ActionEvent event) throws ClassNotFoundException, SQLException {      
        if(verificaCamposVazios() == true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CAMPOS VAZIOS");
            alert.setHeaderText(null);
            alert.setContentText("Voce deve preencher todos os campos!!");
                alert.showAndWait();
        }else{
            if(validaCampos() == false){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("CAMPOS CHEIOS");
                    alert.setHeaderText(null);
                    alert.setContentText("Voce excedeu a quantidade de caracteres de algum campo!!");
                        alert.showAndWait();
                                       
                }else{
                    if(aDAO.verificaAviaoByCod(Integer.parseInt(tfCodigo_alterar.getText())) == true){
                        editarAviao();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("ERRO");
                     alert.setHeaderText(null);
                     alert.setContentText("Aviao nao cadastrado!!");
                        alert.showAndWait(); 
                }//fecha if-else                
                }//fecha if-else
            
        }//fecha if-else
    }//fecha handle event
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        listaAvioes = aDAO.retornaListaAvioes();            
                carregaTableViewAvioes(); 
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("EXCEÇÃO");
                alert.setHeaderText(null);
                alert.setContentText("erro ao receber avioes do banco!");

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
                            Integer.toString(aviao.getCodigo())
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
                            Integer.toString(aviao.getQtdeAssentos())
                        );
                return simpleObject;
            }
        });        
       
        observableListAvioes = FXCollections.observableArrayList(listaAvioes);
        tableViewAvioes.setItems(observableListAvioes); 
        
    }//fecha metodo carregaTableView
    
    private void editarAviao(){             
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacao");
                alert.setHeaderText(null);
                alert.setContentText("Voce tem certeza sobre editar este aviao?");
                   Optional<ButtonType> result = alert.showAndWait();
        
            if (result.get() == ButtonType.OK){
                try{
                avi = (aDAO.retornaAviaoByCod(Integer.parseInt(tfCodigo_alterar.getText())));

                String nome = tfNome.getText();
                nome = nome.toLowerCase();
                nome = nome.substring(0,1).toUpperCase().concat(nome.substring(1));
                
                  aDAO.editarAviao(new Aviao(avi.getId(),Integer.parseInt(tfCodigo.getText()),
                          nome, Integer.parseInt(tfQtdeAssentos.getText()))); 
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCESSO!");
                    alert.setHeaderText(null);
                    alert.setContentText("Aviao editado com sucesso!");
                       alert.showAndWait();
                } catch (Exception e){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("erro ao editar aviao!");

                    alert.showAndWait();
            }//try-catch 
             
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CANCELADO!");
                alert.setHeaderText(null);
                alert.setContentText("edicao cancelada com sucesso!");
                    alert.showAndWait();
            }//fecha if-else           
            
    }//fecha editar Aviao
    
    @FXML
    private void onActionLimpar(ActionEvent event){
        limpar();        
    }//fecha onActionLimpar
    
    @FXML
    private void tratarBotaoVoltar(ActionEvent event) throws IOException {
        System.out.println("Voltar");
        Stage stage = (Stage) telaEditaAviao.getScene().getWindow();
        stage.close();
    }//fecha botao voltar
    
    private void limpar(){
        tfCodigo_alterar.setText("");
        tfCodigo.setText("");
        tfNome.setText("");
        tfQtdeAssentos.setText("");
    }//fecha limpar

    private boolean verificaCamposVazios() {
        if(tfCodigo_alterar.getText().isEmpty() || tfCodigo.getText().isEmpty() || tfNome.getText().isEmpty() || tfQtdeAssentos.getText().isEmpty()){
            return true;
        }//fecha if  
        return false;
    }//fecha verificaCampos Vazios
    
     private boolean validaCampos(){
        try{
        if(d.validaCodigo(tfCodigo_alterar.getText()) &&
                d.validaCodigo(tfCodigo.getText()) == true && 
                d.validaNome(tfNome.getText()) == true && 
                d.validaQtdeAssentos(tfQtdeAssentos.getText()) == true){
            return true;
        }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("DIGITE UM NUMERO INTEIRO");

                    alert.showAndWait();                    
        }//fecha try-catch
        return false;
    }//fecha validaCampos
    
}//fecha classe
