package com.cesco.am3pooautonomia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cesco.am3pooautonomia.adapter.AbastecimentoAdapterRecycle;
import com.cesco.am3pooautonomia.dao.AbastecimentoDao;
import com.cesco.am3pooautonomia.modelo.Abastecimento;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnAddAbastecimento;
    private Button btnVisualizarAbastecimento;
    private TextView tvAutonomiaAtual;

    //VER NECESSIDADE
    private ArrayList<Abastecimento> listaAbastecimentos = AbastecimentoDao.obterLista();

    private float tKm;
    private float tL;
    private float x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddAbastecimento = (Button) findViewById(R.id.btnAddAbastecimento);
        btnVisualizarAbastecimento = (Button) findViewById(R.id.btnVisualizarAbastecimento);
        tvAutonomiaAtual = (TextView) findViewById(R.id.tvAutonomiaAtual);

        //Calculo Média
        //-----------------------------------------------------------
        x = calculoAutonomia();
        //-----------------------------------------------------------
        tvAutonomiaAtual.setText(String.valueOf(arredondar(x, 2, 0) + " Km/L"));
    }

    public float calculoAutonomia () {
        //Variavel local auxiliar
        float valorRetornado;
        if(getItemCount()==0||getItemCount()==1) {
            valorRetornado = 0;
            return valorRetornado;
        }else{
            int ultimoElemento = listaAbastecimentos.get(getItemCount()-1).getQuilometragem();
            int penultimoElemento = listaAbastecimentos.get(getItemCount()-2).getQuilometragem();
            tKm = ultimoElemento-penultimoElemento;
            tL = listaAbastecimentos.get(getItemCount()-1).getLitros();
            valorRetornado = tKm/tL;
            return valorRetornado;
        }
    }

    //  Referencia:
    //  http://www.devmedia.com.br/forum/arredondar-numero-0-885650224-para-0-89/564800
    /**
     *  1 - Valor a arredondar.
     *  2 - Quantidade de casas depois da vírgula.
     *  3 - Arredondar para cima ou para baixo?
     *          Para cima = 0 (ceil)
     *          Para baixo = 1 ou qualquer outro inteiro (floor)
     **/
    double arredondar(double valor, int casas, int ceilOrFloor) {
        double arredondado = valor;
        arredondado *= (Math.pow(10, casas));
        if (ceilOrFloor == 0) {
            arredondado = Math.ceil(arredondado);
        } else {
            arredondado = Math.floor(arredondado);
        }
        arredondado /= (Math.pow(10, casas));
        return arredondado;
    }

    public int getItemCount() {
        return this.listaAbastecimentos.size();
    }

    public void clicouNoBotaoAddAbastecimento(View v){
        Intent abridor = new Intent(this.getApplicationContext(), CadastroActivity.class);
        startActivity(abridor);
    }

    public void clicouNoBotaoVisualizarAbastecimentos(View v){
        Intent abridor = new Intent(this.getApplicationContext(), ShowAllActivity.class);
        startActivity(abridor);
    }
}
