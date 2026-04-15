package com.ninive.songsite.features.auth.domain.repositories

import com.ninive.songsite.features.auth.data.datasources.remote.model.LoginRequest
import com.ninive.songsite.features.auth.data.datasources.remote.model.RegisterRequest
import com.ninive.songsite.features.auth.domain.entities.LoginResponse
import com.ninive.songsite.features.auth.domain.entities.RegisterResponse

interface AuthRepository {
    suspend fun register(request: RegisterRequest): RegisterResponse

    suspend fun login(request: LoginRequest): LoginResponse
}
