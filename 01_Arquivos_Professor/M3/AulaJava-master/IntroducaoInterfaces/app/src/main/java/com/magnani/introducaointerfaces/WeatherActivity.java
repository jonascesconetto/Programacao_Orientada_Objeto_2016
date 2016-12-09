package com.magnani.introducaointerfaces;

import android.Manifest;
import android.annotation.TargetApi;
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
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        this.filaRequisicoes = Volley.newRequestQueue(this);

        this.pbLoader = (ProgressBar) findViewById(R.id.pbLoader);
        this.etCidade = (EditText) findViewById(R.id.etCidade);
        this.tvTemperatura = (TextView) findViewById(R.id.tvTemperatura);
    }

    private ProgressBar pbLoader;
    private RequestQueue filaRequisicoes;
    private TextView tvTemperatura;
    private EditText etCidade;

    public void onClickObterTempo(View v) {
        String url = "http://api.openweathermap.org/data/2.5/weather?appid=f2fed730291c909eafd7e28140fb6444&units=metric&q=__CIDADE__";

        url = url.replace("__CIDADE__", etCidade.getText().toString());

        pbLoader.setVisibility(View.VISIBLE);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(WeatherActivity.this.getApplicationContext(), "Request DONE!", Toast.LENGTH_LONG).show();


                        String temperatura = null;
                        String local = null;
                        try {
                            JSONObject main = response.getJSONObject("main");
                            temperatura = main.getString("temp");
                            local = response.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        tvTemperatura.setText(temperatura + "ºC" + " em " + local);
                        pbLoader.setVisibility(View.INVISIBLE);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(WeatherActivity.this.getApplicationContext(), "Erro " + volleyError.getMessage(), Toast.LENGTH_LONG).show();
                        pbLoader.setVisibility(View.INVISIBLE);
                        tvTemperatura.setText("ERRO!");
                    }

                });
        this.filaRequisicoes.add(jsObjRequest);
    }

    public void onClickObterLocal(View v) {
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

    private LocationManager locationManager;
    private LocationListener locationListener;

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

                String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=__LAT__,__LONG__&sensor=true";

                url = url.replace("__LAT__", String.valueOf(location.getLatitude()));
                url = url.replace("__LONG__", String.valueOf(location.getLongitude()));

                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(WeatherActivity.this.getApplicationContext(), "Request DONE!", Toast.LENGTH_LONG).show();

                                try {
                                    JSONArray results = response.getJSONArray("results");
                                    JSONArray address_components = results.getJSONObject(0).getJSONArray("address_components");
                                    for (int i = 0; i < address_components.length(); i++) {
                                        JSONObject component = address_components.getJSONObject(i);
                                        String long_name = component.getString("long_name");
                                        JSONArray mtypes = component.getJSONArray("types");
                                        String Type = mtypes.getString(0);
                                        if (Type.equalsIgnoreCase("locality")) {
                                            etCidade.setText(long_name);
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
                                Toast.makeText(WeatherActivity.this.getApplicationContext(), "Erro " + volleyError.getMessage(), Toast.LENGTH_LONG).show();
                                pbLoader.setVisibility(View.INVISIBLE);
                                tvTemperatura.setText("ERRO!");
                            }

                        });

                filaRequisicoes.add(jsObjRequest);
                pbLoader.setVisibility(View.INVISIBLE);
                locationManager.removeUpdates(locationListener);
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

            @Override
            public void onProviderEnabled(String s) {
                Log.d("GPS", "GPS LIGADO");
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d("GPS", "GPS DESLIGADO");
            }
        };
        Log.d("GPS", "INICIO");
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    @SuppressWarnings({"ResourceType"})
    protected void onPause() {
        super.onPause();
        if(locationManager != null)
            locationManager.removeUpdates(locationListener);
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
