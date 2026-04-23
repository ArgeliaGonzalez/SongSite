package com.ninive.songsite.features.songs.di

import com.ninive.songsite.features.songs.domain.repositories.CatalogRepository
import com.ninive.songsite.features.songs.domain.repositories.SongRepository
import com.ninive.songsite.features.songs.domain.usecases.CreateSongUseCase
import com.ninive.songsite.features.songs.domain.usecases.DeleteSongUseCase
import com.ninive.songsite.features.songs.domain.usecases.EditSongUseCase
import com.ninive.songsite.features.songs.domain.usecases.GetAlbumsByArtistUseCase
import com.ninive.songsite.features.songs.domain.usecases.GetArtistsUseCase
import com.ninive.songsite.features.songs.domain.usecases.GetSongsUseCase
import com.ninive.songsite.features.songs.domain.usecases.SongsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SongsUseCaseModule {

    @Provides
    fun provideSongsUseCases(
        songRepository: SongRepository,
        catalogRepository: CatalogRepository
    ): SongsUseCases = SongsUseCases(
        getSongs            = GetSongsUseCase(songRepository),
        createSong          = CreateSongUseCase(songRepository),
        editSong            = EditSongUseCase(songRepository),
        deleteSong          = DeleteSongUseCase(songRepository),
        getArtists          = GetArtistsUseCase(catalogRepository),
        getAlbumsByArtist   = GetAlbumsByArtistUseCase(catalogRepository)
    )
}