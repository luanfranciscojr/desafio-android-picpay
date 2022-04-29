package com.picpay.desafio.android.di

import com.picpay.desafio.android.user.UserListViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel {
        UserListViewModel(useCase = get())
    }
}