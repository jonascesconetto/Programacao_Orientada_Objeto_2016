package com.magnani.introducaointerfaces.dao;

import com.magnani.introducaointerfaces.modelo.Pessoa;

import java.util.ArrayList;

/**
 * Created by marcelo on 11/8/16.
 */
public class PessoaDao {

    private static  ArrayList<Pessoa> listaPessoas;


    private static void inicializarLista(){
        if(PessoaDao.listaPessoas == null){
            PessoaDao.listaPessoas = new ArrayList<>();
        }
    }

    public static void salvar(Pessoa pessoa){
        inicializarLista();
        listaPessoas.add(pessoa);
    }

    public static ArrayList<Pessoa> obterLista(){
        inicializarLista();
        return  listaPessoas;
    }


}
