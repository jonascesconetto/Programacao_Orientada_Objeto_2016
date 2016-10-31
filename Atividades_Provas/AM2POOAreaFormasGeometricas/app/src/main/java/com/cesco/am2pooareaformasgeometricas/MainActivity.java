package com.cesco.am2pooareaformasgeometricas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    //Declaração dos parametros
    private RadioGroup radioGroupSelForma;
    private RadioButton radioButtonRetangulo;
    private RadioButton radioButtonTriangulo;
    private RadioButton radioButtonCirculo;
    private Button buttonAvancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Itens da Seleção de Formma
        radioGroupSelForma = (RadioGroup) findViewById(R.id.radioGroupSelForma);
        radioButtonRetangulo = (RadioButton) findViewById(R.id.radioButtonRetangulo);
        radioButtonTriangulo = (RadioButton) findViewById(R.id.radioButtonTriangulo);
        radioButtonCirculo = (RadioButton) findViewById(R.id.radioButtonCirculo);
        //Button
        buttonAvancar = (Button) findViewById(R.id.buttonAvancar);


//        buttonAvancar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this.getApplicationContext(), "Ola mundo", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    public void clicouNoAvancar (View origemDoClique) {

        int idRadioButtonSel = radioGroupSelForma.getCheckedRadioButtonId();
        RadioButton opcaoSelecionada = (RadioButton) findViewById(idRadioButtonSel);

        if (opcaoSelecionada.getText().toString().equals("Retângulo")) {
            //Caminho
            Intent abridor = new Intent(this.getApplicationContext(), RetanguloActivity.class);
            startActivity(abridor);
            //Mensagem
            Toast.makeText(MainActivity.this.getApplicationContext(), "Retângulo Selecionado", Toast.LENGTH_SHORT).show();
        }

        if (opcaoSelecionada.getText().toString().equals("Triângulo")) {
            // Caminho
            Intent abridor = new Intent(this.getApplicationContext(), TrianguloActivity.class);
            startActivity(abridor);
            //Mensagem
            Toast.makeText(MainActivity.this.getApplicationContext(), "Triângulo Selecionado", Toast.LENGTH_SHORT).show();
        }

        if (opcaoSelecionada.getText().toString().equals("Circulo")) {
            //Caminho
            Intent abridor = new Intent(this.getApplicationContext(), CirculoActivity.class);
            startActivity(abridor);
            //Mensagem
            Toast.makeText(MainActivity.this.getApplicationContext(), "Circulo Selecionado", Toast.LENGTH_SHORT).show();
        }

    }

}

// Deu certo
//    public void clicouNoAvancar(View origemDoClique){
//
//        Intent abridor = new Intent(this, RetanguloActivity.class);
//        startActivity(abridor);
//    }

//    public void clicouNoAvancar (View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();

//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radioButtonRetangulo:
//                if (checked) {
//                    // Pirates are the best
//                    Intent vaiParaOutroLayout = new Intent(this.getApplicationContext(), RetanguloActivity.class);
//                    startActivity(vaiParaOutroLayout);
//                }
//                    break;
//            case R.id.radioButtonTriangulo:
//                if (checked)
//                    // Ninjas rule
//                    Toast.makeText(MainActivity.this.getApplicationContext(), "Ola mundo", Toast.LENGTH_SHORT).show();
//                    break;
//        }
//    }

    //Segunda alternativa
//    radioGroupSelForma.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            idOpcaoSelecionada = checkedId;
//        }
//    });

//    buttonAvancar.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if (idOpcaoSelecionada == R.id.radioButtonRetangulo){
//                Intent vaiParaOutroLayout = new Intent(getBaseContext().RetanguloActivity.class);
//                startActivity(vaiParaOutroLayout);
//            }
//        }
//    });

    //É pra dar certo
//    public void clicouNoAvancar(View origemDoClique) {
//
//        //Verificando a seleção do RadioGroup
//        int idOpcaoSelecionada = radioGroupSelForma.getCheckedRadioButtonId();
//        RadioButton opcaoSelecionada = (RadioButton) findViewById(idOpcaoSelecionada);
//
//        //Tratamento da forma selecionada
//        if (opcaoSelecionada.getText().toString().equals(R.id.radioButtonRetangulo)) {
//            Intent abridor = new Intent(this, RetanguloActivity.class);
//            startActivity(abridor);
//        }
//    }

