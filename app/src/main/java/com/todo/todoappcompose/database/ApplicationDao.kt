package com.todo.todoappcompose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ApplicationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertApplication(application: DeviceApps)



    @Query("SELECT * FROM deviceapps")
    fun list(): Flow<List<DeviceApps>>
}