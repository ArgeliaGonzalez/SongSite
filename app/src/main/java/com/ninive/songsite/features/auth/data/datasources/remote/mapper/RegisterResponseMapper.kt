package com.ninive.songsite.features.auth.data.datasources.remote.mapper

import com.ninive.songsite.features.auth.data.datasources.remote.model.RegisterResponseDto
import com.ninive.songsite.features.auth.domain.entities.RegisterResponse

fun RegisterResponseDto.registerToDomain(): RegisterResponse {
    return RegisterResponse(
        message = this.message
    )
}
