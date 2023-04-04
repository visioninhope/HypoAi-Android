package dev.atick.hypoai.repository.home

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.compose.ui.graphics.asImageBitmap
import com.orhanobut.logger.Logger
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.atick.core.ui.extensions.getFileFromContentUri
import dev.atick.hypoai.data.home.AnalysisResult
import dev.atick.network.data.HypoAiDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jetpackDataSource: HypoAiDataSource
) : HomeRepository {
    override suspend fun uploadImageForHypospadiasAnalysis(inputImageUri: Uri): Result<AnalysisResult> {
        return try {
            Logger.i("IMAGE URI: $inputImageUri")
            val file = context.getFileFromContentUri(inputImageUri)
            val response = jetpackDataSource.getHypospadiasScore(file)
            val decodedString = Base64.decode(response.mask, Base64.DEFAULT)
            val mask = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            val score = response.score
            Result.success(
                AnalysisResult(
                    score = score,
                    mask = mask.asImageBitmap()
                )
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.failure(exception)
        }
    }

    override suspend fun uploadImageForCurvatureAnalysis(inputImageUri: Uri): Result<AnalysisResult> {
        return try {
            Logger.i("IMAGE URI: $inputImageUri")
            val file = context.getFileFromContentUri(inputImageUri)
            val response = jetpackDataSource.getHypospadiasScore(file)
            val decodedString = Base64.decode(response.mask, Base64.DEFAULT)
            val mask = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            val score = response.score
            Result.success(
                AnalysisResult(
                    score = score,
                    mask = mask.asImageBitmap()
                )
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.failure(exception)
        }
    }
}
