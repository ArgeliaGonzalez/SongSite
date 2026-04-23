package com.ninive.songsite.features.songs.domain.usecases

import com.ninive.songsite.features.songs.domain.entities.Song
import com.ninive.songsite.features.songs.domain.repositories.SongRepository
import javax.inject.Inject

class GetSongsUseCase @Inject constructor(
    private val repository: SongRepository
) {
    suspend operator fun invoke(): Result<List<Song>> {
        return try {
            val result = repository.getSongs()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
