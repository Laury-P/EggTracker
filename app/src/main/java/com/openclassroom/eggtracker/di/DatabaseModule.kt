package com.openclassroom.eggtracker.di

import android.content.Context
import androidx.room.Room
import com.openclassroom.eggtracker.data.EggTrackerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideEggTrackerDatabase(
        @ApplicationContext context: Context,
        scope: CoroutineScope
    ): EggTrackerDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            EggTrackerDatabase::class.java,
            "egg_tracker_database"
        ).build()

    }

    @Provides
    @Singleton
    fun provideCoopDao(database: EggTrackerDatabase) = database.coopDao()

    @Provides
    @Singleton
    fun provideEggLogDao(database: EggTrackerDatabase) = database.eggLogDao()
}