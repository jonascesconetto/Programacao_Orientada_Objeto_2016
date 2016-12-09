package com.magnani.introducaointerfaces;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.magnani.introducaointerfaces.modelo.Pessoa;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            return;
        }
        Pessoa p = new Pessoa();
        p.setNome(etNome.getText().toString().trim());
        p.setSobrenome(etSobrenome.getText().toString().trim());
        p.setIdade(Integer.parseInt(etIdade.getText().toString()));
        p.setCaminhoFoto(caminhoFoto);
        RadioButton selecionado = (RadioButton) findViewById(rgSexo.getCheckedRadioButtonId());
        if(selecionado == rbMasculino){
            p.setSexo("M");
        }else{
            p.setSexo("F");
        }

        Intent abridor = new Intent(this.getApplicationContext(), ConfirmSaveActivity.class);
        abridor.putExtra("pessoa", p);
//        startActivity(abridor);
        startActivityForResult(abridor, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            //voltando da tela de visualização
            if (resultCode == Activity.RESULT_OK) {// após salvar
                Toast.makeText(getApplicationContext(), "Ele salvou!! Número aleatório "+data.getIntExtra("numeroAleatorio", 999), Toast.LENGTH_SHORT).show();
            } else {
            // após desistir
                Toast.makeText(getApplicationContext(), "Ele desistiu!!", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 3) {//voltando do app de camera
            if (resultCode == RESULT_OK) {
                ImageView imgFotografia = (ImageView) findViewById(R.id.ivFotografia);
                //recebimento da imagem através de bitmap
//                Bundle extras = data.getExtras();
//                Bitmap imageBitmap = (Bitmap) extras.get("data");
//                imgFotografia.setImageBitmap(imageBitmap);

                //recebimento da imagem através de arquivo
                File arquivoImagem = new File(caminhoFoto);
                imgFotografia.setImageURI(Uri.fromFile(arquivoImagem));
            } else if (resultCode == RESULT_CANCELED) {
                //cancelou
            }
        }
        if (requestCode == 4) {//voltando do app de contatos
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                cursor.moveToFirst();

                int coluna = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Contactables.DISPLAY_NAME);
                String nome = cursor.getString(coluna);
                etNome.setText(nome);
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
        //abertura da câmera para recebimento da imagem através de bitmap
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(takePictureIntent, 3);

        //abertura da câmera para recebimento da imagem através de arquivo
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, 3);
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.magnani.introducaointerfaces",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 3);
            }
        }

    }

    public void clicouNoContato(View v) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, 4);
    }


    public void clicouNoBotaoMostrarTodos(View v){
        Intent abridor = new Intent(this.getApplicationContext(), ShowAllActivity.class);
        startActivity(abridor);
    }

    public void clicouNoBotaoPrevisaoTempo(View v){
        Intent abridor = new Intent(this.getApplicationContext(), WeatherActivity.class);
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
