/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AssentosDAO;
import DAO.AviaoDAO;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Aviao;
import model.Cliente;
import model.Voo;
import util.Digita;
import util.VerificaDatas;

/**
 *
 * @author Lucas Guasselli
 * @since 03/07/2017
 * @version 3.2
 * 
 */
public class TelaCadVooController implements Initializable {

    @FXML
    AnchorPane telaCadVoo;
    @FXML
    private TextField tfOrigem;
    @FXML
    private TextField tfDestino;
    @FXML
    private DatePicker dpDataVoo;
    @FXML
    private TextField tfCodigo;
    @FXML
    private TableView<Aviao> tableViewAviao;    
    @FXML
    private TableColumn<Aviao, String> tableColumnCodigo;    
    @FXML
    private TableColumn<Aviao, String> tableColumnNome;     
    @FXML
    private TableColumn<Aviao, String> tableColumnQtdeAssentos;
    private List<Aviao> listaAvioes;    
    private ObservableList<Aviao> observableListAvioes;
    
    private AviaoDAO aDAO = new AviaoDAO();
    private VooDAO  vDAO = new VooDAO();
    private Digita d = new Digita();
    private Aviao avi = null;
    private Voo voo = null;
    private VerificaDatas verifica = new VerificaDatas();
    private AssentosDAO assDAO = new AssentosDAO();

    
    @FXML
    private void onActionCadastrar(ActionEvent event) throws ClassNotFoundException, SQLException {      
        if(aDAO.verificaExistAviao()== true){
            if(verificaCamposVazios() == true){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("CAMPOS VAZIOS");
                alert.setHeaderText(null);
                alert.setContentText("Voce deve preencher todos os campos!!");
                    alert.showAndWait();
            }else{
                if(validaCampos() == true){
                    if(aDAO.verificaAviaoByCod(Integer.parseInt(tfCodigo.getText())) == true){
                        if(verifica.verificaDataAnterior(dpDataVoo.getValue()) == false){
                            cadastrarVoo();
                        }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERRO DE DATA");
                        alert.setHeaderText(null);
                        alert.setContentText("Informe uma data igual ou a partir da data atual");
                            alert.showAndWait();
                }//fecha if-else             
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("AVIAO NAO EXISTE");
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
                alert.setTitle("AVIAO NAO CADASTRADO");
                alert.setHeaderText(null);
                alert.setContentText("So e permitido o cadastro de voo quando tiver pelo menos"
                + " um aviao cadastrado");
                   alert.showAndWait();
        }
    }//fecha handle event
    
    @FXML
    private void onActionLimpar(ActionEvent event){
        limpar();        
    }//fecha onActionLimpar
    
    @FXML
    private void tratarBotaoVoltar(ActionEvent event) throws IOException {
        System.out.println("Voltar");
        Stage stage = (Stage) telaCadVoo.getScene().getWindow();
        stage.close();
    }//fecha botao voltar
    
    private void cadastrarVoo(){             
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacao de cadastro");
                alert.setHeaderText(null);
                alert.setContentText("Voce tem certeza?");
                   Optional<ButtonType> result = alert.showAndWait();
        
            if (result.get() == ButtonType.OK){
                try{
                    avi = (aDAO.retornaAviaoByCod(Integer.parseInt(tfCodigo.getText())));

                String origem = tfOrigem.getText();
                origem = origem.toLowerCase();
                origem = origem.substring(0,1).toUpperCase().concat(origem.substring(1));
                
                String destino = tfDestino.getText();
                destino = destino.toLowerCase();
                destino = destino.substring(0,1).toUpperCase().concat(destino.substring(1));
                
                    vDAO.cadastrarVoo(new Voo(origem,destino, dpDataVoo.getValue(), avi)); 
                    
                    voo = vDAO.retornaVoo(dpDataVoo.getValue(), origem, destino);
                        for(int i = 0;i<avi.getQtdeAssentos();i++){
                            assDAO.cadastrarAssento(voo, (i+1));
                        }//fecha for    
                        
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("SUCESSO!");
                        alert.setHeaderText(null);
                        alert.setContentText("Voo cadastrado com sucesso!");
                            alert.showAndWait();
                } catch (Exception e){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("EXCEÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("erro ao cadastrar voo!");

                    alert.showAndWait();
            }//try-catch 
             
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CANCELADO!");
                alert.setHeaderText(null);
                alert.setContentText("cadastro cancelado com sucesso!");
                    alert.showAndWait();
            }//fecha if-else           
            
    }//fecha cadastrar Cliente
    
    private void limpar(){
        tfOrigem.setText("");
        tfDestino.setText("");
        tfCodigo.setText("");
        dpDataVoo.setValue(LocalDate.now());

    }//fecha limpar

    private boolean verificaCamposVazios() {
        if(tfOrigem.getText().isEmpty() || tfDestino.getText().isEmpty() || tfCodigo.getText().isEmpty() || dpDataVoo.getValue() == null){
            return true;
        }//fecha if  
        return false;
    }//fecha verificaCampos Vazios
    
    private boolean validaCampos(){        
        if(d.validaNome(tfOrigem.getText()) == true && 
                d.validaNome(tfDestino.getText()) == true && 
                d.validaCodigo(tfCodigo.getText()) == true){
            return true;
        }//fecha -if                            
        return false;
    }//fecha validaCampos
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpDataVoo.setValue(LocalDate.now());
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
    }//fecha initiaze
    
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
        tableViewAviao.setItems(observableListAvioes); 
        
    }//fecha metodo carregaTableView
    
}//fecha classe
