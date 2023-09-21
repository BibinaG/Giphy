package com.example.assignment.model


import com.example.assignment.model.table.TrendingTable
import com.google.gson.annotations.SerializedName

data class TrendyGiphyResponse(
    @SerializedName("data")
    val `data`: List<TrendingResponse>?,
    @SerializedName("pagination")
    val pagination: Pagination?
)

data class TrendingResponse(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("bitly_gif_url")
    var bitlyGifUrl: String? = null,
    @SerializedName("bitly_url")
    var bitlyUrl: String? = null,
    @SerializedName("content_url")
    var contentUrl: String? = null,
    @SerializedName("embed_url")
    var embedUrl: String? = null,
    @SerializedName("images")
    var image: Image? = null,
    @SerializedName("import_datetime")
    var importDatetime: String? = null,
    @SerializedName("is_sticker")
    var isSticker: Int? = 0,
    @SerializedName("rating")
    var rating: String? = null,

    @SerializedName("slug")
    var slug: String? = null,

    @SerializedName("source")
    var source: String? = null,

    @SerializedName("source_post_url")
    var sourcePostUrl: String? = null,

    @SerializedName("source_tld")
    var sourceTld: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("trending_datetime")
    var trendingDatetime: String? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("url")
    var url: String? = null,
    @SerializedName("username")
    var username: String? = null
) {
    fun getTrendingTable(): TrendingTable {
        return TrendingTable(
            id = id,
            title = title,
            type = type,
            url = image?.downsized?.url,
        )
    }
}


data class Image(
    @SerializedName("original")
    val original: Original?,
    @SerializedName("downsized_medium")
    val downsized: Downsize?,
)

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


data class Original(
    @SerializedName("frames")
    val frames: String?,
    @SerializedName("hash")
    val hash: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("mp4")
    val mp4: String?,
    @SerializedName("mp4_size")
    val mp4Size: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("webp")
    val webp: String?,
    @SerializedName("webp_size")
    val webpSize: String?,
    @SerializedName("width")
    val width: String?
)