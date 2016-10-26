package modelo;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import modelo.Equipamento;
import modelo.Manutencao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jc
 */
public class ManutencaoDao {
    
    public static ArrayList<Manutencao> obterLista(Equipamento obtDaVez){    
        
        //MESMA COISA QUE EQUIPAMENTO DAO, POREM ALTERAMOS O TIPO DO OBJETO
        ArrayList<Manutencao> retorno = new ArrayList<>();
               
        try{
            Path localManutencoes = Paths.get("armazenamento/manutencoes_equipamento_"+obtDaVez.getPatrimonio()+".txt");
            if(localManutencoes.toFile().exists()){
                for( String linhaManutencao : Files.readAllLines(localManutencoes)){
                    //Quebra dos separadores
                    String partsManutencoes[] = linhaManutencao.split(";");
                    
                    Manutencao manutencaoDaVez = new Manutencao();
                    
                    manutencaoDaVez.setDescricao(partsManutencoes[0]);
                    
                    DateFormat formatador = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    
                    manutencaoDaVez.setData(formatador.parse(partsManutencoes[1]));
                    
                    manutencaoDaVez.setValor(Float.parseFloat(partsManutencoes[2]));
                    
                    retorno.add(manutencaoDaVez);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return retorno;
    }
    
    //Salvar 
    public static void salvar(Equipamento obtDaVez){
        
        try{
        Path local = Paths.get("armazenamento/manutencoes_equipamento_"+obtDaVez.getPatrimonio()+".txt");
        String separadorLinha = System.getProperty("line.separator");
        String manutencoesString = "";
        
        for(Manutencao manutencao : obtDaVez.getListaManutencoes()){
            manutencoesString = manutencoesString + manutencao.getDescricao()+";"+manutencao.getData()+";"+manutencao.getValor()+separadorLinha;
        }
        //http://stackoverflow.com/questions/19794101/how-to-overwrite-file-via-java-nio-writer
        Files.write(local, manutencoesString.getBytes());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
