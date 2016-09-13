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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

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
