package com.cesco.am3pooautonomia;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

import com.cesco.am3pooautonomia.adapter.Masks;
import com.cesco.am3pooautonomia.dao.AbastecimentoDao;
import com.cesco.am3pooautonomia.modelo.Abastecimento;

/**
 * Created by cesco on 20/11/16.
 */

public class CadastroActivity extends AppCompatActivity {

    private EditText etQuilometragemAtual;
    private EditText etLitrosAbastecidos;
    private EditText etDataAbastecimento;
    private Spinner spinnerSelecionaPosto;
    private Button btnConfirmar;

    //Especial para data
    private DatePickerDialog dpDataAbastecimento;
    private SimpleDateFormat dataEditada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etQuilometragemAtual = (EditText) findViewById(R.id.etQuilometragemAtual);
        etLitrosAbastecidos = (EditText) findViewById(R.id.etLitrosAbastecidos);
        etDataAbastecimento = (EditText) findViewById(R.id.etDataAbastecimento);
        //Mask
        etDataAbastecimento.addTextChangedListener(Masks.insert("##/##/####", etDataAbastecimento));

        //Spinner Seleção do Posto
        spinnerSelecionaPosto = (Spinner) findViewById(R.id.spinnerSelecionaPosto);
        ArrayAdapter adapterSpinnerPosto = ArrayAdapter.createFromResource(this, R.array.spItensPosto, android.R.layout.simple_spinner_dropdown_item);
        spinnerSelecionaPosto.setAdapter(adapterSpinnerPosto);
        //Btn Confirmar
        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
    }

    public void clicouNoConfirmar(View v){

        //Variaveis Locais e auxiliares
        Abastecimento ab = new Abastecimento();
        int ultimoAbastecimentoLista = AbastecimentoDao.obterLista().size();
        float valor;
        //Controlando a posição
        if(ultimoAbastecimentoLista ==0){
            valor = 0;
        }else{
            valor = AbastecimentoDao.obterLista().get(ultimoAbastecimentoLista -1).getQuilometragem();
        }

        //Validação e set Quilometragem
        if(etQuilometragemAtual.getText().toString().equals("")){
            String mensagemErroKm = getResources().getString(R.string.infoErroInsertKm);
            Toast.makeText(this.getApplicationContext(), mensagemErroKm, Toast.LENGTH_SHORT).show();
            return;
        }else{//Teste da Posição final
            if(Integer.parseInt(etQuilometragemAtual.getText().toString()) > valor){
                ab.setQuilometragem(Integer.parseInt(etQuilometragemAtual.getText().toString()));
            }else{
                String mensagemErroKmSuperior = getResources().getString(R.string.infoErroInsertKmSuperior);
                Toast.makeText(this.getApplicationContext(), mensagemErroKmSuperior, Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //Valida e set Litros
        if(etLitrosAbastecidos.getText().toString().equals("")){
            String mensagemErroLitros = getResources().getString(R.string.infoErroInsertLitros);
            Toast.makeText(this.getApplicationContext(), mensagemErroLitros, Toast.LENGTH_SHORT).show();
            return;
        }else {
            ab.setLitros(Float.parseFloat(etLitrosAbastecidos.getText().toString()));
        }

        //Valida e Set Data
        if(etDataAbastecimento.getText().toString().equals("")){
            String mensagemErroData= getResources().getString(R.string.infoErroInsertData);
            Toast.makeText(this.getApplicationContext(), mensagemErroData, Toast.LENGTH_SHORT).show();
            return;
        }else {
            ab.setDataAbastecimento(etDataAbastecimento.getText().toString());
        }

        //Valida e set POSTO
        ab.setPosto(spinnerSelecionaPosto.toString().trim());
        //Spinner selecionado = (Spinner) findViewById(R.id.spinnerSelecionaPosto);
        if(spinnerSelecionaPosto.getSelectedItem().toString().trim().equals("Informe a bandeira do Posto")){
            String mensagemErroPosto = getResources().getString(R.string.infoErroInsertPosto);
            Toast.makeText(this.getApplicationContext(), mensagemErroPosto, Toast.LENGTH_SHORT).show();
            return;
        }else {
            if(spinnerSelecionaPosto.getSelectedItem().toString().trim().equals("Texaco")){
                ab.setPosto("Texaco");
            }else{
                if(spinnerSelecionaPosto.getSelectedItem().toString().trim().equals("Shell")) {
                    ab.setPosto("Shell");
                }else{
                    if(spinnerSelecionaPosto.getSelectedItem().toString().trim().equals("Petrobras")) {
                        ab.setPosto("Petrobras");
                    } else{
                        if(spinnerSelecionaPosto.getSelectedItem().toString().trim().equals("Ipiranga")) {
                            ab.setPosto("Ipiranga");
                        }else{
                            ab.setPosto("Outros");
                        }
                    }
                }
            }
        }

        Intent abridor = new Intent(this.getApplicationContext(), ConfirmaDadosCadastroActivity.class);
        abridor.putExtra("abastecimento", ab);
        startActivity(abridor);
    }

    @Override
    public void onPause(){
        super.onPause();
        finish();
    }
}