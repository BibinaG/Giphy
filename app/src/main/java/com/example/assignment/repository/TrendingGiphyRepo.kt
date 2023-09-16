package com.example.assignment.repository


import com.example.assignment.androidcommon.utils.UiState
import com.example.assignment.androidcommon.handler.handleResponse
import com.example.assignment.androidcommon.handler.doTryCatch
import com.example.assignment.model.TrendyGiphyResponse
import com.example.assignment.network.GiphyApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface TrendingGiphyRepo {
    suspend fun getTrendGiphy(offset: Int): UiState<TrendyGiphyResponse>
    suspend fun searchItem(searchValue: String?, offset: Int): UiState<TrendyGiphyResponse>
}

class TrendingGiphyRepoImp(
    private val giphyApi: GiphyApi,
    private val dispatcher: CoroutineDispatcher,
) : TrendingGiphyRepo {
    override suspend fun getTrendGiphy(offset: Int): UiState<TrendyGiphyResponse> {
        return withContext(dispatcher) {
            doTryCatch {
                giphyApi.getTrendingGiphy(offset = offset).handleResponse()
            }
        }

    }

    override suspend fun searchItem(
        searchValue: String?,
        offset: Int
    ): UiState<TrendyGiphyResponse> {
        return withContext(dispatcher) {
            doTryCatch {
                giphyApi.getSearchData(searchValue = searchValue, offset = offset).handleResponse()
            }
        }
    }


}