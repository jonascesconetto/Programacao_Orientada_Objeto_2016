package com.cesco.am3poocriticagastronomica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cesco.am3poocriticagastronomica.adapter.CriticaAdapterRecycle;
import com.cesco.am3poocriticagastronomica.dao.CriticaDao;
import com.cesco.am3poocriticagastronomica.modelo.Critica;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Critica> lista = CriticaDao.obterLista();
        RecyclerView rvListaCritica = (RecyclerView) findViewById(R.id.rvListagemCritica);
        RecyclerView.LayoutManager formaApresentacao = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        rvListaCritica.setLayoutManager(formaApresentacao);
        CriticaAdapterRecycle adaptador = new CriticaAdapterRecycle(this.getApplicationContext(), lista);
        rvListaCritica.setAdapter(adaptador);

    }

    public void clicouAddCriticas(View v){
        Intent abridor = new Intent(this.getApplicationContext(), CadastroActivity.class);
        startActivity(abridor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.addButton:
            Intent abridor = new Intent(this.getApplicationContext(), CadastroActivity.class);
            startActivity(abridor);
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

}
