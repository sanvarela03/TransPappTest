package com.example.transpapptest.domain.use_cases.vehicle

import com.example.transpapptest.data.local.entities.VehicleEntity
import com.example.transpapptest.domain.repository.VehicleRepository

class GetVehicle(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(vehicleId: Long): VehicleEntity? {
        return repository.get(vehicleId)
    }
}