package dev.atick.compose.ui.home.state

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import dev.atick.core.ui.base.BaseUiState
import dev.atick.core.ui.utils.UiText

data class HomeUiState(
    override val loading: Boolean = false,
    override val toastMessage: UiText? = null,
    val inputImageUri: Uri? = null,
    val score: String? = null,
    val inputImageBitmap: ImageBitmap? = null,
) : BaseUiState()
