package dev.atick.hypoai.data.home

import androidx.compose.ui.graphics.ImageBitmap

data class AnalysisResult(
    val score: Float,
    val mask: ImageBitmap
)
