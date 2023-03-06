package dev.atick.hypoai.repository.home

import android.net.Uri
import dev.atick.hypoai.data.home.AnalysisResult

interface HomeRepository {
    suspend fun analyzeImage(inputImageUri: Uri): Result<AnalysisResult>
}