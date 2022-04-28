package com.example.data.dto

import com.desafio.domain.model.UserModel
import com.example.data.db.UserEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto (
    val id: Long,
    val name: String,
    val img: String,
    @Json(name = "username")  val userName: String
)

fun List<UserDto>.asEntity(): List<UserEntity> {
    return map {
        UserEntity(
            id = it.id,
            name = it.name,
            img = it.img,
            userName = it.userName
        )
    }
}