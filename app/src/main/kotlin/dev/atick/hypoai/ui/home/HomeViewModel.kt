package dev.atick.hypoai.ui.home

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.atick.hypoai.repository.home.HomeRepository
import dev.atick.hypoai.ui.home.state.HomeUiState
import dev.atick.core.ui.base.BaseViewModel
import dev.atick.core.ui.utils.UiText
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
        _homeUiState.update { it.copy(toastMessage = null) }
    }

    fun setInputImageUri(uri: Uri) {
        _homeUiState.update { it.copy(inputImageUri = uri) }
    }

    fun clearAnalysisResult() {
        _homeUiState.update { it.copy(inputImageBitmap = null, score = null) }
    }

    fun analyzeImage() {
        homeUiState.value.inputImageUri?.let { uri ->
            viewModelScope.launch {
                _homeUiState.update { it.copy(loading = true) }
                val result = homeRepository.analyzeImage(uri)
                if (result.isSuccess) {
                    val analysisResult = result.getOrNull()
                    analysisResult?.let {
                        _homeUiState.update {
                            it.copy(
                                score = analysisResult.score,
                                inputImageBitmap = analysisResult.mask,
                                loading = false
                            )
                        }
                    }
                } else {
                    _homeUiState.update {
                        it.copy(
                            toastMessage = UiText.DynamicString(
                                result.exceptionOrNull().toString()
                            ),
                            loading = false
                        )
                    }
                }
            }
        }
    }
}