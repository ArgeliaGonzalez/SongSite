package com.ninive.songsite.features.songs.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ninive.songsite.features.songs.domain.entities.Album

/**
 * Exposed dropdown selector for choosing an Album from the catalog.
 *
 * The dropdown is disabled ([enabled] = false) until a valid artist has been selected
 * and albums have been successfully fetched. This enforces the cascade UX contract.
 *
 * @param albums          Available albums for the currently selected artist.
 * @param selectedAlbum   Currently selected album, or null.
 * @param isLoading       True while albums are being fetched.
 * @param enabled         False until an artist is selected and albums are loaded.
 * @param errorMessage    Non-null if the album fetch failed.
 * @param onAlbumSelected Callback when the user picks an album.
 * @param modifier        Optional modifier.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDropdown(
    albums: List<Album>,
    selectedAlbum: Album?,
    isLoading: Boolean,
    enabled: Boolean,
    errorMessage: String?,
    onAlbumSelected: (Album) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded && albums.isNotEmpty() && enabled,
        onExpandedChange = { if (enabled && !isLoading) expanded = it },
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedAlbum?.titulo ?: "",
            onValueChange = {},
            readOnly = true,
            enabled = enabled,
            label = { Text("Álbum *") },
            placeholder = {
                Text(
                    when {
                        !enabled && !isLoading -> "Selecciona un artista primero"
                        isLoading              -> "Cargando álbumes…"
                        albums.isEmpty()       -> "Sin álbumes disponibles"
                        else                   -> "Selecciona un álbum"
                    }
                )
            },
            trailingIcon = {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                }
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            isError = errorMessage != null,
            supportingText = errorMessage?.let { msg ->
                { Text(msg, color = MaterialTheme.colorScheme.error) }
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable)
        )

        ExposedDropdownMenu(
            expanded = expanded && albums.isNotEmpty() && enabled,
            onDismissRequest = { expanded = false }
        ) {
            albums.forEach { album ->
                DropdownMenuItem(
                    text = { Text(album.titulo) },
                    onClick = {
                        onAlbumSelected(album)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
