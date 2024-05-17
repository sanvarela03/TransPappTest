package com.example.transpapptest.domain.use_cases.auth

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.remote.payload.request.SignInRequest
import com.example.transpapptest.data.remote.payload.response.SignInResponse
import com.example.transpapptest.domain.repository.AuthRepository

import kotlinx.coroutines.flow.Flow

class SignIn(
    private val repository: AuthRepository
) {
    operator fun invoke(username: String, password: String): Flow<ApiResponse<SignInResponse>> {
        return repository.signIn(SignInRequest(username, password))
    }
}