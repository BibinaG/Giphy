package com.example.assignment.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.assignment.App
import com.example.assignment.network.dao.GiphyDao
import com.example.assignment.network.database.GiphyDatabase
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val roomModule = module {
    single { provideRoomDatabase(androidContext()) }
    single { getGiphyDao(get()) }
}

fun provideRoomDatabase(context: Context): GiphyDatabase {
    var database: GiphyDatabase? = null
    database = Room.databaseBuilder(context, GiphyDatabase::class.java, "DbName")
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // AppDatabase.onCreate(scope = scope, database = database)
            }
        })
        .build()
    return database
}

fun getGiphyDao(database: GiphyDatabase): GiphyDao {
    return database.giphyDao()
}