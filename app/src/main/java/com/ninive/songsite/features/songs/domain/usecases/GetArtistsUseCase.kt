package com.ninive.songsite.features.songs.domain.usecases

import com.ninive.songsite.features.songs.domain.entities.Artist
import com.ninive.songsite.features.songs.domain.repositories.CatalogRepository
import javax.inject.Inject

class GetArtistsUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository
) {
    suspend operator fun invoke(): Result<List<Artist>> = runCatching {
        catalogRepository.getArtists()
    }
}
