package com.picpay.data.network

import com.example.data.dto.UserDto
import retrofit2.http.GET

interface PicPayApi {

    @GET("users")
    suspend fun getUsers(): List<UserDto>

}