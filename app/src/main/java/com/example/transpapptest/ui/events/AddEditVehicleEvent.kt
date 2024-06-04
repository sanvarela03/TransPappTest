package com.example.transpapptest.ui.events

sealed class AddEditVehicleEvent {
    data class BrandChanged(val brand: String) : AddEditVehicleEvent()
    data class ModelChanged(val model: String) : AddEditVehicleEvent()
    data class YearChanged(val year: String) : AddEditVehicleEvent()
    data class VinChanged(val vin: String) : AddEditVehicleEvent()
    data class PlateChanged(val plate: String) : AddEditVehicleEvent()
    data class FuelTypeChanged(val fuelType: String) : AddEditVehicleEvent()
    data class FuelConsumptionChanged(val fuelConsumption: String) : AddEditVehicleEvent()
    data class FuelConsumptionUnitChanged(val fuelConsumptionUnit: String) : AddEditVehicleEvent()
    data class CargoVolumeChanged(val cargoVolume: String) : AddEditVehicleEvent()
    data class PayloadChanged(val payload: String) : AddEditVehicleEvent()
    data class IsCurrentVehicleChanged(val isCurrentVehicle: Boolean) : AddEditVehicleEvent()
    object SaveBtnClick : AddEditVehicleEvent()
}