package com.wally.gallery.di

import com.wally.gallery.domain.GetPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provide() = GetPhotosUseCase()
}