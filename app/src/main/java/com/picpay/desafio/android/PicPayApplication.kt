package com.picpay.desafio.android

import android.app.Application
import com.desafio.domain.di.repositoryModule
import com.desafio.domain.di.useCaseModule
import com.example.data.di.databaseModule
import com.example.data.di.apiModule
import com.picpay.data.di.networkModule
import com.picpay.desafio.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PicPayApplication)
            modules(
                listOf(
                    apiModule,
                    networkModule,
                    databaseModule,
                    useCaseModule,
                    repositoryModule,
                    viewModelModule

                )
            )
        }
    }
}