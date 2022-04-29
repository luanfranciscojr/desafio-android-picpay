package com.picpay.desafio.android.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafio.domain.usecases.UserUseCase
import kotlinx.coroutines.launch

class UserListViewModel(private val useCase: UserUseCase) : ViewModel() {

    fun  getUses(){
        viewModelScope.launch {
            useCase.refreshUsers()
            val users = useCase.getLocalUsers()
            Log.d("teste", users.toString())
        }
    }
}