package com.ninive.songsite.features.songs.domain.usecases

import com.ninive.songsite.features.songs.domain.entities.Album
import com.ninive.songsite.features.songs.domain.repositories.CatalogRepository
import javax.inject.Inject


class GetAlbumsByArtistUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository
) {
    suspend operator fun invoke(artistId: Int): Result<List<Album>> {
        if (artistId <= 0) {
            return Result.failure(IllegalArgumentException("ID de artista inválido: $artistId"))
        }
        return runCatching {
            catalogRepository.getAlbumsByArtist(artistId)
        }
    }
}
