package com.example.transpapptest.ui.states

import com.example.transpapptest.data.remote.payload.request.VehicleRequest

data class AddEditVehicleState(
    var brand: String = "",
    var model: String = "",
    var year: String = "",
    var vin: String = "",
    var plate: String = "",
    var fuelType: String = "",
    var fuelConsumption: String = "",
    var fuelConsumptionUnit: String = "",
    var cargoVolume: String = "",
    var payload: String = "",
    var isCurrentVehicle: Boolean = false,
) {
    fun toVehicleRequest(): VehicleRequest {
        return VehicleRequest(
            brand = brand,
            model = model,
            year = year,
            vin = vin,
            fuelType = fuelType,
            fuelConsumption = fuelConsumption.toDouble(),
            fuelConsumptionUnit = fuelConsumptionUnit,
            cargoVolume = cargoVolume.toDouble(),
            payload = payload.toDouble(),
            plate = plate,
            isCurrentVehicle = isCurrentVehicle,
        )
    }
}
