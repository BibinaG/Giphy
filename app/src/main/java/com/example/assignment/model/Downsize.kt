package com.example.assignment.model


import com.google.gson.annotations.SerializedName

data class Downsize(
    @SerializedName("height")
    val height: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: String?,
    @SerializedName("mp4")
    val mp4: String?
)