package com.cesco.am2pooareaformasgeometricas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by cesco on 29/10/16.
 */

public class CirculoActivity extends Activity {

    //Compos para inserção
    private EditText editTextRaioCirculo;
    private Button buttonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circulo);

        editTextRaioCirculo = (EditText) findViewById(R.id.editTextRaioCirculo);
        buttonCalcular = (Button) findViewById(R.id.buttonCalcular);

    }

    public void clicouCalcular(View origemDoClique) {

        //Mudar para outra activity
        Intent abridor = new Intent(this.getApplicationContext(), AreaCalculoActivity.class);

        //Parametros para empacotamento
        double raio = Double.parseDouble(editTextRaioCirculo.getText().toString());

        //  Calculo para Triangulo
        double resultado = (Math.PI * Math.pow(raio,2));

        //colocando na sacola valor arredondado para cima
        abridor.putExtra("resultadoEnviado", arredondar(resultado,2,0));

        //iniciando outra activity
        startActivity(abridor);
    }

    //  Parâmetros:
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
}