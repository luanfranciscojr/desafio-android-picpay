package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserEntity>)

    @Query("select * from user")
    fun getUsers(): List<UserEntity>

    @Query("delete from user")
    fun deleteAll()
}