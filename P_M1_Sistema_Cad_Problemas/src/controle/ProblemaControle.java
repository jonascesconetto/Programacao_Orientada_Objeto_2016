/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import java.util.Date;
import modelo.Problema;
import modelo.ProblemaDao;

/**
 *
 * @author jc
 */
public class ProblemaControle {
    public static void receberDadosProblema(int codigo, String descricao, String situacao, Date dataReportagemProblema){
        
        Problema objProblema = new Problema();
        //atribuição dos parametros para avariavel
        objProblema.setCodigo(codigo);
        objProblema.setData(dataReportagemProblema);
        objProblema.setDescricao(descricao);
        objProblema.setSituacao(situacao);
        ProblemaDao.salvar(objProblema);    
    }
    
    public static ArrayList<Problema> obterListaProblemas(){
        //obtenho uma lista dos equipamentos
        return ProblemaDao.obterLista();
    }
    
    public static Problema obterProblemaPeloCodigo(int codigoProblema){
        return ProblemaDao.obterProblemaPeloCodigo(codigoProblema);
    }

}
