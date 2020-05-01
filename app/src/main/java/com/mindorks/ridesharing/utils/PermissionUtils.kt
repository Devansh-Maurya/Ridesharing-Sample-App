package com.mindorks.ridesharing.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mindorks.ridesharing.R

/**
 * Created by Devansh on 1/5/20
 */

object PermissionUtils {
    
    fun requestAccessFineLocationPermission(activity: AppCompatActivity, requestId: Int) {
        ActivityCompat.requestPermissions(
            activity, 
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            requestId
        )
    }
    
    fun isAccessFineLocationGranted(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) or
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
    
    fun showGpsNotEnabledDialog(context: Context) {
        context.apply {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.enable_gps))
                .setMessage(getString(R.string.required_for_this_app))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.enable_now)) { _, _ ->
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }
                .show()
        }
    }
}