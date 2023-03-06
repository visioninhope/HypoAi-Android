package dev.atick.hypoai.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.atick.hypoai.ui.home.state.HomeUiState
import dev.atick.core.ui.components.LoadingButton

@Preview(showSystemUi = true)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState = HomeUiState(),
    onImageCaptureClick: () -> Unit = {},
    onSelectAnotherImageClick: () -> Unit = {},
    onOpenGalleryClick: () -> Unit = {},
    onUploadImageClick: () -> Unit = {}
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Url("https://assets9.lottiefiles.com/datafiles/vhvOcuUkH41HdrL/data.json"))

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (homeUiState.inputImageBitmap == null) {
            Column(
                modifier = Modifier.weight(1F),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LottieAnimation(
                    modifier = Modifier.size(200.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(0.7F),
                    text = "No Image Found! Please Take an Image or Select One from the Gallery",
                    textAlign = TextAlign.Center
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onImageCaptureClick
            ) {
                Icon(imageVector = Icons.Default.CameraAlt, contentDescription = "Take Picture")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Take a Picture")
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onOpenGalleryClick
            ) {
                Icon(imageVector = Icons.Default.Photo, contentDescription = "Open Gallery")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Select Image from Gallery")
            }
        } else {
            homeUiState.score?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 20.sp,
                    text = "SCORE: ${homeUiState.score}",
                    textAlign = TextAlign.Center
                )
            }
            Image(
                modifier = Modifier.weight(1F),
                bitmap = homeUiState.inputImageBitmap,
                contentDescription = "Input Image"
            )
            LoadingButton(
                modifier = Modifier.fillMaxWidth(),
                loading = homeUiState.loading,
                onClick = onUploadImageClick
            ) {
                Text(text = "Upload Image to the Server")
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onSelectAnotherImageClick
            ) {
                Text(text = "Select Another Image")
            }
        }
    }
}