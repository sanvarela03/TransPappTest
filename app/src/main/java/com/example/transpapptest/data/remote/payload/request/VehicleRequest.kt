package com.example.transpapptest.data.remote.payload.request

data class VehicleRequest(
    val brand: String,
    val model: String,
    val year: String,
    val vin: String,
    val fuelType: String,
    val fuelConsumption: Double,
    val fuelConsumptionUnit: String,
    val cargoVolume: Double,
    val payload: Double,
    val isCurrentVehicle: Boolean
)
