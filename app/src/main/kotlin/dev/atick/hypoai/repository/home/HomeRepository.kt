package dev.atick.hypoai.repository.home

import android.net.Uri
import dev.atick.hypoai.data.home.AnalysisResult

interface HomeRepository {
    suspend fun uploadImageForHypospadiasAnalysis(inputImageUri: Uri): Result<AnalysisResult>
    suspend fun uploadImageForCurvatureAnalysis(inputImageUri: Uri): Result<AnalysisResult>
}