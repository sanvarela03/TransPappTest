package com.example.transpapptest.domain.use_cases.transporter

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.domain.repository.TransporterRepository
import kotlinx.coroutines.flow.Flow

class GetTransporter(
    private val repository: TransporterRepository
) {
    operator fun invoke(transporterId: Long): Flow<TransporterEntity> {
        return repository.getTransporter(transporterId)
    }
}