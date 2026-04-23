package com.ninive.songsite.features.songs.domain.repositories

import com.ninive.songsite.features.songs.domain.entities.Album
import com.ninive.songsite.features.songs.domain.entities.Artist

/**
 * Domain contract for the catalog data source.
 * Depends only on domain entities — no network or framework types.
 */
interface CatalogRepository {
    suspend fun getArtists(): List<Artist>
    suspend fun getAlbumsByArtist(artistId: Int): List<Album>
}
