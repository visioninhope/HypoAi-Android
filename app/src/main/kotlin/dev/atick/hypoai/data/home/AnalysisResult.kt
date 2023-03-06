package dev.atick.hypoai.data.home

import androidx.compose.ui.graphics.ImageBitmap

data class AnalysisResult(
    val score: String,
    val mask: ImageBitmap
)
