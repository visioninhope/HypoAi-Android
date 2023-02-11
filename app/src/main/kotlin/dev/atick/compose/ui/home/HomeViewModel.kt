package dev.atick.compose.ui.home

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.atick.compose.repository.home.HomeRepository
import dev.atick.compose.ui.home.state.HomeUiState
import dev.atick.core.ui.base.BaseViewModel
import dev.atick.core.ui.utils.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
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
        homeUiState.value.inputImageUri?.let { uri ->
            viewModelScope.launch {
                _homeUiState.update { it.copy(loading = true) }
                delay(3000L)
                val result = homeRepository.analyzeImage(uri)
                if (result.isSuccess) {
                    val imageBitmap = result.getOrNull()
                    imageBitmap?.let {
                        _homeUiState.update {
                            it.copy(
                                inputImageBitmap = imageBitmap,
                                loading = false
                            )
                        }
                    }
                } else {
                    _homeUiState.update {
                        it.copy(
                            error = UiText.DynamicString(result.exceptionOrNull().toString()),
                            loading = false
                        )
                    }
                }
            }
        }
    }
}