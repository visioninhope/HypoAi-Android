package dev.atick.network.data.models

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("score")
    val score: Float,
    @SerializedName("image")
    val image: String,
    @SerializedName("mask")
    val mask: String
)