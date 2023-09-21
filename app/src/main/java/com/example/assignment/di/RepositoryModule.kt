package com.example.assignment.di

import com.example.assignment.network.database.TrendyDatabaseRepoImpl
import com.example.assignment.network.database.TrendyDatabaseRepository
import com.example.assignment.repository.TrendingGiphyRepo
import com.example.assignment.repository.TrendingGiphyRepoImp
import org.koin.dsl.module


val repositoryModule = module {
    single<TrendingGiphyRepo> { TrendingGiphyRepoImp(get(), get()) }
    single<TrendyDatabaseRepository> { TrendyDatabaseRepoImpl(get(), get()) }
}
