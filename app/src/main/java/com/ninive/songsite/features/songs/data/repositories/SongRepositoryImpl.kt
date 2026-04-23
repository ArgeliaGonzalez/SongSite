package com.ninive.songsite.features.songs.data.repositories

import com.ninive.songsite.features.songs.data.datasources.remote.api.SongsApi
import com.ninive.songsite.features.songs.data.datasources.remote.model.CreateSongRequestDto
import com.ninive.songsite.features.songs.data.datasources.remote.model.UpdateSongRequestDto
import com.ninive.songsite.features.songs.data.datasources.remote.mapper.toDomain
import com.ninive.songsite.features.songs.domain.entities.Song
import com.ninive.songsite.features.songs.domain.repositories.SongRepository
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val api: SongsApi
) : SongRepository {

    override suspend fun getSongs(): List<Song> =
        api.getSongs().map { it.toDomain() }

    override suspend fun createSong(titulo: String, artistaId: Int, albumId: Int) {
        api.createSong(CreateSongRequestDto(titulo = titulo, artistaId = artistaId, albumId = albumId))
    }

    override suspend fun updateSong(id: Int, titulo: String, artistaId: Int, albumId: Int) {
        api.updateSong(id, UpdateSongRequestDto(titulo = titulo, artistaId = artistaId, albumId = albumId))
    }

    override suspend fun deleteSong(id: Int) {
        api.deleteSong(id)
    }
}