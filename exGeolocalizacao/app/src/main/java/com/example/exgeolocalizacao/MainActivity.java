package com.example.exgeolocalizacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buscarInformacoesGPS(View view){

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, 1);

            return;

        }

        LocationManager nLocManager = (LocationManager) getSystemService(MainActivity.this.LOCATION_SERVICE);
        LocationListener nLocListener = new minhaLocalizacaoListener();

        nLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,nLocListener);

        if(nLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            String texto = "Latitude: " + minhaLocalizacaoListener.latitude + "/n" +
                            "Longitude: " + minhaLocalizacaoListener.longitude + "/n";

            Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "GPS disabilitdo", Toast.LENGTH_SHORT).show();
        }

        //chamar metodo
        this.mostrarGoogleMaps(minhaLocalizacaoListener.latitude, minhaLocalizacaoListener.longitude);
    }

    public void mostrarGoogleMaps(double latitude, double longitude){

        WebView webview = findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://www.google.com/maps/search/api=&query=" + latitude + "," + longitude);
    }
}