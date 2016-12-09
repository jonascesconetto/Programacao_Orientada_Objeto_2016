/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.EquipamentoControle;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import modelo.Equipamento;

/**
 *
 * @author marcelo
 */
public class EquipamentoVisao implements  Initializable{
    
    @FXML
    private TextField txtNomeEquipamento;
    
    @FXML
    private TextField txtNumeroPatrimonio;
    
    @FXML
    private TextField txtValor;
    
    @FXML
    private DatePicker datAquisicao;
    
    @FXML
    private DatePicker datTerminoGarantia;
    
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    
    public void onClickBotao(ActionEvent event) throws IOException{
        String nome = txtNomeEquipamento.getText();
        String numero = txtNumeroPatrimonio.getText();
        String valor = txtValor.getText();
        float valorEmFloat = Float.parseFloat(valor);
        
        //http://stackoverflow.com/questions/20446026/get-value-from-date-picker
        LocalDate dataAquisicaoLocalizada = datAquisicao.getValue();
        Instant instanteDoInicioDoDiaDaDataAquisicao = Instant.from(dataAquisicaoLocalizada.atStartOfDay(ZoneId.systemDefault()));
        Date datAquisicaoEmDate = Date.from(instanteDoInicioDoDiaDaDataAquisicao);
        
        LocalDate dataTerminoGarantiaLocalizada = datTerminoGarantia.getValue();
        Instant instanteDoInicioDoDiaDaDataTerminoGarantia = Instant.from(dataTerminoGarantiaLocalizada.atStartOfDay(ZoneId.systemDefault()));
        Date datTerminoGarantiaEmDate = Date.from(instanteDoInicioDoDiaDaDataTerminoGarantia);
        
        if(datTerminoGarantiaEmDate.before(datAquisicaoEmDate)){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro!");
            alerta.setContentText("Deu ruim!");
            alerta.showAndWait();
            return;
        }
        
        EquipamentoControle.receberDadosCadastroEquipamento(
            nome, 
            numero, 
            datAquisicaoEmDate, 
            datTerminoGarantiaEmDate, 
            valorEmFloat);
        
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso!");
        alerta.setContentText("Equipamento cadastrado");
        alerta.showAndWait();
        
        Button botaoQueClicou = (Button) event.getSource();
        Stage palco = (Stage) botaoQueClicou.getScene().getWindow();
        Pane principal = FXMLLoader.load(getClass().getResource("PrincipalTela.fxml"));
        Scene novaCena = new Scene(principal);
        palco.setScene(novaCena);
        palco.show();
        
    }
    
    
    public static void exibirFormularioCadastroEquipamento(){
        Scanner entrada = new Scanner( System.in );
        System.out.println("==== TELA DE CADASTRO DE EQUIPAMENTO ====");
        String nome, patrimonio;
        Date dataAquisicao, dataTerminoGarantia;
        float valor;
                
        System.out.println("Informe o nome: ");
        nome = entrada.nextLine();
        System.out.println("Informe o número do patromônio: ");
        patrimonio = entrada.nextLine();
        System.out.println("Informe a data de aquisição: ");
        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        formatadorData.setLenient(false);
        do{
            try{
                dataAquisicao = formatadorData.parse(entrada.nextLine());
                break;
            }catch(Exception  e){
                System.out.println("Data inválida!!! Digite novamente!");
            }
        }while(true);
        System.out.println("Informe a data de término da garantia: ");
        do{
            try{
                dataTerminoGarantia = formatadorData.parse(entrada.nextLine());
                if(dataTerminoGarantia.after(dataAquisicao)){
                    break;
                }else{
                    System.out.println("Data de término da garantia deve ser posterior a data de aquisição");
                }

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
        EquipamentoControle.receberDadosCadastroEquipamento(nome, patrimonio, dataAquisicao, dataTerminoGarantia, valor);
    }
    
    
    public static void exibirListagemEquipamentos(){
        System.out.println("==== TELA DE LISTAGEM DE EQUIPAMENTOS ====");
        
        System.out.println("NOME \t NÚMERO PATROMONIO \t NÚMERO MANUTENÇÕES \t TOTAL GASTO");

//        ArrayList<Equipamento> lista = EquipamentoControle.obterListaEquipamentos();
//        for(int i = 0; i < lista.size(); i++){
//            System.out.println(lista.get(i).getNome()+"\t "+lista.get(i).getDataAquisicao());
//        }
        
        for(Equipamento obj : EquipamentoControle.obterListaEquipamentos()){
            System.out.println(obj.getNome() + "\t " + obj.getPatrimonio()+"\t " + obj.getListaManutencoes().size() + "\t " + obj.getTotalGastoManutencoes() );
        }
        
        System.out.println("\nO que deseja fazer?");
        System.out.println("0) Voltar ao menu");
        System.out.println("Número patromônio) Cadastrar manutenção para o equipamento");
        
        Scanner entrada = new Scanner(System.in);
        String valorDigitado = entrada.nextLine();
        if(entrada.equals("0")){
            //não faz nada, ou seja, retorna pro menu
        }else{
            Equipamento encontrado = EquipamentoControle.obterEquipamentoPeloNumeroDoPatrimonio(valorDigitado);
            if(encontrado == null){
                System.out.println("ERRO: Não encontrei");
            }else{
//                ManutencaoVisao.exibirFormularioCadastroManutencao(valorDigitado);
            }
        }
        
        
    }

    
}
