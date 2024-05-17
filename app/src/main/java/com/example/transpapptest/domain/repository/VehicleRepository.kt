package com.example.transpapptest.domain.repository

import com.example.transpapptest.data.local.entities.VehicleEntity
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun getAll(fetchFromRemote: Boolean): Flow<List<VehicleEntity>>
}