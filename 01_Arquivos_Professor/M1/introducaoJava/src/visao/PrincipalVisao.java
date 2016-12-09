/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author 2008777
 */
public class PrincipalVisao implements  Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void onClickCadastrarEquipamento(ActionEvent event) throws IOException{
        Button botaoQueClicou = (Button) event.getSource();
        Stage palco = (Stage) botaoQueClicou.getScene().getWindow();
        Pane principal = FXMLLoader.load(getClass().getResource("EquipamentoTela.fxml"));
        Scene novaCena = new Scene(principal);
        palco.setScene(novaCena);
        palco.show();
        
    }   
    
    public void onClickCadastrarManutencao(ActionEvent event) throws IOException{
        Button botaoQueClicou = (Button) event.getSource();
        Stage palco = (Stage) botaoQueClicou.getScene().getWindow();
        Pane principal = FXMLLoader.load(getClass().getResource("ManutencaoTela.fxml"));
        Scene novaCena = new Scene(principal);
        palco.setScene(novaCena);
        palco.show();
    }    
    
    public void onClickListagemEquipamento(ActionEvent event) throws IOException{
        Button botaoQueClicou = (Button) event.getSource();
        Stage palco = (Stage) botaoQueClicou.getScene().getWindow();
        Pane principal = FXMLLoader.load(getClass().getResource("EquipamentoListagemTela.fxml"));
        Scene novaCena = new Scene(principal);
        palco.setScene(novaCena);
        palco.show();
    }    
    
            
}
