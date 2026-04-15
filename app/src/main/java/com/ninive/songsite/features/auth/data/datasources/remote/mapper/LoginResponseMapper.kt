package com.ninive.songsite.features.auth.data.datasources.remote.mapper

import com.ninive.songsite.features.auth.data.datasources.remote.model.LoginResponseDto
import com.ninive.songsite.features.auth.domain.entities.LoginResponse

fun LoginResponseDto.loginToDomain(): LoginResponse {
    return LoginResponse(
        token = this.token,
        username = this.username
    )
}
