package com.tamayo.code_base_sdk.di

import com.tamayo.code_base_sdk.data.rest.BaseRepository
import com.tamayo.code_base_sdk.data.rest.BaseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


/**
 * Dagger module that provides [BaseRepository] dependency.
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    /**
     * Binds [BaseRepositoryImpl] implementation to [BaseRepository] interface.
     * @param baseRepository the implementation of [BaseRepository]
     * @return the [BaseRepository] interface
     */
    @Binds
    abstract fun providesRepository(baseRepository: BaseRepositoryImpl): BaseRepository
}