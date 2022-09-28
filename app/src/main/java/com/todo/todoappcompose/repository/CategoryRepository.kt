package com.todo.todoappcompose.repository

import com.todo.todoappcompose.database.ApplicationDao
import com.todo.todoappcompose.database.DeviceApps
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CategoryRepository @Inject constructor(
    private val dao: ApplicationDao,
) {
    suspend fun insertApplication(apps: DeviceApps) = dao.insertApplication(apps)

    fun list(): Flow<List<DeviceApps>> {
        return dao.list()
    }
    fun removeApp(packageName: DeviceApps) = dao.deleteApp(packageName)

}