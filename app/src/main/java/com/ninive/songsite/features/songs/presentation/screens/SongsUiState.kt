package com.ninive.songsite.features.songs.presentation.screens

import com.ninive.songsite.features.songs.domain.entities.Album
import com.ninive.songsite.features.songs.domain.entities.Artist
import com.ninive.songsite.features.songs.domain.entities.Song


data class SongsUiState(
    val isLoading: Boolean = false,
    val songs: List<Song> = emptyList(),
    val filteredSongs: List<Song> = emptyList(),
    val error: String? = null,
    val searchQuery: String = "",

    val selectedSong: Song? = null,
    val showBottomSheet: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val showEditDialog: Boolean = false,
    val showAddDialog: Boolean = false,

    val editTitle: String = "",

    val availableArtists: List<Artist> = emptyList(),
    val selectedArtist: Artist? = null,
    val catalogArtistError: String? = null,

    val isLoadingAlbums: Boolean = false,
    val availableAlbums: List<Album> = emptyList(),
    val selectedAlbum: Album? = null,
    val catalogAlbumError: String? = null,

    val addTitle: String = "",

    val operationSuccess: String? = null
)
