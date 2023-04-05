package dev.atick.network.data

import dev.atick.network.data.models.Response
import java.io.File

interface HypoAiDataSource {
    suspend fun getHypospadiasScore(file: File): Response
    suspend fun getCurvatureScore(file: File): Response
}