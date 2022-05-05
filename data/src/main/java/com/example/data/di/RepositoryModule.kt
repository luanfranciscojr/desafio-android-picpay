package com.desafio.domain.di

import com.desafio.domain.repositories.UserRepository
import com.example.data.db.UserDao
import com.example.data.repositories.UserRepositoryImpl
import com.picpay.data.network.PicPayApi
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {

    fun providerUserRepository(api: PicPayApi, dao: UserDao): UserRepository {
        return UserRepositoryImpl(Dispatchers.IO,api, dao)
    }

    factory { providerUserRepository(get(), get()) }

}