/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.util.Scanner;

/**
 *
 * @author jc
 */
public class Menu {
    
    public static void exibirMenu(){
        do{
            System.out.println("=== MENU PRINCIAPL ===");
            System.out.println("Digite V para visualizar os problemas reportados.");
            System.out.println("Digite R para reportar um novo problema.");
            System.out.println("Presione qualquer outra para SAIR !!!");
            System.out.print("Opção-->> ");
            Scanner entrada = new Scanner(System.in);
            char opcao = 0;
            //Validação de dados
            try{
                opcao = entrada.nextLine().charAt(0);
                System.out.println("O usuario digitou " + opcao);
            }catch(Exception e){
                System.out.println("Erro !!! \n --->> " + e.getClass());
            }
            //Tratamento das opções
            if(opcao == 'V' || opcao == 'v'){
                //System.out.println("Apertou V");
                ProblemaVisao.exibirListagemProblemas();
            }else if(opcao == 'R' || opcao == 'r'){
                //System.out.println("Apertou R");
                ProblemaVisao.reportarNovoProblema();
            }else{
                System.exit(0);
            }
        }while(true);
    }
}
