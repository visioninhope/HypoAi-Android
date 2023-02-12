package dev.atick.core.ui.extensions

import android.Manifest
import android.app.Notification
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) ==
        PackageManager.PERMISSION_GRANTED
}

fun Context.isAllPermissionsGranted(permissions: List<String>): Boolean {
    return permissions.map { permission ->
        this.hasPermission(permission)
    }.reduce { acc, b -> acc && b }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
fun Context.showNotification(
    notificationId: Int,
    notification: Notification
) {
    with(NotificationManagerCompat.from(this)) {
        notify(notificationId, notification)
    }
}

// ... https://medium.com/codex/how-to-implement-the-activity-result-api-takepicture-contract-with-uri-return-type-7c93881f5b0f
fun Context.getTmpFileUri(appId: String): Uri {
    val tmpFile = File.createTempFile("tmp_image_file", ".png", cacheDir).apply {
        createNewFile()
        deleteOnExit()
    }

    return FileProvider.getUriForFile(
        applicationContext,
        "${appId}.provider",
        tmpFile
    )
}