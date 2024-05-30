package com.laironlf.smartRecipes.presentation.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

object PermissionUtils {

    fun checkStoragePermissions(activity: Activity): Boolean {
        val isAtLeastT = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        val storagePermissions = if (isAtLeastT) {
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
            )
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        val permissionsToRequest = storagePermissions.filter {
            ActivityCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED
        }

        return if (permissionsToRequest.isEmpty()) {
            true
        } else {
//            permissionsToRequest.forEach { permission ->
//                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
//                    // Здесь можно показать объяснение пользователю, почему нужно разрешение
//                    // Например, через диалоговое окно или Snackbar
//                }
//            }
            ActivityCompat.requestPermissions(activity, permissionsToRequest.toTypedArray(), 100)
            false
        }
    }
}