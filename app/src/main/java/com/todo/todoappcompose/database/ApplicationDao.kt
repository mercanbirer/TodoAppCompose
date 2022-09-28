package com.todo.todoappcompose.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ApplicationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertApplication(application: DeviceApps)

    @Query("SELECT * FROM deviceapps")
    fun list(): Flow<List<DeviceApps>>


    @Delete
    fun deleteApp(title: DeviceApps)
}