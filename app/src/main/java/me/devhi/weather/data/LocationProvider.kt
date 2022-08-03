package me.devhi.weather.data

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException
import java.util.*

class LocationProvider(private val activity: Activity) {
    private val PERMISSIONS_REQUEST_CODE = 1
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    fun getCurrentLocation(): Location? {
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED
        ) {

            val locationManager =
                activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    REQUIRED_PERMISSIONS[0]
                )
            ) {
                Toast.makeText(activity, "앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_SHORT)
                    .show()
                ActivityCompat.requestPermissions(
                    activity,
                    REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE
                )
            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE
                )
            }
            return null
        }
    }

    fun getAddress(location: Location): String? {
        val geoCoder = Geocoder(activity, Locale.KOREAN)
        val address = try {
            geoCoder.getFromLocation(location.latitude, location.longitude, 1).getOrNull(0)
        } catch (e: IOException) {
            return null
        }
        return if (address?.adminArea.isNullOrEmpty()) {
            address?.locality
        } else {
            address?.adminArea
        }
    }

}
