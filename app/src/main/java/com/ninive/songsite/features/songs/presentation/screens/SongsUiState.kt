package com.ninive.songsite.features.songs.presentation.screens

import com.ninive.songsite.features.songs.domain.entities.Album
import com.ninive.songsite.features.songs.domain.entities.Artist
import com.ninive.songsite.features.songs.domain.entities.Song

/**
 * Immutable UI state for the Songs screen.
 *
 * Cascade contract:
 *  - [availableArtists] is loaded once when the Add dialog opens.
 *  - [selectedArtist] selection triggers loading of [availableAlbums].
 *  - [availableAlbums] is empty (and the Album selector disabled) until
 *    [selectedArtist] is non-null and albums have been successfully fetched.
 *  - [selectedAlbum] is reset to null whenever [selectedArtist] changes.
 */
data class SongsUiState(
    // ── List state ───────────────────────────────────────────────────────────
    val isLoading: Boolean = false,
    val songs: List<Song> = emptyList(),
    val filteredSongs: List<Song> = emptyList(),
    val error: String? = null,
    val searchQuery: String = "",

    // ── Bottom sheet / Dialog visibility ────────────────────────────────────
    val selectedSong: Song? = null,
    val showBottomSheet: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val showEditDialog: Boolean = false,
    val showAddDialog: Boolean = false,

    // ── Edit form fields (title-only per API contract) ───────────────────────
    val editTitle: String = "",

    // ── Add form: catalog data ───────────────────────────────────────────────
    val isLoadingArtists: Boolean = false,
    val availableArtists: List<Artist> = emptyList(),
    val selectedArtist: Artist? = null,
    val catalogArtistError: String? = null,

    val isLoadingAlbums: Boolean = false,
    val availableAlbums: List<Album> = emptyList(),
    val selectedAlbum: Album? = null,
    val catalogAlbumError: String? = null,

    // ── Add form: title field ────────────────────────────────────────────────
    val addTitle: String = "",

    // ── Operation feedback ───────────────────────────────────────────────────
    val operationSuccess: String? = null
)
