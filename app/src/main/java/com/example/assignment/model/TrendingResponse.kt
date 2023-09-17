package com.example.assignment.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity(tableName = "giphy_table")
data class TrendingResponse(
    @SerializedName("bitly_gif_url")
    val bitlyGifUrl: String?,
    @SerializedName("bitly_url")
    val bitlyUrl: String?,
    @SerializedName("content_url")
    val contentUrl: String?,
    @SerializedName("embed_url")
    val embedUrl: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("images")
    val image: Image?,
    @SerializedName("import_datetime")
    val importDatetime: String?,
    @SerializedName("is_sticker")
    val isSticker: Int?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("source_post_url")
    val sourcePostUrl: String?,
    @SerializedName("source_tld")
    val sourceTld: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("trending_datetime")
    val trendingDatetime: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("username")
    val username: String?
)