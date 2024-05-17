package com.example.transpapptest.data.remote.payload.response.transporter.vehicle

import com.example.transpapptest.data.local.entities.VehicleEntity

data class VehicleResponse(
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
) {
    fun toVehicleEntity(): VehicleEntity {
        return VehicleEntity(
            id = id,
            brand = brand,
            model = model,
            year = year,
            vin = vin,
            fuelType = fuelType,
            fuelConsumption = fuelConsumption,
            fuelConsumptionUnit = fuelConsumptionUnit,
            cargoVolume = cargoVolume,
            payload = payload,
            transporterId = transporterId
        )
    }
}
