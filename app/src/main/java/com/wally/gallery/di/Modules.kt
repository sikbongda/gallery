package com.wally.gallery.di

import com.wally.gallery.data.PhotoRepository
import com.wally.gallery.data.PhotoRepositoryImpl
import com.wally.gallery.domain.GetPhotosUseCase
import com.wally.network.PhotoApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provide(repo: PhotoRepository) = GetPhotosUseCase(repo)
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bind(impl: PhotoRepositoryImpl): PhotoRepository
}

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provide(): PhotoApiService = PhotoApiService.create()
}