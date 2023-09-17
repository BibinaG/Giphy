package com.example.assignment.network.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment.model.TrendingResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface GiphyDao {
    @Query("SELECT * FROM giphy_table")
    fun getAllFavourites(): Flow<List<TrendingResponse>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shipmentTable: TrendingResponse)

    @Query("DELETE FROM giphy_table")
    suspend fun deleteAll()

}