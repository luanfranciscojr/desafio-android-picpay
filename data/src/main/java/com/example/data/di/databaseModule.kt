package com.example.data.di

import android.content.Context
import com.example.data.db.PicPayDataBase
import com.example.data.db.UserDao
import org.koin.dsl.module

val databaseModule = module {
    fun provideDaoModule(context: Context): UserDao {
        return PicPayDataBase.getInstance(context).userDao
    }

    single { provideDaoModule(get()) }
}