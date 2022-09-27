package com.todo.todoappcompose.repository

import com.todo.todoappcompose.database.ApplicationDao
import com.todo.todoappcompose.database.DeviceApps
import javax.inject.Inject


class CategoryRepository @Inject constructor(
    private val dao: ApplicationDao,
) {
    suspend fun insertApplication(apps: DeviceApps) = dao.insertApplication(apps)


}