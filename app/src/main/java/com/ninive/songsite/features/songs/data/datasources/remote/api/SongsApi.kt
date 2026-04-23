package com.ninive.songsite.features.songs.data.datasources.remote.api

import com.ninive.songsite.features.songs.data.datasources.remote.model.CreateSongRequestDto
import com.ninive.songsite.features.songs.data.datasources.remote.model.SongDto
import com.ninive.songsite.features.songs.data.datasources.remote.model.UpdateSongRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Retrofit interface for the Songs feature endpoints.
 *
 * POST body for creation: { "titulo": String, "artistaId": Int, "albumId": Int }
 * PUT body for update:    { "titulo": String }
 */
interface SongsApi {

    @GET("songs")
    suspend fun getSongs(): List<SongDto>

    @POST("songs")
    suspend fun createSong(@Body request: CreateSongRequestDto)

    @PUT("songs/{id}")
    suspend fun updateSong(
        @Path("id") id: Int,
        @Body request: UpdateSongRequestDto
    )

    @DELETE("songs/{id}")
    suspend fun deleteSong(@Path("id") id: Int)
}
