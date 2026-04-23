package com.ninive.songsite.features.songs.domain.usecases

import javax.inject.Inject

data class SongsUseCases @Inject constructor(
    val getSongs: GetSongsUseCase,
    val createSong: CreateSongUseCase,
    val editSong: EditSongUseCase,
    val deleteSong: DeleteSongUseCase,
    val getArtists: GetArtistsUseCase,
    val getAlbumsByArtist: GetAlbumsByArtistUseCase
)
