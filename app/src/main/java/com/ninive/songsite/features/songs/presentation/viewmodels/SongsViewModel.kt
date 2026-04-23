package com.ninive.songsite.features.songs.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninive.songsite.features.songs.domain.entities.Artist
import com.ninive.songsite.features.songs.domain.entities.Song
import com.ninive.songsite.features.songs.domain.usecases.SongsUseCases
import com.ninive.songsite.features.songs.presentation.screens.SongsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(
    private val songsUseCases: SongsUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(SongsUiState())
    val uiState: StateFlow<SongsUiState> = _uiState.asStateFlow()

    init {
        loadSongs()
    }

    fun loadSongs() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            songsUseCases.getSongs().fold(
                onSuccess = { songs ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            songs = songs,
                            filteredSongs = filterSongs(songs, it.searchQuery)
                        )
                    }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(isLoading = false, error = e.message ?: "Error desconocido")
                    }
                }
            )
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update {
            it.copy(
                searchQuery = query,
                filteredSongs = filterSongs(it.songs, query)
            )
        }
    }

    private fun filterSongs(songs: List<Song>, query: String): List<Song> {
        if (query.isBlank()) return songs
        val q = query.trim().lowercase()
        return songs.filter {
            it.titulo.lowercase().contains(q) ||
            it.artistaNombre.lowercase().contains(q) ||
            it.albumNombre.lowercase().contains(q)
        }
    }

    fun onSongOptionsClick(song: Song) {
        _uiState.update { it.copy(selectedSong = song, showBottomSheet = true) }
    }

    fun dismissBottomSheet() {
        _uiState.update { it.copy(showBottomSheet = false, selectedSong = null) }
    }


    fun onDeleteClick() {
        _uiState.update { it.copy(showBottomSheet = false, showDeleteDialog = true) }
    }

    fun dismissDeleteDialog() {
        _uiState.update { it.copy(showDeleteDialog = false, selectedSong = null) }
    }

    fun confirmDelete() {
        val song = _uiState.value.selectedSong ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, showDeleteDialog = false) }
            songsUseCases.deleteSong(song.id).fold(
                onSuccess = {
                    _uiState.update { state ->
                        val updatedList = state.songs.filter { it.id != song.id }
                        state.copy(
                            isLoading = false,
                            songs = updatedList,
                            filteredSongs = filterSongs(updatedList, state.searchQuery),
                            selectedSong = null,
                            operationSuccess = "\"${song.titulo}\" eliminada correctamente"
                        )
                    }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(isLoading = false, error = e.message ?: "Error al eliminar")
                    }
                }
            )
        }
    }

    fun onEditClick() {
        val song = _uiState.value.selectedSong ?: return
        _uiState.update {
            it.copy(
                showBottomSheet = false,
                showEditDialog = true,
                editTitle = song.titulo
            )
        }
    }

    fun dismissEditDialog() {
        _uiState.update { it.copy(showEditDialog = false, selectedSong = null, editTitle = "") }
    }

    fun onEditTitleChange(value: String) = _uiState.update { it.copy(editTitle = value) }

    fun confirmEdit() {
        val song = _uiState.value.selectedSong ?: return
        val newTitle = _uiState.value.editTitle
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, showEditDialog = false) }
            songsUseCases.editSong(
                id = song.id,
                titulo = newTitle,
                artistaId = song.artistaId,
                albumId = song.albumId
            ).fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(
                            selectedSong = null,
                            editTitle = "",
                            operationSuccess = "\"$newTitle\" actualizada correctamente"
                        )
                    }
                    loadSongs()
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(isLoading = false, error = e.message ?: "Error al editar")
                    }
                }
            )
        }
    }

    fun onAddClick() {
        _uiState.update {
            it.copy(
                showAddDialog = true,
                addTitle = "",
                selectedArtist = null,
                selectedAlbum = null,
                availableArtists = emptyList(),
                availableAlbums = emptyList(),
                catalogArtistError = null,
                catalogAlbumError = null
            )
        }
        loadArtists()
    }

    fun dismissAddDialog() {
        _uiState.update {
            it.copy(
                showAddDialog = false,
                addTitle = "",
                selectedArtist = null,
                selectedAlbum = null,
                availableArtists = emptyList(),
                availableAlbums = emptyList(),
                catalogArtistError = null,
                catalogAlbumError = null
            )
        }
    }

    fun onAddTitleChange(value: String) = _uiState.update { it.copy(addTitle = value) }

    fun onArtistSelected(artist: Artist) {
        _uiState.update {
            it.copy(
                selectedArtist = artist,
                selectedAlbum = null,
                availableAlbums = emptyList(),
                catalogAlbumError = null
            )
        }
        loadAlbumsForArtist(artist.id)
    }


    fun onAlbumSelected(album: com.ninive.songsite.features.songs.domain.entities.Album) {
        _uiState.update { it.copy(selectedAlbum = album) }
    }

    fun confirmAdd() {
        val state = _uiState.value
        val artistaId = state.selectedArtist?.id ?: return
        val albumId = state.selectedAlbum?.id ?: return
        val titulo = state.addTitle

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, showAddDialog = false) }
            songsUseCases.createSong(
                titulo = titulo,
                artistaId = artistaId,
                albumId = albumId
            ).fold(
                onSuccess = {
                    _uiState.update { it.copy(operationSuccess = "Canción añadida correctamente") }
                    loadSongs()
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(isLoading = false, error = e.message ?: "Error al crear canción")
                    }
                }
            )
        }
    }

    private fun loadArtists() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingArtists = true, catalogArtistError = null) }
            songsUseCases.getArtists().fold(
                onSuccess = { artists ->
                    _uiState.update {
                        it.copy(isLoadingArtists = false, availableArtists = artists)
                    }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(
                            isLoadingArtists = false,
                            catalogArtistError = e.message ?: "Error al cargar artistas"
                        )
                    }
                }
            )
        }
    }

    private fun loadAlbumsForArtist(artistId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingAlbums = true, catalogAlbumError = null) }
            songsUseCases.getAlbumsByArtist(artistId).fold(
                onSuccess = { albums ->
                    _uiState.update {
                        it.copy(isLoadingAlbums = false, availableAlbums = albums)
                    }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(
                            isLoadingAlbums = false,
                            catalogAlbumError = e.message ?: "Error al cargar álbumes"
                        )
                    }
                }
            )
        }
    }

    fun clearError() = _uiState.update { it.copy(error = null) }
    fun clearSuccess() = _uiState.update { it.copy(operationSuccess = null) }
}