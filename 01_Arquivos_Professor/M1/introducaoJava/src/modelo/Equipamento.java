/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author marcelo
 */
public class Equipamento {
    private String nome;
    private String patrimonio;
    private Date dataAquisicao;
    private Date dataTerminoGarantia;
    private float valor;
    private ArrayList<Manutencao> listaManutencoes;
    
    public String toString(){
        return this.getNome()+" - "+this.getPatrimonio();
    }
    
    
    public Equipamento(){
        this.listaManutencoes = new ArrayList();
    }
    
    public void setListaManutencoes(ArrayList<Manutencao> listaManutencoes){
        this.listaManutencoes = listaManutencoes;
    }
    
    public ArrayList<Manutencao> getListaManutencoes(){
        return listaManutencoes;
    }
    
    public void adicionaManutencao(Manutencao manutencao){
        this.listaManutencoes.add(manutencao);
    }
    
    public float getTotalGastoManutencoes(){
        float total = 0;
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
