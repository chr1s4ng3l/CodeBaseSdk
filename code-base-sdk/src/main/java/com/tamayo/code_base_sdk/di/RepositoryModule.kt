package com.tamayo.code_base_sdk.di

import com.tamayo.code_base_sdk.rest.BaseRepository
import com.tamayo.code_base_sdk.rest.BaseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(baseRepository: BaseRepositoryImpl): BaseRepository
}