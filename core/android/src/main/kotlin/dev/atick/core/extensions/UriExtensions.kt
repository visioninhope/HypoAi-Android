package dev.atick.core.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore

// ... https://stackoverflow.com/a/57960422/12737399
fun Uri.getBitmap(context: Context): Bitmap? {
    return try {
        if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(context.contentResolver, this)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, this)
            ImageDecoder.decodeBitmap(source)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}