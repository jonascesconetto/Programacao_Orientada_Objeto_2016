/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.EquipamentoControle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import modelo.Equipamento;

/**
 *
 * @author jonas
 */
public class EquipamentoVisao {
    public static void exibirFormularioCadastroEquipamento() {
        
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
                
    }
    
    public static void exibirListagemEquipamentos(){
        
        System.out.println("=== TELA DE LISTAGEM DE EQUIPAMENTOS ===");
        System.out.println("NOME:\t\t|\tNÚMERO PATRIMONIO\t|\tNÚMERO MANUTENÇÕES\t|\tTOTAL GASTO MANUTENÇÕES");
        
        /*
        ArrayList<Equipamento> lista = EquipamentoControle.obterListaEquipamentos();       
        for (int i=0; i<lista.size(); i++){
            System.out.println(lista.get(i).getNome()+"\t"+lista.get(i).getDataAquisicao());        
        }
        */
        
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
        
    }
}


