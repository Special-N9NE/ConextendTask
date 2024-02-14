package com.example.qrapp.data.source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): RoomDao
}
