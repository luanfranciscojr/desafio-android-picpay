package com.picpay.desafio.android.user

import androidx.lifecycle.*
import com.desafio.domain.model.UserModel
import com.desafio.domain.usecases.UserUseCase
import com.picpay.desafio.android.util.StatusResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class UserListViewModel(private val dispatcher: CoroutineDispatcher, private val state: SavedStateHandle, private val useCase: UserUseCase) : ViewModel() {

    private val _users = MutableLiveData<List<UserModel>>(state.getLiveData<List<UserModel>>("users").value)
    val users: LiveData<List<UserModel>>
        get() = _users

    private val _status = MutableLiveData<StatusResponse<UserModel>>()
    val status: LiveData<StatusResponse<UserModel>>
        get() = _status

    fun refresh(){
        _status.value = StatusResponse.Load("");
        viewModelScope.launch(dispatcher) {
            try {
                useCase.refreshUsers()
                getUses()
                _status.value = StatusResponse.Success<UserModel>("");
            }catch (exception: Exception){
                _status.value =  StatusResponse.Failure<UserModel>(exception.toString());
            }
        }
    }

    fun getUses(){
        viewModelScope.launch(dispatcher) {
            _users.value = useCase.getLocalUsers()
        }
    }

    fun setSaveState(){
        state.set("users", _users.value);
    }
}