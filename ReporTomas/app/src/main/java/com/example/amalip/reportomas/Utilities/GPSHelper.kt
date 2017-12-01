package com.example.amalip.reportomas.Utilities

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Context.LOCATION_SERVICE
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.amalip.reportomas.ReportMap.ReportMapFragment
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*

/**
 * Created by Amalip on 12/1/2017.
 */
class GPSHelper(activity: BaseActivity) {
    val activity = activity
    lateinit var googleApiClient : GoogleApiClient

    fun initApiClient(){
        googleApiClient = GoogleApiClient.Builder(activity)
                .addApi(LocationServices.API).build()
        googleApiClient.connect()
    }

    fun enableGPS() {
        initApiClient()

        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10000
        locationRequest.fastestInterval = (10000 / 2).toLong()

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)

        val result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())
        result.setResultCallback { result ->
            val status = result.status
            when (status.statusCode) {
                LocationSettingsStatusCodes.SUCCESS -> Log.i(TAG, "All location settings are satisfied.")
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                    Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ")

                    try {
                        // Show the dialog by calling startResolutionForResult(), and check the result
                        // in onActivityResult().
                        status.startResolutionForResult(activity, 1000)
                    } catch (e: IntentSender.SendIntentException) {
                        Log.i(TAG, "PendingIntent unable to execute request.")
                    }

                }
                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.")
            }
        }

        var locationManager = activity.getSystemService(LOCATION_SERVICE) as LocationManager?;
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, ReportMapFragment())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            loadPermissions(Manifest.permission.ACCESS_FINE_LOCATION, 0)
    }

    private fun loadPermissions(perm: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(activity, perm) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(perm), requestCode)
        }
    }

}