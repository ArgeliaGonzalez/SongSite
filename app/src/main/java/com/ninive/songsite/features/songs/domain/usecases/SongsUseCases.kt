package com.ninive.songsite.features.songs.domain.usecases

import javax.inject.Inject

/**
 * Aggregates all Songs-related use cases for clean injection into the ViewModel.
 * Includes catalog use cases needed for the creation form.
 */
data class SongsUseCases @Inject constructor(
    val getSongs: GetSongsUseCase,
    val createSong: CreateSongUseCase,
    val editSong: EditSongUseCase,
    val deleteSong: DeleteSongUseCase,
    val getArtists: GetArtistsUseCase,
    val getAlbumsByArtist: GetAlbumsByArtistUseCase
)
