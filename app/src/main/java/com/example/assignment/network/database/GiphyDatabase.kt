package com.example.assignment.network.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.assignment.model.TrendingResponse
import com.example.assignment.network.dao.GiphyDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [TrendingResponse::class], version = 1)
abstract class GiphyDatabase : RoomDatabase() {
    abstract fun giphyDao(): GiphyDao

    private class ShipmentDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Instance?.let { database ->
                scope.launch {
                    val giphyDao = database.giphyDao()
                    giphyDao.deleteAll()
                }
            }
        }
    }

    companion object {
        @Volatile
        private var Instance: GiphyDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): GiphyDatabase {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GiphyDatabase::class.java,
                    "shipment_database"
                ).addCallback(ShipmentDatabaseCallback(scope = scope)).build()
                Instance = instance
                instance
            }
        }
    }
}