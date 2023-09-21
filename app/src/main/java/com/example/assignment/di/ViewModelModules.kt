package com.example.assignment.di

import com.example.assignment.viewmodel.TrendingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TrendingViewModel(get(), get()) }
}