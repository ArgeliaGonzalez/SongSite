package com.ninive.songsite.features.songs.domain.usecases

import com.ninive.songsite.features.songs.domain.repositories.SongRepository
import javax.inject.Inject

/**
 * Use case: remove a song from the user's favorites.
 * Returns [Result<Unit>] — the server echoes only a message, not a boolean flag.
 */
class DeleteSongUseCase @Inject constructor(
    private val repository: SongRepository
) {
    suspend operator fun invoke(id: Int): Result<Unit> = runCatching {
        repository.deleteSong(id)
    }
}