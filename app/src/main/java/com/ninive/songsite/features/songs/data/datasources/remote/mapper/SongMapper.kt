package com.ninive.songsite.features.songs.data.datasources.remote.mapper

import com.ninive.songsite.features.songs.data.datasources.remote.model.ArtistDto
import com.ninive.songsite.features.songs.data.datasources.remote.model.AlbumDto
import com.ninive.songsite.features.songs.data.datasources.remote.model.CreateSongRequestDto
import com.ninive.songsite.features.songs.data.datasources.remote.model.SongDto

import com.ninive.songsite.features.songs.domain.entities.Album
import com.ninive.songsite.features.songs.domain.entities.Artist
import com.ninive.songsite.features.songs.domain.entities.Song

// ─── Song mappers ────────────────────────────────────────────────────────────

fun SongDto.toDomain(): Song = Song(
    id           = id,
    titulo       = titulo,
    artistaId    = artistaId,
    artistaNombre = artistaNombre,
    albumId      = albumId,
    albumNombre  = albumNombre
)

fun Song.toCreateDto(): CreateSongRequestDto = CreateSongRequestDto(
    titulo    = titulo,
    artistaId = artistaId,
    albumId   = albumId
)



// ─── Catalog mappers ──────────────────────────────────────────────────────────

fun ArtistDto.toDomain(): Artist = Artist(id = id, nombre = nombre)

fun AlbumDto.toDomain(): Album = Album(id = id, titulo = titulo, artistaId = artistaId)