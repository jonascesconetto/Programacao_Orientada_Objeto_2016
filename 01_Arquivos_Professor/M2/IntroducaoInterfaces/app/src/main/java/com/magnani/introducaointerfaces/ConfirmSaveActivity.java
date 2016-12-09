package com.magnani.introducaointerfaces;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.magnani.introducaointerfaces.dao.PessoaDao;
import com.magnani.introducaointerfaces.modelo.Pessoa;

public class ConfirmSaveActivity extends Activity {
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_save);

        pessoa = getIntent().getParcelableExtra("pessoa");
        TextView tvNome = (TextView) findViewById(R.id.tvNome);
        TextView tvSobrenome = (TextView) findViewById(R.id.tvSobrenome);
        TextView tvIdade = (TextView) findViewById(R.id.tvIdade);
        TextView tvSexo = (TextView) findViewById(R.id.tvSexo);


        tvNome.setText("Nome: " + pessoa.getNome());
        tvSobrenome.setText("Sobrenome: " + pessoa.getSobrenome());
        tvIdade.setText("Idade: " + pessoa.getIdade());
        tvSexo.setText("Sexo: " + pessoa.getSexo());
    }

    public void clicouNoBotao(View origemDoClique){
        PessoaDao.salvar(pessoa);
        String mensagemInternacionalizada = getResources().getString(R.string.pessoa_NOME_armazenada_com_sucesso, pessoa.getNome());
        Toast.makeText(this.getApplicationContext(), mensagemInternacionalizada, Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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
