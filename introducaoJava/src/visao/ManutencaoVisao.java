/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.ItemComboEquipamento;
import controle.EquipamentoControle;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
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

/**
 *
 * @author jonas
 */

public class ManutencaoVisao implements Initializable{
       
    @FXML
    ComboBox combBoxEquipamentos;
    
    @FXML
    TextField txtDescricao, txtValor;
    
    @FXML        
    DatePicker datePicker;
    
    @FXML
    Button btnSalvar;
    
    @Override 
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<ItemComboEquipamento> itens = ItemComboEquipamento.buscarItems();
        combBoxEquipamentos.setItems(itens);
        combBoxEquipamentos.getSelectionModel().selectFirst();
    }
        
      
    public void onClickCadastrar( ActionEvent event) throws IOException{
        
        //DECLAÇÃO DOS PARAMETROS PARA ARMAZENAMENTO
        //chamada combobox
        ItemComboEquipamento item = (ItemComboEquipamento) combBoxEquipamentos.getSelectionModel().getSelectedItem();     
        String descricao = txtDescricao.getText();
        
        Date data = new Date();
        
        float valor = Float.parseFloat(txtValor.getText());
        
        //ENVIO DOS PARAMETROS
        EquipamentoControle.receberDadosNovaManutencao(item.getPatrimonio(), descricao, data, valor);
          
         //Mensagem de alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CADASTRO MANUTENÇÃO");
        alert.setHeaderText(null);
        alert.setContentText("Cadastro realizado com sucesso!!!");
        alert.showAndWait();
        
        //voltar para o menu
        Button bt = (Button) event.getSource();
        //bt.getScene().getWindow()
        Scene cenaAntual = bt.getScene();
        Stage palcoAtual = (Stage) cenaAntual.getWindow();
        Pane elementoNovoPalco = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene cena = new Scene(elementoNovoPalco);
        palcoAtual.setScene(cena);
        palcoAtual.show();
        System.out.println("CADASTRO MANUTENCOES para MENU");
            
    }
    
    public void onClickCancelar( ActionEvent event)throws IOException{
        
        //voltar para o menu
        Button bt = (Button) event.getSource();
        //bt.getScene().getWindow()
        Scene cenaAntual = bt.getScene();
        Stage palcoAtual = (Stage) cenaAntual.getWindow();           
        Pane elementoNovoPalco = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene cena = new Scene(elementoNovoPalco);
        palcoAtual.setScene(cena);
        palcoAtual.show();
        System.out.println("CADASTRO MANUTENCAO para MENU");
    
    }

}

/*public class ManutencaoVisao {
    
    public static void exibirFormularioCadastroManutencao(String numeroPatrimonio){
        Scanner entrada = new Scanner( System.in );
        System.out.println("==== TELA DE CADASTRO DE MANUTENÇÃO DO EQUIPAMENTO "+numeroPatrimonio+" ====");
        String descricao;
        Date data;
        float valor;
                
        System.out.println("Informe a descrição: ");
        descricao = entrada.nextLine();
        System.out.println("Informe a data: ");
        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        formatadorData.setLenient(false);
        do{
            try{
                data = formatadorData.parse(entrada.nextLine());
                break;
            }catch(Exception  e){
                System.out.println("Data inválida!!! Digite novamente!");
            }
        }while(true);
        System.out.println("Informe o valor: ");
        do{
            try{
                valor = Float.parseFloat(entrada.nextLine());
                break;
            }catch(Exception e){
                System.out.println("Valor inválido!!! Digite novamente!");
            }
            
        }while(true);
  
        EquipamentoControle.receberDadosNovaManutencao(numeroPatrimonio, descricao, data, valor);
        
    }
    
}*/