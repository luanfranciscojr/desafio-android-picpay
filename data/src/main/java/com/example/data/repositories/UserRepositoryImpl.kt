package com.example.data.repositories

import com.desafio.domain.model.UserModel
import com.desafio.domain.repositories.UserRepository
import com.example.data.db.UserDao
import com.example.data.db.asDomainModel
import com.example.data.dto.asEntity
import com.picpay.data.network.PicPayApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val api: PicPayApi, private val dao: UserDao): UserRepository {

    override suspend fun getUsers(): List<UserModel> {
      return  withContext(Dispatchers.IO) {
           dao.getUsers().asDomainModel()
        }
    }

    override suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val users = api.getUsers().asEntity()
            dao.insertAll(users);
        }
    }
}