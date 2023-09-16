package com.example.assignment.model


import com.google.gson.annotations.SerializedName

data class TrendyGiphyResponse(
    @SerializedName("data")
    val `data`: List<TrendingResponse>?,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("pagination")
    val pagination: Pagination?
)