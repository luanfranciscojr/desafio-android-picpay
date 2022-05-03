package com.picpay.desafio.android.util

sealed class StatusResponse<T> {
    data class Success<T>(val sucess: String) : StatusResponse<T>()
    data class Failure<T>(val exception: String) : StatusResponse<T>()
    data class Load<T>(val load: String) : StatusResponse<T>()
}