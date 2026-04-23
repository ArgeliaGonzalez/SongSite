package com.ninive.songsite.features.songs.domain.usecases

import com.ninive.songsite.features.songs.domain.repositories.SongRepository
import javax.inject.Inject

class EditSongUseCase @Inject constructor(
    private val repository: SongRepository
) {
    suspend operator fun invoke(id: Int, titulo: String, artistaId: Int, albumId: Int): Result<Unit> {
        if (titulo.isBlank()) {
            return Result.failure(IllegalArgumentException("El título no puede estar vacío"))
        }
        return runCatching {
            repository.updateSong(id = id, titulo = titulo.trim(), artistaId = artistaId, albumId = albumId)
        }
    }
}