package com.todo.todoappcompose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy


@Dao
interface  ApplicationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insertApplication(application: DeviceApps)


}