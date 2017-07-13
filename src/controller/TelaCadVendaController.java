/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AssentosDAO;
import DAO.ClienteDAO;
import DAO.VendaDAO;
import DAO.VooDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Assento;
import model.Cliente;
import model.Venda;
import model.Voo;
import util.Digita;

/**
 *
 * @author Lucas Guasselli
 * @since 13/07/2017
 * @version 3.5
 * 
 */

public class TelaCadVendaController implements Initializable {

    @FXML
    AnchorPane telaCadVenda;
    @FXML
    private TextField tfCodigo;
    @FXML
    private TextField tfRg;
    @FXML
    private TextField tfNumAssento;
    @FXML
    private TableView<Assento> tableViewAssento;    
    @FXML
    private TableColumn<Assento, String> tableColumnIdVoo;    
    @FXML
    private TableColumn<Assento, String> tableColumnDisponibilidade;     
    @FXML
    private TableColumn<Assento, String> tableColumnNumAssento;
    
    private AssentosDAO assDAO = new AssentosDAO();
    private List<Assento> listaAssentos;    
    private ObservableList<Assento> observableListAssentos;
    private VooDAO  vDAO = new VooDAO();
    private ClienteDAO cDAO = new ClienteDAO();
    private VendaDAO vendDAO = new VendaDAO();
    private Digita d = new Digita();
    private Voo voo = null;
    private Assento assento = null;
    private Cliente cliente = null;

@FXML
    private void onActionCadastrar(ActionEvent event) throws ClassNotFoundException, SQLException {      
        if(vDAO.verificaExistVoo() == true){
            if(verificaCamposVazios() == true){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("CAMPOS VAZIOS");
                alert.setHeaderText(null);
                alert.setContentText("Voce deve preencher todos os campos!!");
                    alert.showAndWait();
            }else{
                if(validaCampos() == true){
                    if(vDAO.verificaExistVoo(Integer.parseInt(tfCodigo.getText()))){    
                            if(cDAO.verificaClienteRg(tfRg.getText()) == true){
                                if(assDAO.verificaDisponibilidadeAssento(Integer.parseInt(tfNumAssento.getText()))){
                                     cadastrarVenda();
                                 }else{
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("ERRO DE DATA");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Informe um assento disponivel");
                                        alert.showAndWait();
                }//fecha if-else             
                            }else{
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                 alert.setTitle("CLIENTE NAO EXISTE");
                                 alert.setHeaderText(null);
                                 alert.setContentText("Voce inseriu um RG nao cadastrado no banco de dados!!");
                                      alert.showAndWait();
                    }//fecha if-else 
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("VOO NAO EXISTE");
                            alert.setHeaderText(null);
                            alert.setContentText("Voce inseriu um codigo nao cadastrado no banco de dados!!");
                                alert.showAndWait();
                        }//fecha if-else  
                }else{
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("CAMPOS CHEIOS");
                       alert.setHeaderText(null);
                       alert.setContentText("Voce excedeu a quantidade de caracteres de algum campo!!");
                          alert.showAndWait();
                }//fecha if-else                
            }//fecha if-else
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("VOO NAO CADASTRADO");
                alert.setHeaderText(null);
                alert.setContentText("So e possivel a realizacao de uma venda quando tiver pelo menos"
                + " um voo cadastrado");
                   alert.showAndWait();
        }
    }//fecha handle event
    
    private void cadastrarVenda(){             
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacao de cadastro");
                alert.setHeaderText(null);
                alert.setContentText("Voce tem certeza?");
                   Optional<ButtonType> result = alert.showAndWait();
        
            if (result.get() == ButtonType.OK){
                try{
                    cliente = cDAO.retornaClientePorRg(tfRg.getText());
                    voo = vDAO.retornaVoo(Integer.parseInt(tfCodigo.getText()));
                    assento = assDAO.retornaAssento(voo,Integer.parseInt(tfNumAssento.getText()));
                        vendDAO.cadastrarVenda(new Venda(cliente, voo, assento));
                            assDAO.editarDisponibilidadeAssento(assento,Integer.parseInt(tfNumAssento.getText()));   

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("SUCESSO!");
                        alert.setHeaderText(null);
                        alert.setContentText("venda cadastrado com sucesso!");
                            alert.showAndWait();
                } catch (Exception e){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("erro ao cadastrar venda!");

                    alert.showAndWait();
            }//try-catch 
             
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CANCELADO!");
                alert.setHeaderText(null);
                alert.setContentText("cadastro cancelado com sucesso!");
                    alert.showAndWait();
            }//fecha if-else           
            
    }//fecha cadastrar venda
    
