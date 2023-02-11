package dev.atick.network.data.models

import com.google.gson.annotations.SerializedName

data class HypoAiResponse(
    @SerializedName("image")
    val image: String,
    @SerializedName("mask")
    val mask: String
)