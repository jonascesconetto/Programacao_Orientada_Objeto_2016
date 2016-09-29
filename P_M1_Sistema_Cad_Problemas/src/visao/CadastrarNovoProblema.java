/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.ProblemaControle;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.ProblemaDao;

/**
 *
 * @author jc
 */
public class CadastrarNovoProblema implements Initializable{

    @FXML
    TextField txtDescricao;
    
    @FXML
    DatePicker datePickerDataCadProblema;
    
    @FXML
    Button btnSalvar, btnVoltar;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
    public void onClickCadastrarProblema( ActionEvent event)throws IOException{
                
        int codigo = 0;
        String descricao = txtDescricao.getText();
        String situacao = "Aberto";
        
        ProblemaDao teste = new ProblemaDao();
        
        //PARAMETROS DE ENTRADA
        codigo = teste.obterNumeroProblemas() + 1;//sempre adiciona mais 1 para adicionar o código certo
        //Converção datePicker para um Date
        LocalDate dataReportagemProblemaLocalizada = datePickerDataCadProblema.getValue();
        Instant instanteDoInicioDoDiaDaDataAquisisao = Instant.from(dataReportagemProblemaLocalizada.atStartOfDay(ZoneId.systemDefault()));
        Date dataReportagemProblema = Date.from(instanteDoInicioDoDiaDaDataAquisisao);
       
        ProblemaControle.receberDadosProblema(codigo, descricao, situacao, dataReportagemProblema);
        
        //Mensagem de alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CADASTRO EQUIPAMENTO");
        alert.setHeaderText(null);
        alert.setContentText("Cadastro realizado com sucesso!!!");
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
