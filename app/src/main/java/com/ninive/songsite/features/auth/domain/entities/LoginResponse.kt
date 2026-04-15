package com.ninive.songsite.features.auth.domain.entities

data class LoginResponse(
    val token: String,
    val username: String
)
