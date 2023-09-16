package com.example.assignment.viewmodel

import com.example.assignment.androidcommon.utils.UiState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.model.TrendyGiphyResponse
import com.example.assignment.repository.TrendingGiphyRepo
import kotlinx.coroutines.launch

enum class DataTypes {
    Trending,
    Search,
    None
}

class TrendingViewModel(
    private val repo: TrendingGiphyRepo
) : ViewModel() {
    private var _dataType = DataTypes.None
    val dataType = _dataType;

    fun setDataType(type: DataTypes) {
        _dataType = type
    }

    private val _trendingGiphy = MutableLiveData<UiState<TrendyGiphyResponse>>()
    val trendingGiphy: LiveData<UiState<TrendyGiphyResponse>> = _trendingGiphy
    fun getAllTrendingGiphy(offset: Int) {
        viewModelScope.launch {
            _trendingGiphy.value = UiState.Loading()
            _trendingGiphy.value = repo.getTrendGiphy(offset)
        }
    }

    fun getSearchItem(searchValue: String?, offset: Int) {
        viewModelScope.launch {
            _trendingGiphy.value = UiState.Loading()
            _trendingGiphy.value = repo.searchItem(searchValue,offset)
        }
    }

}