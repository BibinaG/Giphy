package com.example.assignment.network.database

import com.example.assignment.model.TrendingResponse
import com.example.assignment.network.dao.GiphyDao
import kotlinx.coroutines.flow.Flow

class TrendyRepository(giphyDatabase: GiphyDatabase) {
    private var gifDao: GiphyDao = giphyDatabase.giphyDao()

    val favGiphy: Flow<List<TrendingResponse>> = gifDao.getAllFavourites()

    suspend fun insert(shipment: TrendingResponse) {
        gifDao.insert(shipmentTable = shipment)
    }

    suspend fun deleteAll() {
        gifDao.deleteAll()
    }
}