package com.ninive.songsite.features.auth.data.datasources.remote.api

import com.ninive.songsite.features.auth.data.datasources.remote.model.LoginRequest
import com.ninive.songsite.features.auth.data.datasources.remote.model.LoginResponseDto
import com.ninive.songsite.features.auth.data.datasources.remote.model.RegisterRequest
import com.ninive.songsite.features.auth.data.datasources.remote.model.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("users/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponseDto

    @POST("users/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponseDto
}
