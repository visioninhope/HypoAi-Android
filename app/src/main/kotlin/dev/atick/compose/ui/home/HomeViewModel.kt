package dev.atick.compose.ui.home

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.atick.compose.repository.home.HomeRepository
import dev.atick.compose.ui.home.state.HomeUiState
import dev.atick.core.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jetpackRepository: HomeRepository
) : BaseViewModel<HomeUiState>() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    fun setInputImageBitmap(imageBitmap: ImageBitmap?) {
        _homeUiState.update { it.copy(inputImageBitmap = imageBitmap) }
    }

    fun clearError() {
        _homeUiState.update { it.copy(error = null) }
    }

    fun setInputImageUri(uri: Uri) {
        _homeUiState.update { it.copy(inputImageUri = uri) }
    }

    fun analyzeImage() {
        viewModelScope.launch {
            _homeUiState.update { it.copy(loading = true) }
            delay(3000L)
            _homeUiState.update { it.copy(loading = false) }
        }
    }
}