package com.ninive.songsite.features.auth.domain.usecases

import com.ninive.songsite.core.storage.TokenDataStore
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val tokenDataStore: TokenDataStore
) {
    suspend operator fun invoke(token: String) {
        tokenDataStore.saveToken(token)
    }
}
