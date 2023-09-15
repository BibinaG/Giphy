package com.example.assignment.di

import android.content.ContentResolver
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.dsl.module


val appModule = module {
    single { provideResources(get()) }
    single { provideAssetManager(get()) }
    single { provideContentResolver(get()) }
    single { Dispatchers.Default }
    single { CoroutineScope(Dispatchers.Main + Job()) }
}

fun provideResources(context: Context): Resources = context.resources

fun provideAssetManager(resources: Resources): AssetManager = resources.assets

fun provideContentResolver(context: Context): ContentResolver = context.contentResolver