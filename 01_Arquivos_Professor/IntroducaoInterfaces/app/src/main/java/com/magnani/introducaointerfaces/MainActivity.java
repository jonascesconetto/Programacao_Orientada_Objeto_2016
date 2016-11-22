package com.magnani.introducaointerfaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.magnani.introducaointerfaces.modelo.Pessoa;

public class MainActivity extends Activity {

    private EditText etNome;
    private EditText etSobrenome;
    private EditText etIdade;
    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private RadioGroup rgSexo;
    private Button btSalvar;
    private LinearLayout llTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = (EditText) findViewById(R.id.etNome);
        etSobrenome = (EditText) findViewById(R.id.etSobrenome);
        etIdade = (EditText) findViewById(R.id.etIdade);
        btSalvar = (Button) findViewById(R.id.btSalvar);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        rbFeminino = (RadioButton) findViewById(R.id.rbFeminino);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
        llTela = (LinearLayout) findViewById(R.id.llTela);

        /*
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this.getApplicationContext(), "Ola mundo", Toast.LENGTH_SHORT).show();
            }
        });
        */
    }

    private boolean formularioValido(){
        if(etNome.getText().toString().trim().equals("")){
            return false;
        }
        if(etSobrenome.getText().toString().trim().equals("")){
            return false;
        }
        try{
            Integer.parseInt(etIdade.getText().toString());
        }catch (Exception e){
            return false;
        }
        if(rgSexo.getCheckedRadioButtonId() == -1){
            return false;
        }
        return true;
    }

    public void clicouNoBotao(View origemDoClique){
        if(!formularioValido()){
            String mensagemInternacionalizada = getResources().getString(R.string.formulario_invalido);
            Toast.makeText(this.getApplicationContext(), mensagemInternacionalizada, Toast.LENGTH_SHORT).show();
        }
        Pessoa p = new Pessoa();
        p.setNome(etNome.getText().toString().trim());
        p.setSobrenome(etSobrenome.getText().toString().trim());
        p.setIdade(Integer.parseInt(etIdade.getText().toString()));
        RadioButton selecionado = (RadioButton) findViewById(rgSexo.getCheckedRadioButtonId());
        if(selecionado == rbMasculino){
            p.setSexo("M");
        }else{
            p.setSexo("F");
        }

        Intent abridor = new Intent(this.getApplicationContext(), ConfirmSaveActivity.class);
        abridor.putExtra("pessoa", p);
        startActivity(abridor);
    }


    public void clicouNoBotaoMostrarTodos(View v){
        Intent abridor = new Intent(this.getApplicationContext(), ShowAllActivity.class);
        startActivity(abridor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
