package com.example.transpapptest.domain.repository

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.data.remote.payload.response.customer.CustomerInfoResponse
import com.example.transpapptest.data.remote.payload.response.transporter.TransporterInfoResponse
import kotlinx.coroutines.flow.Flow

interface TransporterRepository {
    fun loadTransporter(fetchFromRemote: Boolean): Flow<ApiResponse<MessageResponse>>
    fun getTransporter(transporterId: Long): Flow<TransporterEntity>

    suspend fun getAll(): List<TransporterEntity>
}