package com.todo.todoappcompose.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.todoappcompose.database.DeviceApps
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @ExperimentalCoroutinesApi
@Inject constructor(
    val daoRepo: CategoryRepository,
    @ApplicationContext context: Context
) : ViewModel() {

    suspend fun insertApp(app: DeviceApps) {
        daoRepo.insertApplication(app)
    }

}