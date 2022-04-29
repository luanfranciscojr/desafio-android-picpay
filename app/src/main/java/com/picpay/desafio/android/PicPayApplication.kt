package com.picpay.desafio.android

import android.app.Application
import com.desafio.domain.di.repositoryModule
import com.desafio.domain.di.useCaseModule
import com.picpay.data.di.apiModule
import com.picpay.data.di.networkModule
import com.picpay.desafio.android.di.viewModelModule
import org.koin.core.context.startKoin

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    useCaseModule,
                    apiModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}