package com.example.assignment.network.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment.model.table.TrendingTable

@Dao
interface GiphyDao {
    @Query("SELECT * FROM giphy_table")
    fun getAllFavourites(): List<TrendingTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trendingResponse: TrendingTable)

    @Query("DELETE FROM giphy_table")
    fun deleteAll()

    @Delete
    fun delete(response: TrendingTable)
}