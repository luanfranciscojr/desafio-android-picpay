package com.desafio.domain.di

import com.desafio.domain.repositories.UserRepository
import com.desafio.domain.usecases.UserUseCase
import com.desafio.domain.usecases.UserUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {

    fun providerUserUseCase(repository: UserRepository): UserUseCase {
        return UserUseCaseImpl(repository)
    }

    factory { providerUserUseCase(get()) }
}