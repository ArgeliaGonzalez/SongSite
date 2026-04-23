package com.ninive.songsite.features.songs.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ninive.songsite.features.songs.domain.entities.Song
import com.ninive.songsite.features.songs.presentation.components.AddSongDialog
import com.ninive.songsite.features.songs.presentation.components.DeleteSongDialog
import com.ninive.songsite.features.songs.presentation.components.EditSongDialog
import com.ninive.songsite.features.songs.presentation.components.SongListItem
import com.ninive.songsite.features.songs.presentation.components.SongOptionsBottomSheet
import com.ninive.songsite.features.songs.presentation.components.SongsSearchBar
import com.ninive.songsite.features.songs.presentation.viewmodels.SongsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongsScreen(
    viewModel: SongsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    // Show error messages via Snackbar
    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    // Show success messages via Snackbar
    LaunchedEffect(uiState.operationSuccess) {
        uiState.operationSuccess?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Songsite",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                actions = {
                    IconButton(onClick = viewModel::loadSongs) {
                        Icon(
                            imageVector = Icons.Outlined.Refresh,
                            contentDescription = "Recargar",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::onAddClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Añadir canción"
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->

        SongsScreenContent(
            uiState = uiState,
            onSearchQueryChange = viewModel::onSearchQueryChange,
            onSongOptionsClick = viewModel::onSongOptionsClick,
            modifier = Modifier.padding(paddingValues)
        )

        // ─── Bottom sheet ──────────────────────────────────────────────────
        if (uiState.showBottomSheet && uiState.selectedSong != null) {
            SongOptionsBottomSheet(
                song = uiState.selectedSong!!,
                sheetState = bottomSheetState,
                onDismiss = viewModel::dismissBottomSheet,
                onEditClick = viewModel::onEditClick,
                onDeleteClick = viewModel::onDeleteClick
            )
        }

        // ─── Delete dialog ─────────────────────────────────────────────────
        if (uiState.showDeleteDialog && uiState.selectedSong != null) {
            DeleteSongDialog(
                song = uiState.selectedSong!!,
                onConfirm = viewModel::confirmDelete,
                onDismiss = viewModel::dismissDeleteDialog
            )
        }

        // ─── Edit dialog (title-only) ──────────────────────────────────────
        if (uiState.showEditDialog && uiState.selectedSong != null) {
            EditSongDialog(
                uiState = uiState,
                onTitleChange = viewModel::onEditTitleChange,
                onConfirm = viewModel::confirmEdit,
                onDismiss = viewModel::dismissEditDialog
            )
        }

        // ─── Add dialog (cascade: artist → album) ─────────────────────────
        if (uiState.showAddDialog) {
            AddSongDialog(
                uiState = uiState,
                onTitleChange = viewModel::onAddTitleChange,
                onArtistSelected = viewModel::onArtistSelected,
                onAlbumSelected = viewModel::onAlbumSelected,
                onConfirm = viewModel::confirmAdd,
                onDismiss = viewModel::dismissAddDialog
            )
        }
    }
}

// ─── Screen content (stateless) ───────────────────────────────────────────────

@Composable
private fun SongsScreenContent(
    uiState: SongsUiState,
    onSearchQueryChange: (String) -> Unit,
    onSongOptionsClick: (Song) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Mis favoritos",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(12.dp))

        SongsSearchBar(
            query = uiState.searchQuery,
            onQueryChange = onSearchQueryChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            uiState.isLoading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            uiState.error != null && uiState.songs.isEmpty() -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = uiState.error,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            uiState.filteredSongs.isEmpty() -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = if (uiState.searchQuery.isBlank())
                            "No hay canciones aún"
                        else
                            "Sin resultados para \"${uiState.searchQuery}\"",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 88.dp), // FAB clearance
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        items = uiState.filteredSongs,
                        key = { it.id }
                    ) { song ->
                        SongListItem(
                            song = song,
                            onOptionsClick = { onSongOptionsClick(song) }
                        )
                    }
                }
            }
        }
    }
}