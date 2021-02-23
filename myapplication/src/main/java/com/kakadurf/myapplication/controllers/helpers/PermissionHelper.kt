package com.kakadurf.hw_sem2.controllers.helpers

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.kakadurf.hw_sem2.controllers.PERMISSION_CODE

class PermissionHelper (private val context: Activity){
    fun checkPerms(): Boolean {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("hi","no perms")
            return false
        }
        return true
    }
    fun requestPerm() {
        ActivityCompat.requestPermissions(
            context,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            PERMISSION_CODE
        )
    }
    fun onRequestPermissionsResult(
        requestCode: Int,
        grantResults: IntArray): Boolean {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (!(grantResults.isNotEmpty()
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    return false
                }
                return true
            }
        }
        return false
    }
}