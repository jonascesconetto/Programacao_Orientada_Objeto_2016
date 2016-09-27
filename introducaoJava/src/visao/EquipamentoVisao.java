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

/**
 *
 * @author jonas
 */

public class EquipamentoVisao implements Initializable{
         
    @FXML
    TextField txtNomeEquipamento, txtNumeroPatrimonio, txtValor;
    
    @FXML
    DatePicker datePickerAquisicao, datePickerTerminoGarantia;
    
    @FXML
    Button btnSalvar;
    
    @Override 
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
    public void onClickCadastrar( ActionEvent event)throws IOException{
        
        //Recebe parametros para armazenar
        //algumas conversões são necessárias
        String nome = txtNomeEquipamento.getText();
        String patrimonio = txtNumeroPatrimonio.getText();
        float valor = Float.parseFloat(txtValor.getText());
        
        //Converção datePicker para um Date
        LocalDate dataAquisicaoLocalizada = datePickerAquisicao.getValue();
        Instant instanteDoInicioDoDiaDaDataAquisisao = Instant.from(dataAquisicaoLocalizada.atStartOfDay(ZoneId.systemDefault()));
        Date dataAquisicao = Date.from(instanteDoInicioDoDiaDaDataAquisisao);
        //Converção datePicker para um Date
        LocalDate dataTerminoGarantiaLocalizada = datePickerTerminoGarantia.getValue();
        Instant instanteDoInicioDoDiaDaDataTerminoGarantia = Instant.from(dataTerminoGarantiaLocalizada.atStartOfDay(ZoneId.systemDefault()));
        Date dataTerminoGarantia = Date.from(instanteDoInicioDoDiaDaDataTerminoGarantia);
        
        //Fazer uma validação aqui com um alerta DANGER
        
        //ENVIO DOS PARAMETROS
        EquipamentoControle.receberDadosCadstroEquipamentos(nome, patrimonio, dataAquisicao, dataTerminoGarantia, valor);
              
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
        System.out.println("CADASTRO EQUIPAMENTO para MENU");
    
    }

    /*public static void exibirFormularioCadastroEquipamento() {
        //variaveis do sistema
        Scanner entrada = new Scanner (System.in);
        String nome, patrimonio;
        Date dataAquisicao, dataTerminoGarantia;
        float valor;
        
        System.out.println("=== TELA DE CADASTRO DE EQUIPAMENTO ===");       
        System.out.println("Informe o nome: ");
        nome = entrada.nextLine();
        
        System.out.println("Informe o númeoro do patrimônio: ");
        patrimonio = entrada.nextLine();
        
        System.out.println("Informe a data de aquisição: ");
        //na data gerou um problema na atribuição, devido ao problema da não definição do padrão da data
        //dataAquisicao = entrada.nextLine();
        //para resolver utilizamos o SimpleDateFormat para definir o padrão
        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/mm/yyyy");      
        //desativar a bondade do SimpleDateFormat
        formatadorData.setLenient(false);
        do{
            try{
                dataAquisicao = formatadorData.parse(entrada.nextLine());
                break;
            }catch(Exception e){
                System.out.println("Data Invalida !!! Digite novamente");
            }    
        }while(true);
        
        System.out.println("Informe a data do termino da Garantia:");
        //mesmo problema aqui => dataTerminoGarantia = entrada.nextLine();
        //devemos tratar da mesma forma
        do{
            try{
                dataTerminoGarantia = formatadorData.parse(entrada.nextLine());
                //tratar a data da garantia depois da data de aquisição
                if(dataTerminoGarantia.after(dataAquisicao)){
                    break;   
                }else{
                    System.out.println("Data do termino da garantia deve ser posterior a data de aquisição !!! \n Informe uma data valida");
                }
            }catch(Exception e){
                System.out.println("Data Invalida !!! Digite novamente!");
            }    
        }while(true);
        
        System.out.println("Informe o valor: ");
        do{
            try{
                valor = Float.parseFloat(entrada.nextLine());
                break;
            }catch(Exception e){
                System.out.println("Valor Invalido !!! Digite novamente!");
            }
        }while(true);
        
        //Unir a camada visão com a camada do controle
        EquipamentoControle.receberDadosCadstroEquipamentos(nome, patrimonio, dataAquisicao, dataTerminoGarantia, valor);
        Menu.exibirMenu();
    }*/
    
    /*public static void exibirListagemEquipamentos(){
        
        System.out.println("=== TELA DE LISTAGEM DE EQUIPAMENTOS ===");
        System.out.println("NOME:\t\t|\tNÚMERO PATRIMONIO\t|\tNÚMERO MANUTENÇÕES\t|\tTOTAL GASTO MANUTENÇÕES");
        
        
        //ArrayList<Equipamento> lista = EquipamentoControle.obterListaEquipamentos();       
        //for (int i=0; i<lista.size(); i++){
        //    System.out.println(lista.get(i).getNome()+"\t"+lista.get(i).getDataAquisicao());        
        //}
            
        for (Equipamento obj : EquipamentoControle.obterListaEquipamentos()){
            System.out.println(obj.getNome()+"\t\t|\t"+obj.getPatrimonio()+"\t\t\t|\t"+obj.getListaManutencoes().size()+ "\t\t\t|\t " + obj.getTotalGastoManutencao());        
        }
        
        System.out.println("O que deseja fazer?");  
        System.out.println("0 - Voltar ao Menu");  
        System.out.println("Número do Patrimonio para Cadastrar manutenção\n");  
        
        Scanner entrada = new Scanner(System.in);
        String valorEntrada = entrada.nextLine();
        
        //como o objeto que será testado é uma string
        //o java possui equals que retorna true se o teste for verdadeiro
        if(entrada.equals("0")){
            //Menu.exibirMenu();
        }else{
            Equipamento encontrado = EquipamentoControle.obterEquipamentoPeloNumeroDoPatrimonio(valorEntrada);
            if(encontrado == null){
                System.out.println("ERRO: Não Encontrado");
                Menu.exibirMenu();
            }else{
                ManutencaoVisao.exibirFormularioCadastroManutencao(valorEntrada);
            }
        }
        
    }*/
}


