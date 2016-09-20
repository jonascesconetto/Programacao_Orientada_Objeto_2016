/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import modelo.ManutencaoDao;

/**
 *
 * @author jc
 */
public class EquipamentoDao {
    //metodo para obter o número de patrimonio
    public static Equipamento obterPeloNumero(String numeroPatrimonio){
        for (Equipamento objDaVez : EquipamentoDao.obterLista()){
            if (objDaVez.getPatrimonio().equals(numeroPatrimonio)){
                return objDaVez;
            }
        }
        return null;
    }
    //quando eu mudar o armazenamento so irei alterar isso
    /*
    public static ArrayList<Equipamento> obterLista(){
        return MeioArmazenamento.MEIO_ARMAZENAMENTO_EQUIPAMENTOS;
    }*/
    public static ArrayList<Equipamento> obterLista(){    
        //para o retorno
        ArrayList<Equipamento> retorno = new ArrayList<>();
        //LEITURA DO ARQUIVO
        Path caminhoArquivo = Paths.get("armazenamento/equipamentos.txt");       
        try{
            //iterar todas as linha do arquivo
            for( String linha : Files.readAllLines(caminhoArquivo) ){
                //print de cada linha
                //System.out.println(linha);
                String[] parts = linha.split(";");
                
                Equipamento objDaVez = new Equipamento();
                objDaVez.setNome(parts[0]);
                objDaVez.setPatrimonio(parts[1]);
                //pesquisa para conversão do formato da data
                //http://stackoverflow.com/questions/4713825/how-to-parse-output-of-new-date-tostring
                DateFormat formatadorData = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                //Sun Jan 10 00:10:00 BRST 2010 --> EEE MMM dd HH:mm:ss zzz yyyy
                objDaVez.setDataAquisicao(formatadorData.parse(parts[2]));
                objDaVez.setDataTerminoGarantia(formatadorData.parse(parts[3]));
                objDaVez.setValor(Float.parseFloat(parts[4]));
                
                //setando o número de manutenções
                objDaVez.setListaManutencoes(ManutencaoDao.obterLista(objDaVez));
                retorno.add(objDaVez);
                //System.out.println(nome+"\t\t|\t"+patrimonio+"\t\t\t|\t");//+dataAquisição+"\t\t"+dataTerminoGarantia+"\t\t"+valor);
            }      
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //System.out.println("\n");
        return retorno;
    }
    
    //Salvar 
    public static void salvar(Equipamento salvarEquipamento){
        /*//ESCRITA EM VETORES
        MeioArmazenamento.MEIO_ARMAZENAMENTO_EQUIPAMENTOS.add(this);
        }*/
        //ESCRITA NO ARQUIVO
        Path caminhoArquivo = Paths.get("armazenamento/equipamentos.txt");
        //separador de uma nova linha
        String separadoLinha = System.getProperty("line.separator"); 
        //CONCATENAR TUDO EM UMA STRING
        String dadosEquipamentos = salvarEquipamento.getNome()+";"+salvarEquipamento.getPatrimonio()+";"+salvarEquipamento.getDataAquisicao().toString()+";"+salvarEquipamento.getDataTerminoGarantia().toString()+";"+salvarEquipamento.getValor()+";"+separadoLinha;
        try{
            //escrevendo os bytes no arquivo
            //StandardOpenOption.CREATE = cria um novo arquivo se não existente
            //StandardOpenOption.APPEND = adiciona um novo arquivo no formato de apendice
            Files.write(caminhoArquivo, dadosEquipamentos.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch(Exception e){
            //Logger.getLogger(Equipamento.class.getNome()).log(level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
    }
}
