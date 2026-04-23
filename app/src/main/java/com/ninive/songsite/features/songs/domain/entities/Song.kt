package com.ninive.songsite.features.songs.domain.entities

data class Song(
    val id: Int,
    val titulo: String,
    val artistaId: Int,
    val artistaNombre: String,
    val albumId: Int,
    val albumNombre: String
)
