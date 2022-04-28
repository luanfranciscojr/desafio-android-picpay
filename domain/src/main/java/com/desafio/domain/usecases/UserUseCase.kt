package com.desafio.domain.usecases

import com.desafio.domain.model.UserModel

interface UserUseCase {

    suspend fun getLocalUsers(): List<UserModel>

    suspend fun refreshUsers()
}