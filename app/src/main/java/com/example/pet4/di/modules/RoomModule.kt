package com.example.pet4.di.modules

import android.content.Context
import androidx.room.Room
import com.example.pet4.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Модуль для внедрения AppDatabase
 */
@Module
class RoomModule {
    @Provides
    @Singleton
    fun getDatabase(context: Context): AppDatabase {
       return  Room.databaseBuilder(context, AppDatabase::class.java, "base").build()
    }
}