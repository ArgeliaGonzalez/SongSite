package com.ninive.songsite.features.songs.domain.entities

/**
 * Refactored Song domain entity aligned with the real API contract.
 *
 * The API POST /songs expects { titulo, artistaId, albumId }.
 * The API GET /songs returns { id, titulo, artistaId, artistaNombre, albumId, albumNombre }.
 * We carry the human-readable names for display and the IDs for write operations.
 */
data class Song(
    val id: Int,
    val titulo: String,
    val artistaId: Int,
    val artistaNombre: String,
    val albumId: Int,
    val albumNombre: String
)
