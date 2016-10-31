package com.cesco.am2pooareaformasgeometricas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by cesco on 29/10/16.
 */

public class AreaCalculoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_calculo);

        double resultado = getIntent().getDoubleExtra("resultadoEnviado", -1);

        TextView textViewResultadoCalculado = (TextView) findViewById(R.id.textViewResultadoCalculado);
        textViewResultadoCalculado.setText("Área = " + resultado + "m²");
    }
}