    @FXML            
    private void mostraAssentos() throws SQLException, ClassNotFoundException{
        if(vDAO.verificaExistVoo() == true){
            if(tfCodigo.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("CAMPO VAZIO");
                alert.setHeaderText(null);
                alert.setContentText("Voce deve preencher id do voo!!");
                    alert.showAndWait();
            }else{        
                try{
                    if(d.validaCodigo(tfCodigo.getText()) == true){
                        if(vDAO.verificaExistVoo(Integer.parseInt(tfCodigo.getText()))){
                            try{
                                voo = vDAO.retornaVoo(Integer.parseInt(tfCodigo.getText()));
                                listaAssentos = assDAO.retornaListaAssentos(voo);
                                   carregaTableViewAssentos(); 
                            }catch(Exception e){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("EXCEÇÃO");
                                alert.setHeaderText(null);
                                alert.setContentText("erro ao receber assentos do banco!");

                                      alert.showAndWait();
                            }//try-catch
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("VOO NAO EXISTE");
                            alert.setHeaderText(null);
                            alert.setContentText("Voce inseriu um codigo nao cadastrado no banco de dados!!");
                                alert.showAndWait();
                        }//fecha if-else  
                }//fecha if
                }catch(Exception e){
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("EXCEÇÃO");
                        alert.setHeaderText(null);
                        alert.setContentText("DIGITE UM NUMERO INTEIRO");

                         alert.showAndWait();                    
                }//fecha try-catch
            }//fecha if-else
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("VOO NAO CADASTRADO");
                alert.setHeaderText(null);
                alert.setContentText("So e possivel a realizacao de uma venda quando tiver pelo menos"
                + " um voo cadastrado");
                   alert.showAndWait();
        }//fecha if-else
    }//fecha metodo
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
               
    }//fecha initialize  
    @FXML
    private void onActionLimpar(ActionEvent event){
        limpar();        
    }//fecha onActionLimpar
    
    private void limpar(){
        tfCodigo.setText("");
        tfRg.setText("");
        tfNumAssento.setText("");
        
    }//fecha limpar
    
    private boolean validaCampos(){        
        try{
        if(d.validaCodigo(tfCodigo.getText()) == true && 
                d.validaRg(tfRg.getText()) == true && 
                d.validaAssento(tfNumAssento.getText()) == true){
            return true;
        }//fecha -if                            
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("DIGITE UM NUMERO INTEIRO");

                    alert.showAndWait();                    
        }//fecha try-catch
        return false;
    }//fecha validaCampos
    
    private boolean verificaCamposVazios() {
        if(tfCodigo.getText().isEmpty() || tfRg.getText().isEmpty() || tfNumAssento.getText().isEmpty()){
            return true;
        }//fecha if  
        return false;
    }//fecha verificaCampos Vazios
    @FXML
    private void tratarBotaoVoltar(ActionEvent event) throws IOException {
        System.out.println("Voltar");
        Stage stage = (Stage) telaCadVenda.getScene().getWindow();
        stage.close();
    }//fecha botao voltar
    

    private void carregaTableViewAssentos() {
       tableColumnIdVoo.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Assento,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Assento, String> linha) {
                final Assento assento = linha.getValue();
                final SimpleObjectProperty<String> simpleObject = 
                        new SimpleObjectProperty(
                            Integer.toString(assento.getIdVoo())
                        );
                return simpleObject;
            }
        });       
        
        tableColumnDisponibilidade.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Assento,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Assento, String> linha) {
                final Assento assento = linha.getValue();
                final SimpleObjectProperty<String> simpleObject = 
                        new SimpleObjectProperty(
                            assento.getTextoDisponibilidade()
                        );
                return simpleObject;
            }
        });         
        
       tableColumnNumAssento.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Assento,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Assento, String> linha) {
                final Assento assento = linha.getValue();
                final SimpleObjectProperty<String> simpleObject = 
                        new SimpleObjectProperty(
                            Integer.toString(assento.getNumAssento())
                        );
                return simpleObject;
            }
        });        
       
        observableListAssentos = FXCollections.observableArrayList(listaAssentos);
        tableViewAssento.setItems(observableListAssentos); 
        
    }//fecha metodo carregaTableView
    
}//fecha classe
