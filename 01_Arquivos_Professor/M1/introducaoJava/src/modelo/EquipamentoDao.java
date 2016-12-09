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
import java.nio.file.StandardOpenOption;
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
public class EquipamentoDao {

    public static Equipamento obterPeloNumero(String numeroPatrimonio){
        for(Equipamento objDaVez : EquipamentoDao.obterLista()){
            if(objDaVez.getPatrimonio().equals(numeroPatrimonio)){
                return objDaVez;
            }
        }
        return null;
    }
    
    public static ArrayList<Equipamento>  obterLista(){
        ArrayList<Equipamento> retorno = new ArrayList<>();
        try {
            Path local = Paths.get("armazenamento/equipamentos.txt");
            for(String linha : Files.readAllLines(local)){
                String dados[] = linha.split(";");
                Equipamento equipamento = new Equipamento();
                equipamento.setNome(dados[0]);
                equipamento.setPatrimonio(dados[1]);
                //http://stackoverflow.com/questions/4713825/how-to-parse-output-of-new-date-tostring
                DateFormat formatador = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                equipamento.setDataAquisicao(formatador.parse(dados[2]));
                equipamento.setDataTerminoGarantia(formatador.parse(dados[3]));
                equipamento.setValor(Float.parseFloat(dados[4]));

                equipamento.setListaManutencoes(ManutencaoDao.obterLista(equipamento));
                
                retorno.add(equipamento);
            }
        } catch (Exception ex) {
            Logger.getLogger(EquipamentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public static void salvar(Equipamento equipamento){
        try {
            Path local = Paths.get("armazenamento/equipamentos.txt");
            String separadorLinha = System.getProperty("line.separator");
            String equipamentoString = equipamento.getNome()+";"+equipamento.getPatrimonio()+";"+equipamento.getDataAquisicao().toString()+";"+equipamento.getDataTerminoGarantia().toString()+";"+equipamento.getValor()+separadorLinha;
            Files.write(local, equipamentoString.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(EquipamentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        
}
