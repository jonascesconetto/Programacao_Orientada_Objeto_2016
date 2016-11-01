package com.cesco.am2pooareaformasgeometricas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cesco on 29/10/16.
 */

public class AreaCalculoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_calculo);

        //Variaveis de carregamento
        //Resultado
        double resultado = getIntent().getDoubleExtra("resultadoEnviado", -1);
        //Tipo de Forma
        int fomaSelecionada = getIntent().getIntExtra("tipoForma", 0);

        //Mostrando para o usuario resultado e imagem da forma selecionada na primeira activity

        //achando os ids do elementos do xml
        ImageView imageViewFiguraForma = (ImageView) findViewById(R.id.imageViewFiguraForma);
        TextView textViewResultadoCalculado = (TextView) findViewById(R.id.textViewResultadoCalculado);

        //setando os dados que serão mostrados

        if (fomaSelecionada == 1){
            imageViewFiguraForma.setImageDrawable(getDrawable(R.drawable.retangulo));
        }
        if(fomaSelecionada == 2){
            imageViewFiguraForma.setImageDrawable(getDrawable(R.drawable.triangulo));
        }
        if(fomaSelecionada == 3){
            imageViewFiguraForma.setImageDrawable(getDrawable(R.drawable.circulo));
        }
        textViewResultadoCalculado.setText("Área = " + resultado + "cm²");

    }

    public void clicouReiniciar(View origemDoClique) {

        //Mudar para outra activity
        Intent abridor = new Intent(this.getApplicationContext(), MainActivity.class);

        //iniciando outra activity
        startActivity(abridor);
    }
}