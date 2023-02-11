package dev.atick.compose.repository.home

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.atick.core.utils.FileUtils
import dev.atick.network.data.HypoAiDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jetpackDataSource: HypoAiDataSource
) : HomeRepository {
    override suspend fun analyzeImage(inputImageUri: Uri): Result<ImageBitmap> {
        return try {
            val file = FileUtils.getFileFromUri(context, inputImageUri)
            val response = jetpackDataSource.analyzeImage(file)
            val decodedString = Base64.decode(response.mask.split(",")[1], Base64.DEFAULT)
            val mask = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            Result.success(mask.asImageBitmap())
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}