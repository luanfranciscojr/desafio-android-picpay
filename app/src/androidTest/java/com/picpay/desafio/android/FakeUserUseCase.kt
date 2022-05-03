package com.picpay.desafio.android

import com.desafio.domain.model.UserModel
import com.desafio.domain.usecases.UserUseCase
import org.koin.dsl.module

class FakeUserUseCase: UserUseCase {

    override suspend fun getLocalUsers(): List<UserModel> {
        val user = UserModel("Teste", "ImgUrl", "Teste")
        return arrayListOf(user)
    }

    override suspend fun refreshUsers() {
        val user = UserModel("Teste", "ImgUrl", "Teste")
    }
}

val fakeUserUseCaseModule =module() {
    factory<UserUseCase> { FakeUserUseCase() }
}