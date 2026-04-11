package com.tgsanzh.weatherapp.core.location.data.datasource

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.tgsanzh.weatherapp.core.location.domain.models.Location
import kotlinx.coroutines.suspendCancellableCoroutine

class LocationDataSource(
    private val context: Context,
    private val fusedClient: FusedLocationProviderClient
) {
    suspend fun getLocation(): Location? {

        return suspendCancellableCoroutine { cont ->
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                cont.resume(null, null)
                return@suspendCancellableCoroutine
            }
            fusedClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            )
                .addOnSuccessListener { loc ->
                    if (loc != null) {
                        cont.resume(Location(loc.latitude, loc.longitude), null)
                    } else {
                        cont.resume(null, null)
                    }
                }
                .addOnFailureListener {
                    cont.resume(null, null)
                }
        }
    }
}