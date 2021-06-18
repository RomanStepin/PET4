package com.example.pet4.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pet4.data.models.Place

@Database(entities = arrayOf(Place::class), version = 6, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
   abstract fun getDaoNotifications(): MyDao
}