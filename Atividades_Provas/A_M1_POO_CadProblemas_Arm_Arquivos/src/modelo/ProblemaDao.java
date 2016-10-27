/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import armazenamento.MeioArmazenamento;
import controle.ProblemaControle;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author jc
 */
public class ProblemaDao {
    
    //Método para obter o problema apartir do código
    public static Problema obterProblemaPeloCodigo(int codigoProblema){
        for (Problema objDaVez : ProblemaDao.obterLista()){
            if (objDaVez.getCodigo() == codigoProblema){
                return objDaVez;
            }
        }
        return null;
    }
    
    //Obter lista apartir do arquivo
    public static ArrayList<Problema> obterLista(){
        //para o retorno
        ArrayList<Problema> retorno = new ArrayList<>();
        //LEITURA DO ARQUIVO
        Path caminhoArquivo = Paths.get("armazenamento/problemas.txt");       
        try{
            //iterar todas as linha do arquivo
            for( String linha : Files.readAllLines(caminhoArquivo) ){
                
                String[] parts = linha.split(";");
                Problema problemaVez = new Problema();
                DateFormat formatadorData = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");//, Locale.US);
                
                problemaVez.setCodigo(Integer.parseInt(parts[0]));
                
                problemaVez.setData(formatadorData.parse(parts[1]));
                problemaVez.setDescricao(parts[2]);
                problemaVez.setSituacao(parts[3]);
                retorno.add(problemaVez);
            }      
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //System.out.println("\n");
        return retorno;
    }
    
     //Obter lista apartir do vetor
    public static int obterNumeroProblemas(){
        
        int numeroProblemas=0;
        //LEITURA DO ARQUIVO
        Path caminhoArquivo = Paths.get("armazenamento/problemas.txt");       
        try{
            for( String linha : Files.readAllLines(caminhoArquivo) ){
                numeroProblemas += 1;
            }
         
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //System.out.println("\n");
        return numeroProblemas;
    }
    
    public static void alteraSituacao(int codigoLinha) throws IOException{
        
        ArrayList<Problema> vetorTemporario = new ArrayList<>();
        String situacaoAtual = "Resolvido";
        
        for (Problema obj : ProblemaControle.obterListaProblemas()){
            
            if(codigoLinha != obj.getCodigo()){
                
                vetorTemporario.add(obj);
                
            }else{ 
                obj.setSituacao(situacaoAtual);
                vetorTemporario.add(obj);
            }    
        
        }
        
        Path caminhoArquivo = Paths.get("armazenamento/problemas.txt");
        String separadorLinha = System.getProperty("line.separator"); 
        Files.delete(caminhoArquivo);
        for(int i = 0; i<vetorTemporario.size();i++){
            String dadosProblema = vetorTemporario.get(i).getCodigo()+";"+vetorTemporario.get(i).getData().toString()+";"+vetorTemporario.get(i).getDescricao()+";"+vetorTemporario.get(i).getSituacao()+";"+separadorLinha;
        
            try{                
                Files.write(caminhoArquivo, dadosProblema.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        
        
    }
    
    public static void salvar(Problema salvarEquipamento){
        
        Path caminhoArquivo = Paths.get("armazenamento/problemas.txt");
        String separadorLinha = System.getProperty("line.separator"); 
        String dadosProblema = salvarEquipamento.getCodigo()+";"+salvarEquipamento.getData().toString()+";"+salvarEquipamento.getDescricao()+";"+salvarEquipamento.getSituacao()+";"+separadorLinha;
        try{
            Files.write(caminhoArquivo, dadosProblema.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
