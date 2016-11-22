package com.cesco.am3pooautonomia.dao;

import com.cesco.am3pooautonomia.modelo.Abastecimento;

import java.util.ArrayList;

/**
 * Created by cesco on 20/11/16.
 */

public class AbastecimentoDao {

    private static ArrayList<Abastecimento> listaAbastecimentos;

    private static void inicializarLista(){
        if(AbastecimentoDao.listaAbastecimentos == null){
            AbastecimentoDao.listaAbastecimentos = new ArrayList<>();
        }
    }

    public static void salvar(Abastecimento abastecimento){
        inicializarLista();
        listaAbastecimentos.add(abastecimento);
    }

    public static ArrayList<Abastecimento> obterLista(){
        inicializarLista();
        return listaAbastecimentos;
    }
}
