package com.example.androidappcourse.posts.business

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager as AndroidLocationManager
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LocationManager(private val context: Context) {

    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as AndroidLocationManager

    @SuppressLint("MissingPermission") // Assicurati di gestire i permessi prima di usare!
    suspend fun getCurrentLocation(): Location? = suspendCancellableCoroutine { cont ->
        val provider = AndroidLocationManager.GPS_PROVIDER
        val location = locationManager.getLastKnownLocation(provider)
        cont.resume(location)
    }
}