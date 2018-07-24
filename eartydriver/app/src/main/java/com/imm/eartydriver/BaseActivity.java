package com.imm.eartydriver;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.akhgupta.easylocation.EasyLocationAppCompatActivity;
import com.akhgupta.easylocation.EasyLocationRequest;
import com.akhgupta.easylocation.EasyLocationRequestBuilder;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by Rinov on 7/24/2018.
 */

public class BaseActivity extends EasyLocationAppCompatActivity {

    public static LocationRequest locationRequest;
    public static EasyLocationRequest easyLocationRequest;

    static SharedPreferences pref;
    static SharedPreferences.Editor editor;
    static Context context;

    public BaseActivity(){
        if (locationRequest == null ){
            locationRequest = new LocationRequest()
                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                    .setInterval(5000)
                    .setFastestInterval(5000);
        }
        if (easyLocationRequest == null ){
            easyLocationRequest = new EasyLocationRequestBuilder()
                    .setLocationRequest(locationRequest)
                    .setFallBackToLastLocationTime(3000)
                    .build();
        }
    }

    protected void jalankanUpdateLokasi(){
        msgToast("Jalankan");
        requestLocationUpdates(easyLocationRequest);
    }

    protected void stopUpdateLokasi(){
        msgToast("Berhentikan");
        stopLocationUpdates();
    }

    protected void msgToast(String msg) {
        try{
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public BaseActivity(Context context){
        this.context = context;
        if (pref == null ){
            if (context != null){
                pref = context.getSharedPreferences("MyPref", 0);
                editor = pref.edit();
                msgToast("Context added");
            } else {
                msgToast(""+getApplicationContext());
            }

        }
        if (locationRequest == null ){
            locationRequest = new LocationRequest()
                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                    .setInterval(5000)
                    .setFastestInterval(5000);
        }
        if (easyLocationRequest == null ){
            easyLocationRequest = new EasyLocationRequestBuilder()
                    .setLocationRequest(locationRequest)
                    .setFallBackToLastLocationTime(3000)
                    .build();
        }
    }

    @Override
    public void onLocationPermissionGranted() {

    }

    @Override
    public void onLocationPermissionDenied() {

    }

    @Override
    public void onLocationReceived(Location location) {
        msgToast("Long:"+location.getLongitude()+" lat:"+location.getLatitude());
    }

    @Override
    public void onLocationProviderEnabled() {

    }

    @Override
    public void onLocationProviderDisabled() {

    }
}
