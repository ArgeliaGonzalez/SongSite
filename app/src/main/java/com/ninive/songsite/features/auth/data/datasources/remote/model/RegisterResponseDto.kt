package com.ninive.songsite.features.auth.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class RegisterResponseDto(
    @SerializedName("message")
    val message: String
)
