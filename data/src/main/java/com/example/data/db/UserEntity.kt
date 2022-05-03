package com.example.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.desafio.domain.model.UserModel

@Entity(tableName = "user")
data class UserEntity(@PrimaryKey val id: Long,
                      @ColumnInfo val name: String,
                      @ColumnInfo  val img: String,
                      @ColumnInfo val userName: String){

}


fun List<UserEntity>.asDomainModel(): List<UserModel> {
    return map {
        UserModel(
            name = it.name,
            img = it.img,
            userName = it.userName
        )
    }
}