package com.picpay.desafio.android

import android.app.Application
import com.desafio.domain.di.repositoryModule
import com.desafio.domain.di.useCaseModule
import com.picpay.data.di.apiModule
import com.picpay.data.di.networkModule
import org.koin.core.context.startKoin

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                useCaseModule,
                apiModule,
                networkModule,
                repositoryModule
            )
        }
    }
}