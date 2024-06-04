package com.example.transpapptest.domain.use_cases.vehicle

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.VehicleEntity
import com.example.transpapptest.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow

class GetAllVehicles(
    private val repository: VehicleRepository
) {
    operator fun invoke(fetchFromRemote: Boolean): Flow<ApiResponse<List<VehicleEntity>>> {
        return repository.getAll(fetchFromRemote)
    }
}