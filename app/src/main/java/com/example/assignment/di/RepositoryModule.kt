package com.example.assignment.di

import com.example.assignment.network.database.GiphyDatabase
import com.example.assignment.network.database.TrendyRepository
import com.example.assignment.repository.TrendingGiphyRepo
import com.example.assignment.repository.TrendingGiphyRepoImp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val repositoryModule = module {
    single<TrendingGiphyRepo> { TrendingGiphyRepoImp(get(), get()) }

    single { TrendyRepository(get()) }
    single { GiphyDatabase.getDatabase(androidContext(), get()) }
}