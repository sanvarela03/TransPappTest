package com.example.transpapptest.domain.use_cases.auth

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class SignOut(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<ApiResponse<MessageResponse>> {
        return repository.signOut()
    }
}