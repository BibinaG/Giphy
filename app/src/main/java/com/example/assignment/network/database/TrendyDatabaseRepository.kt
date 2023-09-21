package com.example.assignment.network.database

import com.example.assignment.model.table.TrendingTable
import com.example.assignment.network.dao.GiphyDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface TrendyDatabaseRepository {
    suspend fun getAll(): List<TrendingTable>
    suspend fun insert(trendyGiphy: TrendingTable)
    suspend fun deleteAll()
    suspend fun delete(response: TrendingTable)
}

class TrendyDatabaseRepoImpl(
    private val gifDao: GiphyDao,
    private val scope: CoroutineDispatcher
) : TrendyDatabaseRepository {
    override suspend fun getAll(): List<TrendingTable> {
        return withContext(scope) {
            gifDao.getAllFavourites()
        }
    }

    override suspend fun insert(trendyGiphy: TrendingTable) {
        withContext(scope) {
            gifDao.insert(trendyGiphy)
        }
    }

    override suspend fun deleteAll() {
        withContext(scope) {
            gifDao.deleteAll()
        }
    }

    override suspend fun delete(response: TrendingTable) {
        withContext(scope)
        {
            gifDao.delete(response)
        }
    }
}