/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import armazenamento.MeioArmazenamento;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jc
 */
public class Problema {
    
    private int codigo;
    private String descricao;
    private String situacao;
    private Date data;
    
     //Salvar 
    public void salvar(){
        MeioArmazenamento.MEIO_ARMAZENAMENTO_PROBLEMAS.add(this);
    }

    //Obter lista apartir do vetor
    public static ArrayList<Problema> obterLista(){
        return MeioArmazenamento.MEIO_ARMAZENAMENTO_PROBLEMAS;
    }
    
    //Método para obter o problema apartir do código
    public static Problema obterProblemaPeloCodigo(int codigoProblema){       
        for (Problema objDaVez : Problema.obterLista()){
            if (objDaVez.getCodigo() == codigoProblema){
                return objDaVez;
            }
        }
        return null;
    }
    
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }
}
