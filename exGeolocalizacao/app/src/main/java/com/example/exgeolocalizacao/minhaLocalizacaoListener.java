package com.example.exgeolocalizacao;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class minhaLocalizacaoListener implements LocationListener {

    public static double latitude;
    public static double longitude;

    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider){

    }

    @Override
    public void onProviderEnabled(@NonNull String provider){

    }

    public void onStatusChagend(String provider, int status, Bundle extras){

    }
}
