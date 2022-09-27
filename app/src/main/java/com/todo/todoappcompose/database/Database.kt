package com.todo.todoappcompose.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DeviceApps::class],
    version = 2,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun categoryDao(): ApplicationDao
}