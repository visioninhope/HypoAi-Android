package dev.atick.compose.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.CallSuper
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.atick.compose.BuildConfig
import dev.atick.compose.R
import dev.atick.compose.ui.home.components.HomeContent
import dev.atick.core.extensions.getBitmap
import dev.atick.core.ui.components.TopBar
import dev.atick.core.ui.extensions.getTmpFileUri
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    val homeUiState by homeViewModel.homeUiState.collectAsState()
    val snackbarHost = remember { SnackbarHostState() }
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    homeUiState.error?.let {
        val errorMessage = it.asString()
        LaunchedEffect(homeUiState) {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    snackbarHost.showSnackbar(errorMessage)
                    homeViewModel.clearError()
                }
            }
        }
    }

    val context = LocalContext.current

    val takePhotoLauncher = rememberLauncherForActivityResult(
        TakePictureWithUriReturnContract()
    ) { (isSuccess, uri) ->
        if (isSuccess) {
            homeViewModel.setInputImageUri(uri)
            homeViewModel.setInputImageBitmap(uri.getBitmap(context)?.asImageBitmap())
        }
    }

    val openGalleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            homeViewModel.setInputImageUri(uri)
            homeViewModel.setInputImageBitmap(uri.getBitmap(context)?.asImageBitmap())
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.home),
                onSearchClick = {},
                onMenuClick = {})
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHost) }
    ) { paddingValues ->
        HomeContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            homeUiState = homeUiState,
            onImageCaptureClick = {
                takePhotoLauncher.launch(
                    context.getTmpFileUri(BuildConfig.APPLICATION_ID)
                )
            },
            onSelectAnotherImageClick = { homeViewModel.setInputImageBitmap(null) },
            onOpenGalleryClick = {
                openGalleryLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
            onUploadImageClick = { homeViewModel.analyzeImage() }
        )
    }
}


class TakePictureWithUriReturnContract : ActivityResultContract<Uri, Pair<Boolean, Uri>>() {

    private lateinit var imageUri: Uri

    @CallSuper
    override fun createIntent(context: Context, input: Uri): Intent {
        imageUri = input
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, input)
    }

    override fun getSynchronousResult(
        context: Context,
        input: Uri
    ): SynchronousResult<Pair<Boolean, Uri>>? = null

    @Suppress("AutoBoxing")
    override fun parseResult(resultCode: Int, intent: Intent?): Pair<Boolean, Uri> {
        return (resultCode == Activity.RESULT_OK) to imageUri
    }
}