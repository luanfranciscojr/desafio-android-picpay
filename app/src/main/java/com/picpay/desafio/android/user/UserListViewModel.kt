package com.picpay.desafio.android.user

import android.util.Log
import androidx.lifecycle.*
import com.desafio.domain.model.UserModel
import com.desafio.domain.usecases.UserUseCase
import kotlinx.coroutines.launch

class UserListViewModel(private val state: SavedStateHandle, private val useCase: UserUseCase) : ViewModel() {

    private val _users = MutableLiveData<List<UserModel>>(state.getLiveData<List<UserModel>>("users").value)
    val users: LiveData<List<UserModel>>
        get() = _users

    fun refresh(){
        viewModelScope.launch {
            try {
                useCase.refreshUsers()
                getUses()
            }catch (exception: Exception){

            }
        }
    }

    fun getUses(){
        viewModelScope.launch {
            _users.value = useCase.getLocalUsers()
        }
    }

    fun setSaveState(){
        state.set("users", _users.value);
    }
}