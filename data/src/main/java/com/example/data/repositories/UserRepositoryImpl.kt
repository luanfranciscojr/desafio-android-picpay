package com.example.data.repositories

import com.desafio.domain.model.UserModel
import com.desafio.domain.repositories.UserRepository
import com.example.data.db.UserDao
import com.example.data.db.asDomainModel
import com.example.data.dto.asEntity
import com.picpay.data.network.PicPayApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val dispatcher: CoroutineDispatcher, private val api: PicPayApi, private val dao: UserDao): UserRepository {

    override suspend fun getUsers(): List<UserModel> {
      return  withContext(dispatcher) {
           dao.getUsers().asDomainModel()
        }
    }

    override suspend fun refresh() {
        withContext(dispatcher) {
            val users = api.getUsers().asEntity()
            dao.insertAll(users);
        }
    }
}