package com.ninive.songsite.features.songs.domain.repositories

import com.ninive.songsite.features.songs.domain.entities.Album
import com.ninive.songsite.features.songs.domain.entities.Artist

interface CatalogRepository {
    suspend fun getArtists(): List<Artist>
    suspend fun getAlbumsByArtist(artistId: Int): List<Album>
}
