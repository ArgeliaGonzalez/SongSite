package com.ninive.songsite.features.songs.domain.usecases

import com.ninive.songsite.features.songs.domain.repositories.SongRepository
import javax.inject.Inject

class DeleteSongUseCase @Inject constructor(
    private val repository: SongRepository
) {
    suspend operator fun invoke(id: Int): Result<Unit> = runCatching {
        repository.deleteSong(id)
    }
}       