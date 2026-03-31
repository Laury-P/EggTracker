package com.openclassroom.eggtracker.di

import com.openclassroom.eggtracker.data.repository.LocalCoopRepository
import com.openclassroom.eggtracker.data.repository.LocalEggRepository
import com.openclassroom.eggtracker.domain.repository.CoopRepository
import com.openclassroom.eggtracker.domain.repository.EggRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoopRepository(
        localCoopRepository: LocalCoopRepository
    ): CoopRepository

    @Binds
    @Singleton
    abstract fun bindEggRepository(
        localEggRepository: LocalEggRepository
    ): EggRepository

}