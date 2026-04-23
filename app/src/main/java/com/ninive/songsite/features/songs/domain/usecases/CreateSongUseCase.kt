package com.ninive.songsite.features.songs.domain.usecases

import com.ninive.songsite.features.songs.domain.repositories.SongRepository
import javax.inject.Inject

/**
 * Use case: create a new song in the user's favorites.
 *
 * Validates that [titulo] is not blank and that [artistaId] / [albumId]
 * are positive integers before delegating to the repository.
 */
class CreateSongUseCase @Inject constructor(
    private val repository: SongRepository
) {
    suspend operator fun invoke(
        titulo: String,
        artistaId: Int,
        albumId: Int
    ): Result<Unit> {
        if (titulo.isBlank()) {
            return Result.failure(IllegalArgumentException("El título no puede estar vacío"))
        }
        if (artistaId <= 0) {
            return Result.failure(IllegalArgumentException("Debe seleccionar un artista válido"))
        }
        if (albumId <= 0) {
            return Result.failure(IllegalArgumentException("Debe seleccionar un álbum válido"))
        }
        return runCatching {
            repository.createSong(titulo = titulo.trim(), artistaId = artistaId, albumId = albumId)
        }
    }
}