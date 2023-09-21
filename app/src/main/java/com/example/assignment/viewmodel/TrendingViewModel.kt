package com.example.assignment.viewmodel

import com.example.assignment.androidcommon.utils.UiState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.model.TrendingResponse
import com.example.assignment.model.TrendyGiphyResponse
import com.example.assignment.model.table.TrendingTable
import com.example.assignment.network.database.TrendyDatabaseRepository
import com.example.assignment.repository.TrendingGiphyRepo
import kotlinx.coroutines.launch

enum class DataTypes {
    Trending,
    Search,
}

class TrendingViewModel(
    private val trendyRepository: TrendyDatabaseRepository,
    private val repo: TrendingGiphyRepo,
) : ViewModel() {
    private var _dataType = DataTypes.Trending
    val dataType = _dataType;

    fun setDataType(type: DataTypes) {
        _dataType = type
    }

    fun insert(trendingResponse: TrendingResponse) = viewModelScope.launch {
        trendyRepository.insert(trendyGiphy = trendingResponse.getTrendingTable())
    }

    fun delete(response: TrendingTable) = viewModelScope.launch {
        trendyRepository.delete(response)
        getFavGifs()
    }


    private val _trendingGiphy = MutableLiveData<UiState<TrendyGiphyResponse>>()
    val trendingGiphy: LiveData<UiState<TrendyGiphyResponse>> = _trendingGiphy
    fun getAllTrendingGiphy(offset: Int) {
        viewModelScope.launch {
            _trendingGiphy.value = UiState.Loading()
            _trendingGiphy.value = repo.getTrendGiphy(offset)
        }
    }

    private val _searchResultGifs = MutableLiveData<UiState<TrendyGiphyResponse>>()
    val searchResultGifs: LiveData<UiState<TrendyGiphyResponse>> = _searchResultGifs
    fun getSearchItem(searchValue: String?, offset: Int) {
        viewModelScope.launch {
            _searchResultGifs.value = UiState.Loading()
            _searchResultGifs.value = repo.searchItem(searchValue, offset)
        }
    }

    private val _favGifs = MutableLiveData<List<TrendingTable>>()
    val favGifs: LiveData<List<TrendingTable>> = _favGifs
    fun getFavGifs() {
        viewModelScope.launch {
            _favGifs.value = trendyRepository.getAll();
        }
    }

}