package com.cesco.am3poocriticagastronomica;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.IntegerRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cesco.am3poocriticagastronomica.modelo.Critica;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cesco on 07/12/16.
 */

public class CadastroActivity extends AppCompatActivity {

    private EditText etDescricao;
    private RadioGroup rgCategoria;
    private RadioButton rbEntrada;
    private RadioButton rbPratoPrincipal;
    private RadioButton rbSobremesa;
    private RadioButton rbLanche;
    private RatingBar ratingBarAvaliacao;

    private ImageButton ibtnTirarFoto;
    private ImageView ivFotoComida;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etDescricao = (EditText) findViewById(R.id.etDescricao);
        rgCategoria = (RadioGroup) findViewById(R.id.rgCategoria);
        rbEntrada = (RadioButton) findViewById(R.id.rbEntrada);
        rbPratoPrincipal = (RadioButton) findViewById(R.id.rbPratoPrincipal);
        rbSobremesa = (RadioButton) findViewById(R.id.rbSobremesa);
        rbLanche = (RadioButton) findViewById(R.id.rbLanche);
        ratingBarAvaliacao = (RatingBar) findViewById(R.id.ratingBarAvaliacao);
        ibtnTirarFoto = (ImageButton) findViewById(R.id.ibtnTirarFoto);
        ivFotoComida = (ImageView) findViewById(R.id.ivFotoComida);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
    }

    private boolean formularioValido() {

        if(etDescricao.getText().toString().trim().equals("")){
            return false;
        }

        if(rgCategoria.getCheckedRadioButtonId() == -1){
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            //voltando da tela de confirmação
            if (resultCode == Activity.RESULT_OK) {// após salvar
                Intent abridor = new Intent(this.getApplicationContext(), MainActivity.class);
                startActivity(abridor);
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.infoSalvoComSucesso), Toast.LENGTH_SHORT).show();
            } else {
                // após desistir
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.infoDadosDescartados), Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 3) {//voltando do app de camera
            if (resultCode == RESULT_OK) {
                ImageView imgFotografia = (ImageView) findViewById(R.id.ivFotoComida);

                //recebimento da imagem através de arquivo
                File arquivoImagem = new File(caminhoFoto);
                imgFotografia.setImageURI(Uri.fromFile(arquivoImagem));
            } else if (resultCode == RESULT_CANCELED) {
                //cancelou
            }
        }
    }

    private String caminhoFoto = null;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        caminhoFoto = image.getAbsolutePath();
        return image;
    }

    public void clicouNoBotaoTirarFoto(View v){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.cesco.am3poocriticagastronomica", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 3);
            }
        }
    }

    public void clicouNoSalvar(View origemDoClique){
        if(!formularioValido()){
            String mensagemInternacionalizada = getResources().getString(R.string.formulario_invalido);
            Toast.makeText(this.getApplicationContext(), mensagemInternacionalizada, Toast.LENGTH_SHORT).show();
            return;
        }

        //Crio um objeto
        Critica criticamente = new Critica();

        //set data
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        criticamente.setdataCritica(timeStamp);

        //set da descrição
        criticamente.setDescricao(etDescricao.getText().toString().trim());

        //set da categoria
        RadioButton selecionado = (RadioButton) findViewById(rgCategoria.getCheckedRadioButtonId());
        if(selecionado == rbEntrada){
            criticamente.setCategoria("Entrada");
        }else{
            if (selecionado == rbPratoPrincipal){
                criticamente.setCategoria("Prato Principal");
            }else{
                if (selecionado == rbSobremesa){
                    criticamente.setCategoria("Sobremesa");
                }else{
                    if (selecionado == rbLanche){
                        criticamente.setCategoria("Lanche");
                    }
                }
            }
        }

        //set da avaliação
        float ratingOfAvaluation = ratingBarAvaliacao.getRating();
        criticamente.setAvaliacao((int)ratingOfAvaluation);

        //set do caminho da foto
        criticamente.setCaminhoFoto(caminhoFoto);

        Intent abridor = new Intent(this.getApplicationContext(), CadastroActivityConfirma.class);
        abridor.putExtra("critica", criticamente);
        startActivityForResult(abridor, 2);
    }

    @Override
    public void onBackPressed(){
        Intent abridor = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(abridor);
        finish();
    }
}
