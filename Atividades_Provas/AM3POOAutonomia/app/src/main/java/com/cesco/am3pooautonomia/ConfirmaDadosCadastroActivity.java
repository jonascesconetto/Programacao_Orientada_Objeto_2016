package com.cesco.am3pooautonomia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cesco.am3pooautonomia.dao.AbastecimentoDao;
import com.cesco.am3pooautonomia.modelo.Abastecimento;

/**
 * Created by cesco on 19/11/16.
 */

public class ConfirmaDadosCadastroActivity extends AppCompatActivity{

    private Abastecimento ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_dados_cad);

        ab = getIntent().getParcelableExtra("abastecimento");

        TextView tvQuilometragemAtual = (TextView) findViewById(R.id.tvQuilometragemAtual);
        TextView tvLitrosAbastecidos = (TextView) findViewById(R.id.tvLitrosAbastecidos);
        TextView tvDataAbastecimento = (TextView) findViewById(R.id.tvDataAbastecimento);
        TextView tvPostoSelecionado = (TextView) findViewById(R.id.tvPostoSelecionado);

        String txtMensagemQuilometragem = getResources().getString(R.string.textQuilometragem);
        String txtMensagemLitros = getResources().getString(R.string.textLitros);
        String txtMensagemData = getResources().getString(R.string.textData);
        String txtMensagemPosto = getResources().getString(R.string.textPosto);

        tvQuilometragemAtual.setText(txtMensagemQuilometragem+" "+String.valueOf(ab.getQuilometragem()));
        tvLitrosAbastecidos.setText(txtMensagemLitros+" "+String.valueOf(ab.getLitros()));
        tvDataAbastecimento.setText(txtMensagemData+" "+String.valueOf(ab.getDataAbastecimento()));
        tvPostoSelecionado.setText(txtMensagemPosto+" "+String.valueOf(ab.getPosto()));
    }

    public void clicouNoSalvar(View origemDoClique) {

        //Salvar no Array
        AbastecimentoDao.salvar(ab);

        String mensagemSucessoArmazenamento = getResources().getString(R.string.infoAbastecimentoRealizadoSucesso);
        Toast.makeText(this.getApplicationContext(), mensagemSucessoArmazenamento, Toast.LENGTH_SHORT).show();

        Intent abridor = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(abridor);
    }

    @Override
    public void onPause(){
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed(){
        Intent abridor = new Intent(this.getApplicationContext(), CadastroActivity.class);
        startActivity(abridor);
    }
}

