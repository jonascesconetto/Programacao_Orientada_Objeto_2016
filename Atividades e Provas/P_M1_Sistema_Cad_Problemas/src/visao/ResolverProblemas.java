/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.ItemComboProblemas;
import controle.ProblemaControle;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Problema;
import modelo.ProblemaDao;

/**
 *
 * @author jc
 */
public class ResolverProblemas implements Initializable{

    @FXML
    public ComboBox comboBoxProblemas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ItemComboProblemas> opcoesCombo = ItemComboProblemas.buscarItems();
        comboBoxProblemas.setItems(opcoesCombo);  
        comboBoxProblemas.getSelectionModel().selectFirst();
    }
    
    public void onClickResolver(ActionEvent event)throws IOException{
      
        ItemComboProblemas item = (ItemComboProblemas) comboBoxProblemas.getSelectionModel().getSelectedItem();
          
        ProblemaDao teste = new ProblemaDao();
        
        teste.alteraSituacao(item.getCodigo());
        
        //Mensagem de alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("REOSLVER PROBLEMA");
        alert.setHeaderText(null);
        alert.setContentText("Problema Resolvido com sucesso!!!");
        alert.showAndWait();//esperar o click do OK!!
        
        //ALTERAÇÃO DA  TELA voltar para o menu
        Button bt = (Button) event.getSource();
        //bt.getScene().getWindow()
        Scene cenaAntual = bt.getScene();
        Stage palcoAtual = (Stage) cenaAntual.getWindow();           
        Pane elementoNovoPalco = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene cena = new Scene(elementoNovoPalco);
        palcoAtual.setScene(cena);
        palcoAtual.show();
        System.out.println("CADASTRO EQUIPAMENTO para MENU");
      
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
