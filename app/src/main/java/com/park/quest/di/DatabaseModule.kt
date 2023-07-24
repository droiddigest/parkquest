package com.park.quest.di

import android.content.Context
import androidx.room.Room
import com.park.quest.database.ParksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesAppsDatabase(@ApplicationContext appContext: Context) : ParksDatabase {
        return Room.databaseBuilder(appContext, ParksDatabase::class.java, ParksDatabase.DATABASE_NAME)
            .build()
    }
}