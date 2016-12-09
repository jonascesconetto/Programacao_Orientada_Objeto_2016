/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.EquipamentoControle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Equipamento;
import modelo.EquipamentoDao;


/**
 *
 * @author marcelo
 */
public class ManutencaoVisao implements Initializable{
    
    @FXML
    public ComboBox opcoesEquipamentos;

    @FXML
    public TextField txtDescricao;
    
    @FXML
    public TextField txtValor;
    
    @FXML
    public DatePicker datManutencao;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Equipamento> opcoesCombo = FXCollections.observableArrayList( EquipamentoDao.obterLista() );
        opcoesEquipamentos.setItems(opcoesCombo);
        
    }
    
    public void onClickSalvar(ActionEvent event) throws IOException{
        Equipamento equipamentoSelecionado = (Equipamento) opcoesEquipamentos.getValue();
        String descricao = txtDescricao.getText();
        float valor = Float.parseFloat(txtValor.getText());
        LocalDate dataManutencaoLocal = datManutencao.getValue();
        Instant instante =  Instant.from(dataManutencaoLocal.atStartOfDay(ZoneId.systemDefault()));
        Date dataEmDate = Date.from(instante);
        EquipamentoControle.receberDadosNovaManutencao(equipamentoSelecionado.getPatrimonio(), descricao, dataEmDate, valor);
        
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso!");
        alerta.setContentText("Manutenção cadastrada");
        alerta.showAndWait();
        
        Button botaoQueClicou = (Button) event.getSource();
        Stage palco = (Stage) botaoQueClicou.getScene().getWindow();
        Pane principal = FXMLLoader.load(getClass().getResource("PrincipalTela.fxml"));
        Scene novaCena = new Scene(principal);
        palco.setScene(novaCena);
        palco.show();
    }
    
    
}
