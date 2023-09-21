package com.example.assignment.network.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignment.model.table.TrendingTable
import com.example.assignment.network.dao.GiphyDao

@Database(entities = [TrendingTable::class], version = 1)
abstract class GiphyDatabase : RoomDatabase() {
    abstract fun giphyDao(): GiphyDao

    fun clearDatabase(){
        this.clearDatabase();
    }
}