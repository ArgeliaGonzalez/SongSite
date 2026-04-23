package com.ninive.songsite.features.songs.data.datasources.remote.api

import com.ninive.songsite.features.songs.data.datasources.remote.model.ArtistDto
import com.ninive.songsite.features.songs.data.datasources.remote.model.AlbumDto
import retrofit2.http.GET
import retrofit2.http.Path


interface CatalogApi {

    @GET("catalog/artists")
    suspend fun getArtists(): List<ArtistDto>

    @GET("catalog/artists/{artistId}/albums")
    suspend fun getAlbumsByArtist(@Path("artistId") artistId: Int): List<AlbumDto>
}
