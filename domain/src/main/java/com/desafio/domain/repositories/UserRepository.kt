package com.desafio.domain.repositories

import com.desafio.domain.model.UserModel

interface UserRepository {
    suspend fun getUsers(): List<UserModel>
    suspend fun refresh()
}