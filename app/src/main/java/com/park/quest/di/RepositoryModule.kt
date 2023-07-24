package com.park.quest.di

import android.content.Context
import com.park.quest.database.ParksDatabase
import com.park.quest.database.ParksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideParksRepository(
        @ApplicationContext appContext: Context,
        parksDatabase: ParksDatabase
    ): ParksRepository {
        return ParksRepository(appContext, parksDatabase)
    }
}