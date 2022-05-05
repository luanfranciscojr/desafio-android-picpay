package com.picpay.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

enum class ApiStatus { LOADING, ERROR, DONE }

val networkModule = module {
    val connectTimeout: Long = 40
    val readTimeout: Long = 40

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(connectTimeout, SECONDS)
                .readTimeout(readTimeout, SECONDS)
        return okHttpClientBuilder.build()
    }

    fun provideMoshi(): Moshi {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
        return moshi.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    single {
        provideHttpClient()
    }

    single { provideMoshi() }

    single { parameters ->
        provideRetrofit(
                get(),
                parameters.values[0] as String,
                get()
        )
    }
}