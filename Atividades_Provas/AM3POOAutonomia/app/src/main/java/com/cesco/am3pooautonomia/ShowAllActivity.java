package com.cesco.am3pooautonomia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cesco.am3pooautonomia.adapter.AbastecimentoAdapterRecycle;
import com.cesco.am3pooautonomia.dao.AbastecimentoDao;
import com.cesco.am3pooautonomia.modelo.Abastecimento;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by cesco on 19/11/16.
 */

public class ShowAllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        //Insert abastecimentos test RecicleView
//        ArrayList<Abastecimento> lista = new ArrayList<>();
//        for(int i = 0; i < 100; i++){
//            Abastecimento obj = new Abastecimento();
//
//            obj.setQuilometragem(10000 + i);
//            obj.setLitros(10 + i);
//            obj.setDataAbastecimento(1 + i);
//            obj.setPosto("Ipiranga");
//
//            lista.add(obj);
//        }

        ArrayList<Abastecimento> lista = AbastecimentoDao.obterLista();
        RecyclerView rvListaAbastecimento = (RecyclerView) findViewById(R.id.rvListaAbastecimento);
        RecyclerView.LayoutManager formaApresentacao = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        rvListaAbastecimento.setLayoutManager(formaApresentacao);
        AbastecimentoAdapterRecycle adaptador = new AbastecimentoAdapterRecycle(this.getApplicationContext(), lista);
        rvListaAbastecimento.setAdapter(adaptador);
    }
}
