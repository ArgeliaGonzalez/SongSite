package com.ninive.songsite.features.songs.data.repositories

import com.ninive.songsite.features.songs.data.datasources.remote.api.CatalogApi
import com.ninive.songsite.features.songs.data.datasources.remote.mapper.toDomain
import com.ninive.songsite.features.songs.domain.entities.Album
import com.ninive.songsite.features.songs.domain.entities.Artist
import com.ninive.songsite.features.songs.domain.repositories.CatalogRepository
import javax.inject.Inject

/**
 * Implementation of [CatalogRepository].
 * Delegates network calls to [CatalogApi] and maps results to domain entities.
 */
class CatalogRepositoryImpl @Inject constructor(
    private val catalogApi: CatalogApi
) : CatalogRepository {

    override suspend fun getArtists(): List<Artist> =
        catalogApi.getArtists().map { it.toDomain() }

    override suspend fun getAlbumsByArtist(artistId: Int): List<Album> =
        catalogApi.getAlbumsByArtist(artistId).map { it.toDomain() }
}
