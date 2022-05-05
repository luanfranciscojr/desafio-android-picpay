package com.picpay.desafio.android.di

import androidx.lifecycle.SavedStateHandle
import com.picpay.desafio.android.user.UserListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel {
            (handle: SavedStateHandle) ->  UserListViewModel(dispatcher =  Dispatchers.IO,useCase = get(), state = handle)
    }
}