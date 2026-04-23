package com.ninive.songsite.features.songs.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class ArtistDto(
    @SerializedName("id")     val id: Int,
    @SerializedName("nombre") val nombre: String
)

data class AlbumDto(
    @SerializedName("id")        val id: Int,
    @SerializedName("titulo")    val titulo: String,
    @SerializedName("artistaId") val artistaId: Int
)
