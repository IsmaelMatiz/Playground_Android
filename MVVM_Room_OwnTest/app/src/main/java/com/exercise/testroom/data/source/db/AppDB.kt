package com.exercise.testroom.data.source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Bussines::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun businessDao(): BussinesDao

    companion object {
        const val DB_NAME = "app_db"

        @Volatile
        private var INSTANCE: AppDB? = null

        fun getInstance(context: Context): AppDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}
