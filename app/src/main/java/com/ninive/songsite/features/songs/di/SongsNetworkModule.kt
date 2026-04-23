package com.ninive.songsite.features.songs.di

import com.ninive.songsite.features.songs.data.datasources.remote.api.CatalogApi
import com.ninive.songsite.features.songs.data.datasources.remote.api.SongsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SongsNetworkModule {

    @Provides
    @Singleton
    fun provideSongsApi(retrofit: Retrofit): SongsApi =
        retrofit.create(SongsApi::class.java)

    @Provides
    @Singleton
    fun provideCatalogApi(retrofit: Retrofit): CatalogApi =
        retrofit.create(CatalogApi::class.java)
}