package com.magnani.introducaointerfaces;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.magnani.introducaointerfaces.adapter.PessoaAdapter;
import com.magnani.introducaointerfaces.adapter.PessoaAdapterRecycle;
import com.magnani.introducaointerfaces.dao.PessoaDao;
import com.magnani.introducaointerfaces.modelo.Pessoa;

import java.util.ArrayList;

public class ShowAllActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        ArrayList<Pessoa> lista = PessoaDao.obterLista();
//        ArrayList<Pessoa> lista = new ArrayList<>();
//        for(int i = 0; i < 100; i++){
//            Pessoa obj = new Pessoa();
//            obj.setNome("Pessoa "+i);
//            obj.setSobrenome("Sobrenome "+i);
//            obj.setIdade(i);
//            String sexo = (Math.random() < 0.5 ? "M" : "F");
//            obj.setSexo(sexo);
//            lista.add(obj);
//        }


        //List view
//        ListView lvListaPessoas = (ListView) findViewById(R.id.lvListaPessoas);
////        ArrayAdapter<Pessoa> adaptador = new ArrayAdapter<Pessoa>(
////                this.getApplicationContext(),
////                android.R.layout.simple_list_item_1,
////                PessoaDao.obterLista()
////        );
//        PessoaAdapter adaptador = new PessoaAdapter(this.getApplicationContext(), 0, lista);
//        lvListaPessoas.setAdapter(adaptador);

        RecyclerView rvListaPessoas = (RecyclerView) findViewById(R.id.rvListaPessoas);

        RecyclerView.LayoutManager formaApresentacao = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL,      false);

//        RecyclerView.LayoutManager formaApresentacao = new GridLayoutManager(this.getApplicationContext(), 2);
        rvListaPessoas.setLayoutManager(formaApresentacao);
        PessoaAdapterRecycle adaptador = new PessoaAdapterRecycle(this.getApplicationContext(), lista);
        rvListaPessoas.setAdapter(adaptador);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
