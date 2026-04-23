package com.ninive.songsite.features.songs.domain.entities

/**
 * Domain entity representing an Album from the catalog.
 * Lives exclusively in the domain layer — no framework dependencies.
 */
data class Album(
    val id: Int,
    val titulo: String,
    val artistaId: Int
)
