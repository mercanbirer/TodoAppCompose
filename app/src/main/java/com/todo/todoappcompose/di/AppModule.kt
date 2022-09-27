package com.todo.todoappcompose.di

import android.app.Application
import androidx.room.Room
import com.todo.todoappcompose.database.ApplicationDao
import com.todo.todoappcompose.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@ExperimentalSerializationApi
@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDb(application: Application): Database {
        return Room.databaseBuilder(
            application,
            Database::class.java,
            "MExpenseDb"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTripDao(
        mExpenseDb: Database
    ): ApplicationDao = mExpenseDb.categoryDao()
}