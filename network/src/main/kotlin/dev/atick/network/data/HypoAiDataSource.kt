package dev.atick.network.data

import dev.atick.network.data.models.HypoAiResponse
import java.io.File

interface HypoAiDataSource {
    suspend fun analyzeImage(file: File): HypoAiResponse
}