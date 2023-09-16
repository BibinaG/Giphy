package com.example.assignment.network

import com.example.assignment.model.TrendyGiphyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    companion object {
        private const val TrendingGiphy = "trending"
        private const val search = "searches"
    }

    @GET(TrendingGiphy)
    suspend fun getTrendingGiphy(
        @Query("api_key") apiKey: String = "PYL0VtLFl3E0PdmS6BEwBQNHoXpGA6GV",
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): Response<TrendyGiphyResponse>

    @GET(search)
    suspend fun getSearchData(
        @Query("api_key") apiKey: String = "PYL0VtLFl3E0PdmS6BEwBQNHoXpGA6GV",
        @Query("q") searchValue: String?,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): Response<TrendyGiphyResponse>

}