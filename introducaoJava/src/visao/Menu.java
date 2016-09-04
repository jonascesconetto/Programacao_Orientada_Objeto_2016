/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import armazenamento.MeioArmazenamento;
import java.util.Scanner;

/**
 *
 * @author jonas
 */
public class Menu {
    
    //preciso cria um método para criar um menu
    public static void exibirMenu(){
	do{
            System.out.println("=== MENU PRINCIAPL ===");
            System.out.println("Número de Equipamentos:" + MeioArmazenamento.MEIO_ARMAZENAMENTO_EQUIPAMENTOS.size());
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

            if(opcao == 1){
                EquipamentoVisao.exibirFormularioCadastroEquipamento();
            }else if(opcao == 2){
                EquipamentoVisao.exibirListagemEquipamentos();
            }else{
                System.exit(0);
            }
        }while(true);
    }
}
