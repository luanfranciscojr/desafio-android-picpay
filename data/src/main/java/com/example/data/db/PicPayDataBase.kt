package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class PicPayDataBase: RoomDatabase() {


    abstract val userDao: UserDao

    companion object {

        @Volatile
        private var INSTANCE: PicPayDataBase? = null

        fun getInstance(context: Context): PicPayDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PicPayDataBase::class.java,
                        "picpay_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}