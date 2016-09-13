
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;
import visao.Menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonas
 */
public class Principal {
    public static void main(String[] args) {
        Menu.exibirMenu();
    }
}


///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package infraestrutura_univali;
//
//import java.io.File;
//import java.io.PrintWriter;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.util.ArrayList;
//import java.util.Scanner;
//import visao.Menu;
//
///**
// *
// * @author Edgar
// */
//public class Infraestrutura_UNIVALI {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        
//        try{
//            
//            //java 7-
//        /*  File arquivo = new File("teste.txt");
//            Scanner entrada = new Scanner(arquivo);
//            int linhaAtual = 0;
//            while(entrada.hasNextLine()){
//                String linha = entrada.nextLine();
//                System.out.println(linhaAtual+": "+linha);
//                linhaAtual++;
//            }
//        
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        
//        
//            //java 7+
//            Path caminhoArquivo = Paths.get("teste.txt");
//            for( String linha : Files.readAllLines(caminhoArquivo) ){
//                System.out.println(linha);
//            }
//        
//        
//            //java 7- (escrita)
//            
//            File arquivo = new File("teste2.txt");
//            PrintWriter escritor = new PrintWriter(arquivo);
//            escritor.println("ola");
//            escritor.println("eu");
//            escritor.println("sou");
//            escritor.println("o");
//            escritor.println("Java");
//            escritor.close();
//            
//            //java 7+ (escrita)
//            
//            Path caminhoArquivo = Paths.get("teste2.txt");
//            ArrayList<String> listaLinhas = new ArrayList();
//            //listaLinhas.add("Ola");
//            //listaLinhas.add("mundo");
//            //listaLinhas.add("cruel");
//            String palavra = "Bolinha";
//            Files.write(caminhoArquivo, palavra.getBytes(), StandardOpenOption.APPEND);
//            
//         */   
//            
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        
//        //Menu.exibirMenu();
//    }
//    


