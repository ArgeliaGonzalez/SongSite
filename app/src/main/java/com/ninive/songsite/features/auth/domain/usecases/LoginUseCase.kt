package com.ninive.songsite.features.auth.domain.usecases

import com.ninive.songsite.features.auth.data.datasources.remote.model.LoginRequest
import com.ninive.songsite.features.auth.domain.entities.LoginResponse
import com.ninive.songsite.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(request: LoginRequest): Result<LoginResponse> {
        return try {
            val response = authRepository.login(request)
            if (response.token.isNotEmpty()) {
                Result.success(response)
            } else {
                Result.failure(Exception("No se pudo iniciar sesión"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
