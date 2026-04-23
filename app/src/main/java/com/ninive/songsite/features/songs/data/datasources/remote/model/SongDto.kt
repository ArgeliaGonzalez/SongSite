package com.ninive.songsite.features.songs.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class SongDto(
    @SerializedName("id")           val id: Int,
    @SerializedName("titulo")       val titulo: String,
    @SerializedName("artistaId")    val artistaId: Int,
    @SerializedName("artistaNombre") val artistaNombre: String,
    @SerializedName("albumId")      val albumId: Int,
    @SerializedName("albumNombre")  val albumNombre: String
)

data class CreateSongRequestDto(
    @SerializedName("titulo")    val titulo: String,
    @SerializedName("artistaId") val artistaId: Int,
    @SerializedName("albumId")   val albumId: Int
)


data class UpdateSongRequestDto(
    @SerializedName("titulo") val titulo: String,
    @SerializedName("artistaId") val artistaId: Int,
    @SerializedName("albumId") val albumId: Int
)
