package com.ninive.songsite.features.songs.di

import com.ninive.songsite.features.songs.data.repositories.CatalogRepositoryImpl
import com.ninive.songsite.features.songs.data.repositories.SongRepositoryImpl
import com.ninive.songsite.features.songs.domain.repositories.CatalogRepository
import com.ninive.songsite.features.songs.domain.repositories.SongRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SongsRepositoryModule {

    @Binds
    abstract fun bindSongsRepository(
        impl: SongRepositoryImpl
    ): SongRepository

    @Binds
    abstract fun bindCatalogRepository(
        impl: CatalogRepositoryImpl
    ): CatalogRepository
}