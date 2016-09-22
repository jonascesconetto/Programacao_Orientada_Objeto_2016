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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author jonas
 */
public class Menu implements Initializable{
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    //action faz relação ao evento do botão precionado
    public void abrirTelaEquipamento(ActionEvent e) throws IOException{ 
        Button bt = (Button) e.getSource();
        //bt.getScene().getWindow()
        Scene cenaAntual = bt.getScene();
        Stage palcoAtual = (Stage) cenaAntual.getWindow();
                
        Pane elementoNovoPalco = FXMLLoader.load(getClass().getResource("EquipamentoVisao.fxml"));
        Scene cena = new Scene(elementoNovoPalco);
        palcoAtual.setScene(cena);
        palcoAtual.show();
        System.out.println("MENU para CADASTRO EQUIPAMENTO");
    }
    
    public void abrirTelaManutencoes(ActionEvent e) throws IOException{
        Button bt = (Button) e.getSource();
        //bt.getScene().getWindow()
        Scene cenaAntual = bt.getScene();
        Stage palcoAtual = (Stage) cenaAntual.getWindow();
                
        Pane elementoNovoPalco = FXMLLoader.load(getClass().getResource("ManutencaoVisao.fxml"));
        Scene cena = new Scene(elementoNovoPalco);
        palcoAtual.setScene(cena);
        palcoAtual.show();
        System.out.println("MENU para CADASTRO MANUTENCOES");
    }
    
    public void fecharPrograma(){
        System.out.println("eeeeee");
    }
   
}

 //preciso cria um método para criar um menu
    /*public static void exibirMenu(){
	do{
            System.out.println("=== MENU PRINCIAPL ===");
            
            System.out.println("Selecionar a opção:");
            System.out.println("1 - Cadastro de equipamento");
            System.out.println("2 - Listagem de equipamentos");
            System.out.println("Presione qualquer outra para SAIR !!!");
            System.out.println("\nO que você deseja fazer?");

            //Tipo Scanner do java.util permite sscaniar infitos tipos de informaçao (é um tipo de váriavel)
            //O Java possui dua memórias
                //Memoria principal
                //Memoria - pilha de lixo coletavel

            Scanner entrada = new Scanner(System.in);

            int opcao = 0;
            //validação de dadoscom try{ }catch
            //do{
                try{
                    //opcao = entrada.nextInt();//ler o próximo caracter inteiro digitado 
                    opcao = Integer.parseInt(entrada.nextLine());//
                    System.out.println("O usuario digitou "+opcao);
                    //break;

                //Exception abrange inumeros tipos primitivos
                }catch(Exception e){
                    System.out.println("Não deu certo pq: "+e.getClass());
                }
            //}while(opcao != 1 && opcao != 2);
            //se chegar aqui quer dizer que é um inteiro, mas não que seja um valor valido,
            //por isso precisamos de mais uma validação< neste caso no while

            //ok, agora se ele digitar uma opcao valida para seu programa

            //if(opcao == 1){
            //    EquipamentoVisao.exibirFormularioCadastroEquipamento();
            //}else if(opcao == 2){
            //    EquipamentoVisao.exibirListagemEquipamentos();
            //}else{
                System.exit(0);
            }
        }while(true);
    }*/
