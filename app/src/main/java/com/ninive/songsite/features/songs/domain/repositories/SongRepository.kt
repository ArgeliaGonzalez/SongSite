package com.ninive.songsite.features.songs.domain.repositories

import com.ninive.songsite.features.songs.domain.entities.Song

interface SongRepository {
    suspend fun getSongs(): List<Song>
    suspend fun createSong(titulo: String, artistaId: Int, albumId: Int)
    suspend fun updateSong(id: Int, titulo: String, artistaId: Int, albumId: Int)
    suspend fun deleteSong(id: Int)
}