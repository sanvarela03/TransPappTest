package com.example.transpapptest.domain.use_cases.vehicle

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.remote.payload.request.VehicleRequest
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow

class AddVehicle(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(vehicleRequest: VehicleRequest): Flow<ApiResponse<MessageResponse>> {
        return repository.add(vehicleRequest)
    }
}