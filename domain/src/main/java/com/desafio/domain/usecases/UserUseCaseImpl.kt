package com.desafio.domain.usecases

import com.desafio.domain.model.UserModel
import com.desafio.domain.repositories.UserRepository

class UserUseCaseImpl(private val repository: UserRepository): UserUseCase {

    override suspend fun getLocalUsers(): List<UserModel> {
        return repository.getUsers()
    }

    override suspend fun refreshUsers() {
      repository.refresh()
    }
}