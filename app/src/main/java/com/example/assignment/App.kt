package com.example.assignment

import android.app.Application
import com.example.assignment.di.appModule
import com.example.assignment.di.netModule
import com.example.assignment.di.repositoryModule
import com.example.assignment.di.storageModule
import com.example.assignment.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {
    companion object {
        lateinit var instance: App
    }


    override fun onCreate() {
        super.onCreate()
        startKoin()
        instance = this

    }

    fun startKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                viewModelModule,
                storageModule,
                netModule,
                repositoryModule
            )
            androidLogger()
        }
    }



}