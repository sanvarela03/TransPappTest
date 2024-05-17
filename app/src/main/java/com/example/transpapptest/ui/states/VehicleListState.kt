package com.example.transpapptest.ui.states

import com.example.transpapptest.data.local.entities.VehicleEntity

data class VehicleListState(
    val vehicleList: List<VehicleEntity> = emptyList()
)
