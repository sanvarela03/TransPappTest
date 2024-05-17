package com.example.transpapptest.domain.repository

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.remote.payload.request.SignInRequest
import com.example.transpapptest.data.remote.payload.request.SignUpRequest
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.data.remote.payload.response.SignInResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(signInRequest: SignInRequest): Flow<ApiResponse<SignInResponse>>
    fun signOut(): Flow<ApiResponse<MessageResponse>>
    fun signUp(signUpRequest: SignUpRequest): Flow<ApiResponse<MessageResponse>>
    fun authenticate(): Flow<Boolean>
    suspend fun getUserId(): Long


}