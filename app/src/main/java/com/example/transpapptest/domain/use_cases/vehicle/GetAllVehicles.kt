package com.example.transpapptest.domain.use_cases.vehicle

import com.example.transpapptest.data.local.entities.VehicleEntity
import com.example.transpapptest.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow

class GetAllVehicles(
    private val repository: VehicleRepository
) {
    operator fun invoke(): Flow<List<VehicleEntity>> {
        return repository.getAll()
    }
}