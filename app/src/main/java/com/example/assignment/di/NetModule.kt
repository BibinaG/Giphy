package com.example.assignment.di

import android.content.Context
import com.example.assignment.di.NetConfig.CONNECT_TIMEOUT
import com.example.assignment.di.NetConfig.READ_TIMEOUT
import com.example.assignment.di.NetConfig.WRITE_TIMEOUT
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.assignment.network.GiphyApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val netModule = module(override = true) {
    single { provideGson() }
    single { provideLoggingInterceptor() }
    single { provideHeaderInterceptor() }
    single { providerChuckerInterceptor(androidContext()) }
    factory { provideRestApiService<GiphyApi>(get(), get(), get(), get()) }
}

object NetConfig {
    const val CONNECT_TIMEOUT = 10L
    const val READ_TIMEOUT = 20L
    const val WRITE_TIMEOUT = 60L
}

private fun provideGson(): Gson = GsonBuilder().setPrettyPrinting().create()

private fun provideLoggingInterceptor() =
    HttpLoggingInterceptor { message ->
    }.apply {
        HttpLoggingInterceptor.Level.BODY
    }

private fun providerChuckerInterceptor(context: Context) =
    ChuckerInterceptor.Builder(context).build()

private fun provideHeaderInterceptor(): Interceptor =
    Interceptor { chain ->
//        val version = BuildConfig.VERSION_NAME
//        val versionCode = BuildConfig.VERSION_CODE
        val request = chain.request().newBuilder()
            .header("api_key", "PYL0VtLFl3E0PdmS6BEwBQNHoXpGA6GV")
            // .header("Accept", "application/json")
            .build()

        chain.proceed(request)
    }


inline fun <reified T> provideRestApiService(
    context: Context,
//    prefs: Prefs,
    headerInterceptor: Interceptor,
    loggingInterceptor: HttpLoggingInterceptor,
    chuckerInterceptor: ChuckerInterceptor
): T {
    val serverUrl = "https://api.giphy.com/v1/gifs/"
    val retrofit = Retrofit.Builder()
        .baseUrl(serverUrl)
        .client(
            createRestOkHttpClient(
                context,
                loggingInterceptor,
                chuckerInterceptor,
                headerInterceptor,
//                prefs
            )
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create()
}

fun createRestOkHttpClient(
    context: Context,
    loggingInterceptor: HttpLoggingInterceptor,
    chuckerInterceptor: ChuckerInterceptor,
    headerInterceptor: Interceptor,
//    prefs: Prefs
): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
        .cache(Cache(context.cacheDir, 10 * 1024 * 1024))  // 10MB
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
        .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MINUTES)
        .addInterceptor(headerInterceptor)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(chuckerInterceptor)
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            // requestBuilder.header("api_key", "PYL0VtLFl3E0PdmS6BEwBQNHoXpGA6GV")
            chain.proceed(requestBuilder.build())
        }

    return builder.build()
}




