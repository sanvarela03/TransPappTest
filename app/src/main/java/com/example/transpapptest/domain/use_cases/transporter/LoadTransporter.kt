package com.example.transpapptest.domain.use_cases.transporter

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.domain.repository.TransporterRepository
import kotlinx.coroutines.flow.Flow

class LoadTransporter(
    private val repository: TransporterRepository
) {
    operator fun invoke(fetchFromRemote: Boolean): Flow<ApiResponse<MessageResponse>> {
        return repository.loadTransporter(fetchFromRemote)
    }
}