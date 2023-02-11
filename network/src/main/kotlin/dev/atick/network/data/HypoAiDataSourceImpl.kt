package dev.atick.network.data

import dev.atick.network.api.HypoAiRestApi
import dev.atick.network.data.models.HypoAiResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class HypoAiDataSourceImpl @Inject constructor(
    private val hypoAiRestApi: HypoAiRestApi
) : HypoAiDataSource {
    override suspend fun analyzeImage(file: File): HypoAiResponse {
        val multiPartFile = MultipartBody.Part.createFormData(
            name = "file",
            filename = file.name,
            body = file.asRequestBody("image/*".toMediaTypeOrNull())
        )

        return hypoAiRestApi.analyzeImage(multiPartFile)
    }
}