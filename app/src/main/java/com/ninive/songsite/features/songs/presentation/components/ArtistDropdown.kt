package com.ninive.songsite.features.songs.presentation.components

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ninive.songsite.features.songs.domain.entities.Artist

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistDropdown(
    artists: List<Artist>,
    selectedArtist: Artist?,
    isLoading: Boolean,
    errorMessage: String?,
    onArtistSelected: (Artist) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded && artists.isNotEmpty(),
        onExpandedChange = { if (!isLoading) expanded = it },
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedArtist?.nombre ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Artista *") },
            placeholder = { Text("Selecciona un artista") },
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
            expanded = expanded && artists.isNotEmpty(),
            onDismissRequest = { expanded = false }
        ) {
            artists.forEach { artist ->
                DropdownMenuItem(
                    text = { Text(artist.nombre) },
                    onClick = {
                        onArtistSelected(artist)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
