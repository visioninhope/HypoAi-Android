package dev.atick.network.api

import dev.atick.network.data.models.HypoAiResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface HypoAiRestApi {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    @Multipart
    @POST("hypoAI/rest")
    suspend fun analyzeImage(
        @Part file: MultipartBody.Part
    ): HypoAiResponse
}