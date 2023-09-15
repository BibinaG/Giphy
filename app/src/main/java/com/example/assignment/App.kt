package com.example.assignment

import android.app.Application

class App : Application() {
    companion object {
        lateinit var instance: App
    }
    override fun onCreate() {
        super.onCreate()
        startKoin()
        instance = this

    }

    private fun  startKoin() {


    }
}