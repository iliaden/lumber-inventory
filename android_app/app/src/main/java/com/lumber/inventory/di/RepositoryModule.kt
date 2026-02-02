package com.lumber.inventory.di

import com.google.gson.Gson
import com.lumber.inventory.data.api.LumberApiService
import com.lumber.inventory.data.repository.LumberRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module providing repository dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLumberRepository(
        apiService: LumberApiService,
        gson: Gson
    ): LumberRepository {
        return LumberRepository(apiService, gson)
    }
}
