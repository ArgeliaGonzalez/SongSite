package com.ninive.songsite.core.di

import com.ninive.songsite.core.navigation.FeatureNavGraph
import com.ninive.songsite.features.auth.navigation.AuthNavGraph
import com.ninive.songsite.features.songs.navigation.SongsNavGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @IntoSet
    abstract fun bindAuthNavGraph(
        authNavGraph: AuthNavGraph
    ): FeatureNavGraph

    @Binds
    @IntoSet
    abstract fun bindSongsNavGraph(
        songsNavGraph: SongsNavGraph
    ): FeatureNavGraph
}
