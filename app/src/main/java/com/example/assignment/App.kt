package com.example.assignment

import android.app.Application
import androidx.room.Room
import com.example.assignment.di.appModule
import com.example.assignment.di.netModule
import com.example.assignment.di.provideRoomDatabase
import com.example.assignment.di.repositoryModule
import com.example.assignment.di.roomModule
import com.example.assignment.di.storageModule
import com.example.assignment.di.viewModelModule
import com.example.assignment.network.database.GiphyDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {
    companion object {
        lateinit var instance: App
    }


    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                roomModule,
                viewModelModule,
                storageModule,
                netModule,
                repositoryModule
            )
            androidLogger()
        }
    }


}