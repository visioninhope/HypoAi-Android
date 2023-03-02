package dev.atick.compose.repository.home

import android.net.Uri
import dev.atick.compose.data.home.AnalysisResult

interface HomeRepository {
    suspend fun analyzeImage(inputImageUri: Uri): Result<AnalysisResult>
}