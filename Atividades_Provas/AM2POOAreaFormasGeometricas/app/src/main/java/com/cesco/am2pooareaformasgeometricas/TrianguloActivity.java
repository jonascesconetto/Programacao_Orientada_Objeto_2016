package com.cesco.am2pooareaformasgeometricas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by cesco on 29/10/16.
 */

public class TrianguloActivity extends Activity {

    //Compos para inserção
    private EditText editTextBaseTriangulo;
    private EditText editTextAlturaTriangulo;
    private Button buttonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangulo);

        editTextBaseTriangulo = (EditText) findViewById(R.id.editTextBaseTriangulo);
        editTextAlturaTriangulo = (EditText) findViewById(R.id.editTextAlturaTriangulo);
        buttonCalcular = (Button) findViewById(R.id.buttonCalcular);
    }


    public void clicouCalcular (View origemDoClique) {
        //controle da forma para fazer uma seleção da imagem em outra activity
        // 2 = triangulo
        int forma = 2;

        if (editTextBaseTriangulo.getText().toString().equals("") || editTextAlturaTriangulo.getText().toString().equals("")) {
            Toast.makeText(TrianguloActivity.this.getApplicationContext(), "Dados Vazios apresentados !", Toast.LENGTH_SHORT).show();
        } else {
            //Mudar para outra activity
            Intent abridor = new Intent(this.getApplicationContext(), AreaCalculoActivity.class);

            //Parametros para empacotamento
            double base = Double.parseDouble(editTextBaseTriangulo.getText().toString());
            double altura = Double.parseDouble(editTextAlturaTriangulo.getText().toString());

            //  Calculo para Triangulo
            double resultado = (base * altura) / 2;

            //colocando na sacola
            abridor.putExtra("resultadoEnviado", resultado);
            abridor.putExtra(AreaCalculoActivity.FORMA, forma);

            //iniciando outra activity
            startActivity(abridor);
        }
    }
}