package com.ninive.songsite.features.songs.domain.entities

/**
 * Domain entity representing an Artist from the catalog.
 * Lives exclusively in the domain layer — no framework dependencies.
 */
data class Artist(
    val id: Int,
    val nombre: String
)
