package dev.atick.compose.repository.home

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap

interface HomeRepository {
    suspend fun analyzeImage(inputImageUri: Uri): Result<ImageBitmap>
}