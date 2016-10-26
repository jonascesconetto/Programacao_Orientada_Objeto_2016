/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcelo
 */
public class ManutencaoDao {

    
    public static ArrayList<Manutencao> obterLista(Equipamento equipamento){
        ArrayList<Manutencao> retorno = new ArrayList<>();
        try {
            Path localManutencoes = Paths.get("armazenamento/manutencoes_equipamento_"+equipamento.getPatrimonio()+".txt");
            if(localManutencoes.toFile().exists()){
                for(String linhaManutencao : Files.readAllLines(localManutencoes)){
                    
                    String dadosManutencao[] = linhaManutencao.split(";");
                    
                    Manutencao manutencao = new Manutencao();
                    
                    manutencao.setDescricao(dadosManutencao[0]);
                    
                    DateFormat formatador = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                    
                    manutencao.setData(formatador.parse(dadosManutencao[1]));
                    
                    manutencao.setValor(Float.parseFloat(dadosManutencao[2]));
                    
                    retorno.add(manutencao);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ManutencaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public static void salvar(Equipamento equipamento){
        try {
            Path local = Paths.get("armazenamento/manutencoes_equipamento_"+equipamento.getPatrimonio()+".txt");
            String separadorLinha = System.getProperty("line.separator");
            String manutencoesString = "";
            
            for(Manutencao manutencao : equipamento.getListaManutencoes()){
                 manutencoesString = manutencoesString + manutencao.getDescricao()+";"+manutencao.getData()+";"+manutencao.getValor()+separadorLinha;
            }
            //http://stackoverflow.com/questions/19794101/how-to-overwrite-file-via-java-nio-writer
            Files.write(local, manutencoesString.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(ManutencaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
