package com.tgsanzh.weatherapp.core.location.data.datasource

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.tgsanzh.weatherapp.core.error.AppError
import com.tgsanzh.weatherapp.core.location.domain.models.Location
import com.tgsanzh.weatherapp.core.result.AppResult
import kotlinx.coroutines.suspendCancellableCoroutine

class LocationDataSource(
    private val context: Context,
    private val fusedClient: FusedLocationProviderClient
) {

    suspend fun getLocation(): AppResult<Location> {
        return suspendCancellableCoroutine { cont ->

            val hasFine = ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            val hasCoarse = ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasFine && !hasCoarse) {
                cont.resume(
                    AppResult.Error(AppError.Location.NoPermission),
                    null
                )
                return@suspendCancellableCoroutine
            }

            if (!isLocationEnabled(context)) {
                cont.resume(
                    AppResult.Error(AppError.Location.Disabled),
                    null
                )
            }

            fusedClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            )
                .addOnSuccessListener { loc ->
                    if (loc != null) {
                        cont.resume(
                            AppResult.Success(
                                Location(loc.latitude, loc.longitude)
                            ),
                            null
                        )
                    } else {
                        cont.resume(
                            AppResult.Error(AppError.Location.NotAvailable),
                            null
                        )
                    }
                }
                .addOnFailureListener {
                    cont.resume(
                        AppResult.Error(AppError.Location.NotAvailable),
                        null
                    )
                }
        }
    }
}

fun isLocationEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    return LocationManagerCompat.isLocationEnabled(locationManager)
}