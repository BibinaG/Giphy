package com.example.assignment.model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("original")
    val original: Original?,
    @SerializedName("downsized_small")
    val downsized: Downsize?,
)