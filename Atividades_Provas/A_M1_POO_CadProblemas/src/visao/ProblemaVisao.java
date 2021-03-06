/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import armazenamento.MeioArmazenamento;
import modelo.Problema;
import controle.ProblemaControle;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author jc
 */
public class ProblemaVisao {
    public static void reportarNovoProblema() {
        //variaveis do sistema
        Scanner entrada = new Scanner (System.in);
        int codigo=0;
        String descricao, situacao;
        Date dataReportagemProblema, dataAtual;
        //PARAMETROS DE ENTRADA
        System.out.println("==== REPORTE DE NOVO PROBLEMAS ====");       
        //Parametro do código
        codigo = MeioArmazenamento.MEIO_ARMAZENAMENTO_PROBLEMAS.size() + 1;
        //Parametro da descrição
        System.out.println("Digite a descrição do problema: ");
        descricao = entrada.nextLine();
        //Parametro da descrição, sempre que é cadastrado é considerada aberta
        situacao = "Aberto";
        //Parametro da data
        System.out.println("Digite a data que o problema foi reportado: ");
        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        dataAtual = new Date();
        formatadorData.setLenient(false);
        //inserção da data com validação do dia atual
        do{
            try{
                dataReportagemProblema = formatadorData.parse(entrada.nextLine());
                if(dataReportagemProblema.after(dataAtual)){
                    System.out.println("Data Invalida !!! \nPor favor digite novamente: ");         
                }else{
                    if (dataReportagemProblema.before(dataAtual)){
                        System.out.println("Data Valida!!!\n"); 
                    }
                    break;
                }
            }catch(Exception e){
                
            }    
        }while(true);      
        //Unir a camada visão com a camada do controle
        ProblemaControle.receberDadosProblema(codigo, descricao, situacao, dataReportagemProblema);
        Menu.exibirMenu();
    }
    
    public static void exibirListagemProblemas(){
        //LISTAGEM DO SISTEMA
            
        System.out.println(" ==== LISTAGEM DE PROBLEMAS ====");
        System.out.println("CÓDIG0\t|\tDESCRIÇÃO\t|\tSITUAÇÃO\t|\tDATA");
        //Listagem pela quantidade de problemas no Array de problemas
        for (Problema obj : ProblemaControle.obterListaProblemas()){
            //Resolveldo o problema da vizualização da data
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");    
            Date data = obj.getData() ;
            String str = fmt.format(data);
            
            System.out.println(obj.getCodigo()+"\t|\t"+obj.getDescricao()+"\t\t|\t"+obj.getSituacao()+"\t|\t"+ str);        
        }
        //Interação usuario
        System.out.println("\nO que deseja fazer?");  
        System.out.println("M - Voltar ao Menu");
        System.out.println("Digite o código do problema para mudar a situação para resolvido\n"); 
        System.out.print("Opção ->> ");
        //Parametro de entrada
        Scanner entrada = new Scanner(System.in);
        String valorEntrada = entrada.nextLine();
        //como o objeto que será testado é uma string
        //o java possui equals que retorna true se o teste for verdadeiro
 
        if (valorEntrada.equals("m")||valorEntrada.equals("M")) {
            Menu.exibirMenu();
        } else {
            
            Problema encontrado = null;
            try{
                encontrado = ProblemaControle.obterProblemaPeloCodigo(Integer.parseInt(valorEntrada));
            }catch(Exception e){
                System.out.println("Código informado não é do tipo int.");
            }
             if (encontrado == null) {
                System.out.println("Problema não encontrado.\n");
                //Menu.exibirMenu();
            } else {
                encontrado.setSituacao("Resolvido");
                System.out.println("Problema Resolvido !!!\n");
            }
        }
    }
}
