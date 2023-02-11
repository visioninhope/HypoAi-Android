package dev.atick.compose.ui.home.state

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import dev.atick.core.ui.base.BaseUiState
import dev.atick.core.ui.utils.UiText

data class HomeUiState(
    val loading: Boolean = false,
    val error: UiText? = null,
    val inputImageUri: Uri? = null,
    val inputImageBitmap: ImageBitmap? = null,
) : BaseUiState()
