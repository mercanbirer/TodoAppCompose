package com.todo.todoappcompose.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.todoappcompose.database.DeviceApps
import com.todo.todoappcompose.di.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @ExperimentalCoroutinesApi
@Inject constructor(
    val daoRepo: CategoryRepository,
) : ViewModel() {

    val _user = MutableLiveData<List<DeviceApps>>()
    val user: LiveData<List<DeviceApps>>
        get() = _user

    suspend fun insertApp(app: DeviceApps) {
        daoRepo.insertApplication(app)
    }

    fun getList() {
        viewModelScope.launch() {
            val allTodoList: Flow<List<DeviceApps>> = daoRepo.list()
            allTodoList.collect() {
                _user.value = it
            }
        }
    }

}
