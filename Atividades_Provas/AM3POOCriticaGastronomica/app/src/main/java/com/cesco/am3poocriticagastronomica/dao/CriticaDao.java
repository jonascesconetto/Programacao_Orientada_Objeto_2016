package com.cesco.am3poocriticagastronomica.dao;

import com.cesco.am3poocriticagastronomica.modelo.Critica;

import java.util.ArrayList;

/**
 * Created by cesco on 07/12/16.
 */

public class CriticaDao {

    private static ArrayList<Critica> listaCriticas;

    private static void inicializarLista(){
        if(CriticaDao.listaCriticas == null){
            CriticaDao.listaCriticas = new ArrayList<>();
        }
    }

    public static void salvar(Critica pessoa){
        inicializarLista();
        listaCriticas.add(pessoa);
    }

    public static ArrayList<Critica> obterLista(){
        inicializarLista();
        return  listaCriticas;
    }
}
