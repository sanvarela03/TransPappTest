package com.example.transpapptest.domain.repository

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.VehicleEntity
import com.example.transpapptest.data.remote.payload.request.VehicleRequest
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun getAll(fetchFromRemote: Boolean): Flow<ApiResponse<List<VehicleEntity>>>
    suspend fun get(vehicleId: Long): VehicleEntity?
    suspend fun add(vehicleRequest: VehicleRequest): Flow<ApiResponse<MessageResponse>>
}