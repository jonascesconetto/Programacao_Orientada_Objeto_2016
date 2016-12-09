package com.cesco.am3poocriticagastronomica;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cesco.am3poocriticagastronomica.dao.CriticaDao;
import com.cesco.am3poocriticagastronomica.modelo.Critica;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by cesco on 07/12/16.
 */

public class CadastroActivityConfirma extends Activity {

    private Critica criticamente;

    // --- VOLEY
    private ProgressBar pbLoader;
    private RequestQueue filaRequisicoes;

    // --- REQUISIÇÃO POSIÇÃO
    private LocationManager locationManager;
    private LocationListener locationListener;

    //-------------------GPS
    public void onClickObterLocal() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //nao tem permissão, então solicita
            String[] permissoes = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissoes, 1);
            return;
        }
        //se chegou aqui é pq tem permissão
        obterCoordenada();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        //se chegou aqui é pq a permissão foi solicitada, é hora de avaliar se foi concedida ou não
        for(int i = 0; i < permissions.length; i++){
            if(permissions[i] == Manifest.permission.ACCESS_FINE_LOCATION && grantResults[i] == PackageManager.PERMISSION_GRANTED){
                //permissão concedida
                obterCoordenada();
                return;
            }
        }
        Toast.makeText(this.getApplicationContext(), "Sem permissão", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings({"ResourceType"})
    private void obterCoordenada(){

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        pbLoader.setVisibility(View.VISIBLE);

        if(!locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("GPS desligado. Deseja ligar?")
                    .setCancelable(false)
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            pbLoader.setVisibility(View.INVISIBLE);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("GPS", "CHEGOU DADO DE LOCALIZAÇÃO");
                Log.d("GPS", "LAT: "+location.getLatitude());
                Log.d("GPS", "LON: "+location.getLongitude());
                Log.d("GPS", "ACC: "+location.getAccuracy());

                //--------Test location
                String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=__LAT__,__LONG__&sensor=true";

                url = url.replace("__LAT__", String.valueOf(location.getLatitude()));
                url = url.replace("__LONG__", String.valueOf(location.getLongitude()));

                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(CadastroActivityConfirma.this.getApplicationContext(), "Request DONE!", Toast.LENGTH_LONG).show();

                        try {
                            JSONArray results = response.getJSONArray("results");
                            JSONArray address_components = results.getJSONObject(0).getJSONArray("address_components");
                            for (int i = 0; i < address_components.length(); i++) {
                                JSONObject component = address_components.getJSONObject(i);
                                String long_name = component.getString("long_name");
                                JSONArray mtypes = component.getJSONArray("types");
                                String Type = mtypes.getString(0);
                                if (Type.equalsIgnoreCase("locality")) {
                                    Toast.makeText(CadastroActivityConfirma.this.getApplicationContext(), long_name, Toast.LENGTH_LONG).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pbLoader.setVisibility(View.INVISIBLE);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(CadastroActivityConfirma.this.getApplicationContext(), "Erro " + volleyError.getMessage(), Toast.LENGTH_LONG).show();
                        pbLoader.setVisibility(View.INVISIBLE);
                    }
                });
                filaRequisicoes.add(jsObjRequest);
                pbLoader.setVisibility(View.INVISIBLE);
                locationManager.removeUpdates(locationListener);

                Log.d("GPS", "Provedor trocado");
            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d("GPS", "GPS LIGADO");
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d("GPS", "GPS DESLIGADO");
            }

            @Override
            public void onStatusChanged(String s, int status, Bundle bundle) {
                switch (status) {
                    case LocationProvider.AVAILABLE:
                        Log.d("GPS", "GPS LIGOU");
                        break;
                    case LocationProvider.OUT_OF_SERVICE:
                        Log.d("GPS", "GPS FORA DE SERVIÇO");
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        Log.d("GPS", "GPS TEMPORARIAMENTE INDISPONÍVEL");
                        break;
                }
            }

        };

        Log.d("GPS", "INICIO");
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }
    //-------------------GPS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_confirma);

        pbLoader = (ProgressBar) findViewById(R.id.pbLoader);
        this.filaRequisicoes = Volley.newRequestQueue(this);

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //nao tem permissão, então solicita
            String[] permissoes = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissoes, 1);
            return;
        } else {
            //se chegou aqui é pq tem permissão
            obterCoordenada();
        }

        //abro a mochila
        criticamente = getIntent().getParcelableExtra("critica");
        //defino os textView

        TextView tvData = (TextView) findViewById(R.id.tvData);
        TextView tvDescricao = (TextView) findViewById(R.id.tvDescricao);
        TextView tvCategoria = (TextView) findViewById(R.id.tvCategoria);
        TextView tvAvaliacao = (TextView) findViewById(R.id.tvAvaliacao);
        ImageView ivFotoComidaConfirma = (ImageView) findViewById(R.id.ivFotoComidaConfirma);

        //data
        tvData.setText(getResources().getString(R.string.textData)+" "+ criticamente.getdataCritica());
        //descricao
        tvDescricao.setText(getResources().getString(R.string.textDescricao) +" "+ criticamente.getDescricao());
        //categoria
        tvCategoria.setText(getResources().getString(R.string.textCategoria) +" "+ criticamente.getCategoria());
        //avaliacao
        tvAvaliacao.setText(getResources().getString(R.string.textAvaliacao) +" "+ criticamente.getAvaliacao());
        //imagem
        if(criticamente.getCaminhoFoto() != null) {
            File arquivoImagem = new File(criticamente.getCaminhoFoto());
            if (arquivoImagem.exists()) {
                ivFotoComidaConfirma.setImageURI(Uri.fromFile(arquivoImagem));
            }
        }
    }

    public void clicouNoBotao(View origemDoClique){
        CriticaDao.salvar(criticamente);
        Intent fechador = new Intent();
        setResult(Activity.RESULT_OK, fechador);
        finish();
    }

    @Override
    @SuppressWarnings({"ResourceType"})
    protected void onPause() {
        super.onPause();
        if(locationManager != null)
            locationManager.removeUpdates(locationListener);
        finish();
    }

    @Override
    public void onBackPressed(){
        Intent abridor = new Intent(this.getApplicationContext(), CadastroActivity.class);
        startActivity(abridor);
    }
}
