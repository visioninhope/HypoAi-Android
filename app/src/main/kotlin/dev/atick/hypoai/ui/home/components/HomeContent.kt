package dev.atick.hypoai.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.atick.core.ui.components.LoadingButton
import dev.atick.hypoai.R
import dev.atick.hypoai.ui.home.state.HomeUiState

@Preview(showSystemUi = true)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState = HomeUiState(),
    onImageCaptureClick: () -> Unit = {},
    onSelectAnotherImageClick: () -> Unit = {},
    onOpenGalleryClick: () -> Unit = {},
    onHypospadiasAnalysisClick: () -> Unit = {},
    onCurvatureAnalysisClick: () -> Unit = {},
    onVisitWebsiteClick: () -> Unit = {}
) {
//    val composition by rememberLottieComposition(LottieCompositionSpec.Url("https://assets9.lottiefiles.com/datafiles/vhvOcuUkH41HdrL/data.json"))

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
//                LottieAnimation(
//                    modifier = Modifier.size(200.dp),
//                    composition = composition,
//                    iterations = LottieConstants.IterateForever
//                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .aspectRatio(2.935F),
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "logo"
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .aspectRatio(1F),
                    painter = painterResource(id = R.drawable.banner_alt),
                    contentDescription = "banner"
                )

                Spacer(Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(0.8F),
                    text = "TRANSFORMING HEALTHCARE FOR HYPOSPADIAS WITH ARTIFICIAL INTELLIGENCE",
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(16.dp))
                OutlinedButton(
                    onClick = onVisitWebsiteClick,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.onError,
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = "Visit Website"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Visit Hypospadias.Ai")
                }
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
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(8.dp),
                        ) {
                            Icon(
                                modifier = Modifier.size(48.dp),
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = "score",
                                tint = MaterialTheme.colorScheme.primaryContainer
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(text = "SCORE")
                            Text(text = it, fontSize = 28.sp)
                        }
                    }
                }
            }
            Image(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(1F),
                bitmap = homeUiState.inputImageBitmap,
                contentDescription = "Input Image"
            )
            LoadingButton(
                modifier = Modifier.fillMaxWidth(),
                loading = homeUiState.loading,
                onClick = onHypospadiasAnalysisClick
            ) {
                Text(text = "Upload for Hypospadias Analysis")
            }
            LoadingButton(
                modifier = Modifier.fillMaxWidth(),
                loading = homeUiState.loading,
                onClick = onCurvatureAnalysisClick
            ) {
                Text(text = "Upload for Curvature Analysis")
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