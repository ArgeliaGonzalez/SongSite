package com.ninive.songsite.features.songs.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ninive.songsite.features.songs.domain.entities.Album
import com.ninive.songsite.features.songs.domain.entities.Artist
import com.ninive.songsite.features.songs.presentation.screens.SongsUiState

@Composable
fun AddSongDialog(
    uiState: SongsUiState,
    onTitleChange: (String) -> Unit,
    onArtistSelected: (Artist) -> Unit,
    onAlbumSelected: (Album) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val albumsEnabled = uiState.selectedArtist != null &&
            !uiState.isLoadingAlbums &&
            uiState.availableAlbums.isNotEmpty()

    val isFormValid = uiState.addTitle.isNotBlank() &&
            uiState.selectedArtist != null &&
            uiState.selectedAlbum != null

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Añadir canción", style = MaterialTheme.typography.titleLarge)
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                // ── Title field ───────────────────────────────────────────────
                OutlinedTextField(
                    value = uiState.addTitle,
                    onValueChange = onTitleChange,
                    label = { Text("Título *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                ArtistDropdown(
                    artists = uiState.availableArtists,
                    selectedArtist = uiState.selectedArtist,
                    isLoading = uiState.isLoadingArtists,
                    errorMessage = uiState.catalogArtistError,
                    onArtistSelected = onArtistSelected
                )

                AlbumDropdown(
                    albums = uiState.availableAlbums,
                    selectedAlbum = uiState.selectedAlbum,
                    isLoading = uiState.isLoadingAlbums,
                    enabled = albumsEnabled,
                    errorMessage = uiState.catalogAlbumError,
                    onAlbumSelected = onAlbumSelected
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirm, enabled = isFormValid) {
                Text("Añadir")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
    )
}
