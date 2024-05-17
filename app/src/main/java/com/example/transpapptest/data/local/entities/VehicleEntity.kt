package com.example.transpapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VehicleEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val brand: String,
    val model: String,
    val year: String,
    val vin: String,
    val fuelType: String,
    val fuelConsumption: Double,
    val fuelConsumptionUnit: String,
    val cargoVolume: Double,
    val payload: Double,
    val transporterId: Long
)
