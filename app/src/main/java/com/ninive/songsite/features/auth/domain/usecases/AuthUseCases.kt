package com.ninive.songsite.features.auth.domain.usecases

data class AuthUseCases(
    val login: LoginUseCase,
    val register: RegisterUseCase,
    val saveToken: SaveTokenUseCase,
)
