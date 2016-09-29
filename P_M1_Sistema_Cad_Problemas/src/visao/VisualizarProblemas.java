/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Problema;
import modelo.ProblemaDao;

/**
 *
 * @author jc
 */
public class VisualizarProblemas implements Initializable{
    
    @FXML
    TableView<Problema> tabelaProblemas;
    
    @FXML
    TableColumn<Problema, String> colDescricao;
    
    @FXML
    TableColumn<Problema, Date> colData;
    
    @FXML
    TableColumn<Problema, String> colSituacao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colData.setCellValueFactory(new PropertyValueFactory("data"));
        colSituacao.setCellValueFactory(new PropertyValueFactory("situacao"));
        
        ObservableList<Problema> equipamentos = FXCollections.observableArrayList( ProblemaDao.obterLista() );
        tabelaProblemas.setItems(equipamentos);
    } 
   
    public void onClickVoltar( ActionEvent event)throws IOException{
        Button bt = (Button) event.getSource();
        //bt.getScene().getWindow()
        Scene cenaAntual = bt.getScene();
        Stage palcoAtual = (Stage) cenaAntual.getWindow();           
        Pane elementoNovoPalco = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene cena = new Scene(elementoNovoPalco);
        palcoAtual.setScene(cena);
        palcoAtual.show();
        System.out.println("Voltou pata o MENU");
    }

    
}
