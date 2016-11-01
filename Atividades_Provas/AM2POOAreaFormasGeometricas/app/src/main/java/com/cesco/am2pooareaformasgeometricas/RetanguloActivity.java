package com.cesco.am2pooareaformasgeometricas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by cesco on 28/10/16.
 */

public class RetanguloActivity extends Activity{

    //Compos para inserção
    private EditText editTextBaseRetangulo;
    private EditText editTextAlturaRetangulo;
    private Button buttonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retangulo);

        editTextBaseRetangulo = (EditText) findViewById(R.id.editTextBaseRetangulo);
        editTextAlturaRetangulo = (EditText) findViewById(R.id.editTextAlturaRetangulo);
        buttonCalcular = (Button) findViewById(R.id.buttonCalcular);
    }

    public void clicouCalcular (View origemDoClique){
        //controle da forma para fazer uma seleção da imagem em outra activity
        // 3 = circulo
        int forma = 1;

        //Mudar para outra activity
        Intent abridor = new Intent(this.getApplicationContext(), AreaCalculoActivity.class);

        //Parametros para empacotamento
        double base = Double.parseDouble(editTextBaseRetangulo.getText().toString());
        double altura = Double.parseDouble(editTextAlturaRetangulo.getText().toString());

        validaCampo(editTextBaseRetangulo);
        validaCampo(editTextAlturaRetangulo);

        //  Calculo para Triangulo
        double resultado = base*altura;

        //colocando na sacola
        abridor.putExtra("resultadoEnviado", resultado);
        abridor.putExtra("tipoDeForma", forma);

        //iniciando outra activity
        startActivity(abridor);
    }

    protected void validaCampo( EditText origem){
        if ( origem.getText().toString().equals(null)) {
            Toast.makeText(this, "Data inválida!", Toast.LENGTH_LONG).show();
        }
    }
}
