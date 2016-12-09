/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.Equipamento;
import modelo.EquipamentoDao;

/**
 * FXML Controller class
 *
 * @author marcelo
 */
public class EquipamentoListagemVisao implements Initializable {

    @FXML
    private TableView<Equipamento> tabela;
    @FXML
    private TableColumn<Equipamento, String> colNome;
    @FXML
    private TableColumn<Equipamento, String> colPatrimonio;
    @FXML
    private TableColumn<Equipamento, Integer> colManutencoes;
    @FXML
    private TableColumn<Equipamento, Float> colGasto;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colPatrimonio.setCellValueFactory(new PropertyValueFactory("patrimonio"));
        colManutencoes.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Equipamento, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Equipamento, Integer> p) {
                return new SimpleIntegerProperty(p.getValue().getListaManutencoes().size()).asObject();
            }
        });
        colGasto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Equipamento, Float>, ObservableValue<Float>>() {
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Equipamento, Float> p) {
                return new SimpleFloatProperty(p.getValue().getTotalGastoManutencoes()).asObject();
            }
        });
        
        ObservableList<Equipamento> equipamentos = FXCollections.observableArrayList( EquipamentoDao.obterLista() );
        tabela.setItems(equipamentos);
    }    
    
    public void onClickVoltar(ActionEvent event) throws IOException{
        Button botaoQueClicou = (Button) event.getSource();
        Stage palco = (Stage) botaoQueClicou.getScene().getWindow();
        Pane principal = FXMLLoader.load(getClass().getResource("PrincipalTela.fxml"));
        Scene novaCena = new Scene(principal);
        palco.setScene(novaCena);
        palco.show();
    }
    
}
