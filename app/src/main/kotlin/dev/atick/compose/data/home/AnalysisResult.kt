package dev.atick.compose.data.home

import androidx.compose.ui.graphics.ImageBitmap

data class AnalysisResult(
    val score: String,
    val mask: ImageBitmap
)
