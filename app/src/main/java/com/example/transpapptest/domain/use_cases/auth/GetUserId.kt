package com.example.transpapptest.domain.use_cases.auth

import android.util.Log
import com.example.transpapptest.domain.repository.AuthRepository

class GetUserId(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Long {
        return repository.getUserId()
    }
}