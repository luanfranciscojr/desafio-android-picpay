package com.picpay.data.di

import com.picpay.data.network.PicPayApi
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun providerPicPayApi(retrofit: Retrofit): PicPayApi {
        return retrofit.create(PicPayApi::class.java)
    }

    single {
        providerPicPayApi(get(parameters = {
            parametersOf(
                    "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
            )
        }))
    }



}