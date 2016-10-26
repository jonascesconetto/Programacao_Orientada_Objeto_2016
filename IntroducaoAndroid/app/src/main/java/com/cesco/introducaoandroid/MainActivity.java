package com.cesco.introducaoandroid;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private Button salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.etNome);
        salvar = (Button) findViewById(R.id.btnSalvar);


        //Toast.makeText(this.getApplicationContext(), nome.getText(), Toast.LENGTH_SHORT).show();
    }




}
