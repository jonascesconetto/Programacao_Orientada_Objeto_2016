/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import armazenamento.MeioArmazenamento;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class Equipamento {
    private String nome;
    private String patrimonio;
    private Date dataAquisicao;
    private Date dataTerminoGarantia;
    private float valor;
    //historico de manutenções
    private ArrayList<Manutencao> listaManutencoes;
    
    //Construtor para iniciar alguns atributo, para evita erro Nullpoint
    public Equipamento(){
        this.listaManutencoes = new ArrayList();
    }
    
    //metodo para obter o número de patrimonio
    public static Equipamento obterPeloNumero(String numeroPatrimonio){
        for (Equipamento objDaVez : Equipamento.obterLista()){
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
        //LEITURA NO ARQUIVO
        Path caminhoArquivo = Paths.get("dados.txt");
        
        try{
            for( String linha : Files.readAllLines(caminhoArquivo) ){
                //System.out.println(linha);
                linha.toString();
                
                String[] parts = linha.split(";");
        
                String nome = parts[0];
                String patrimonio = parts[1]; // 034556
                String dataAquisição = parts[2];
                String dataTerminoGarantia = parts[3];
                String valor = parts[4]; 
                
                System.out.println(nome+"\t\t|\t"+patrimonio+"\t\t\t|\t");//+dataAquisição+"\t\t"+dataTerminoGarantia+"\t\t"+valor);
                
            }      
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
        //System.out.println(caminhoArquivo.toString());
        
        /*
        String test = caminhoArquivo.toString();
        String[] parts = test.split(";");
        String nome = parts[0]; // 004
        String patrimonio = parts[1]; // 034556
        String dataAquisição = parts[2];
        String dataTerminoGarantia = parts[3];
        String valor = parts[4]; 
        System.out.println("Test: "+nome+" ");
        */
       
        return MeioArmazenamento.MEIO_ARMAZENAMENTO_EQUIPAMENTOS;
    
    }
    
    //Salvar 
    public void salvar(){
        
        //MeioArmazenamento.MEIO_ARMAZENAMENTO_EQUIPAMENTOS.add(this);
        
        //ESCRITA NO ARQUIVO
        Path caminhoArquivo = Paths.get("dados.txt");
        String separadoLinha = System.getProperty("line.separator"); 
        String dadosEquipamentos = this.getNome()+";"+this.getPatrimonio()+";"+this.getDataAquisicao().toString()+";"+getDataTerminoGarantia().toString()+";"+getValor()+";"+separadoLinha;
        
        try{
            Files.write(caminhoArquivo, dadosEquipamentos.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch(Exception e){
            
        }
    
    }
    
    //criar um metodo para acessar as manutenções em uma outra classe
    public ArrayList<Manutencao> getListaManutencoes(){
        return this.listaManutencoes;
    }
    
    //criar um método adicionar manutenções em um equipamento
    public void adicionaManutencao(Manutencao manutencao){
        this.listaManutencoes.add(manutencao);
    }
    
    //criar um metodo para somar o total gasto com manutenções
    public float getTotalGastoManutencao(){
        //criar uma variavel de para receber o total
        float total = 0;
        //laço para varrer o vetor de manutenções
        for(Manutencao atual : this.listaManutencoes){
            total += atual.getValor();
        }
        return total;
    }
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome O nome para atribuir ao equipamento
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the patrimonio
     */
    public String getPatrimonio() {
        return patrimonio;
    }

    /**
     * @param patrimonio the patrimonio to set
     */
    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    /**
     * @return the dataAquisicao
     */
    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    /**
     * @param dataAquisicao the dataAquisicao to set
     */
    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    /**
     * @return the dataTerminoGarantia
     */
    public Date getDataTerminoGarantia() {
        return dataTerminoGarantia;
    }

    /**
     * @param dataTerminoGarantia the dataTerminoGarantia to set
     */
    public void setDataTerminoGarantia(Date dataTerminoGarantia) {
        this.dataTerminoGarantia = dataTerminoGarantia;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    
}
