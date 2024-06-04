package com.example.transpapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.domain.use_cases.vehicle.VehicleUseCases
import com.example.transpapptest.ui.events.AddEditVehicleEvent
import com.example.transpapptest.ui.states.AddEditVehicleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditVehicleViewModel @Inject constructor(
    private val vehicleUseCases: VehicleUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var isAttemptingToSave by mutableStateOf(true)
    private var currentVehicleId: Long? = null
    var state by mutableStateOf(AddEditVehicleState())
    var showDialog by mutableStateOf(false)
    var messageResponse by mutableStateOf("")

    val brandList = listOf(
        "Audi",
        "Benz",
        "BMW",
        "Ferrari",
        "Maserati",
        "Toyota",
        "Lamborghini",
        "Ford",
        "Ford Mustang",
        "Dodge",
        "Bently",
        "Rolls Roys",
        "Tesla",
        "Porsche",
        "Hyunda",
    )

    val yearList = listOf(
        "2000",
        "2001",
        "2002",
        "2003",
        "2004",
        "2005",
        "2006",
        "2007",
        "2008",
        "2009",
        "2010",
        "2011",
        "2012",
        "2013",
        "2014",
        "2015",
        "2016",
        "2017",
        "2018",
        "2019",
        "2020",
        "2021",
        "2022",
        "2023",
        "2024",
    )

    val fuelTypeList = listOf(
        "gasolina", "electrico", "híbrido"
    )

    val fuelConsumptionUnitList = listOf("L/100 Km", "kWh/100 km")


    init {
        val vehicleId = savedStateHandle.get<Long>("vehicleId")
        vehicleId?.let {
            if (it != -1L) {
                isAttemptingToSave = false
                geVehicle(it)
            } else {
                isAttemptingToSave = true
            }
        }
    }

    private fun geVehicle(vehicleId: Long) {
        viewModelScope.launch {
            vehicleUseCases.getVehicle(vehicleId)?.also { vehicle ->
                currentVehicleId = vehicle.id
                state = state.copy(
                    brand = vehicle.brand,
                    model = vehicle.model,
                    year = vehicle.year,
                    vin = vehicle.vin,
                    fuelType = vehicle.fuelType,
                    fuelConsumption = vehicle.fuelConsumption.toString(),
                    fuelConsumptionUnit = vehicle.fuelConsumptionUnit,
                    cargoVolume = vehicle.cargoVolume.toString(),
                    payload = vehicle.payload.toString(),
                )
            }
        }
    }


    fun onEvent(event: AddEditVehicleEvent) {
        when (event) {
            is AddEditVehicleEvent.BrandChanged -> {
                state = state.copy(brand = event.brand)
            }

            is AddEditVehicleEvent.ModelChanged -> {
                state = state.copy(model = event.model)
            }

            is AddEditVehicleEvent.YearChanged -> {
                state = state.copy(year = event.year)
            }

            is AddEditVehicleEvent.VinChanged -> {
                state = state.copy(vin = event.vin)
            }

            is AddEditVehicleEvent.PlateChanged -> {
                state = state.copy(plate = event.plate)
            }

            is AddEditVehicleEvent.FuelTypeChanged -> {
                state = state.copy(fuelType = event.fuelType)
            }

            is AddEditVehicleEvent.FuelConsumptionChanged -> {
                state = state.copy(fuelConsumption = event.fuelConsumption)
            }

            is AddEditVehicleEvent.FuelConsumptionUnitChanged -> {
                state = state.copy(fuelConsumptionUnit = event.fuelConsumptionUnit)
            }

            is AddEditVehicleEvent.CargoVolumeChanged -> {
                state = state.copy(cargoVolume = event.cargoVolume)
            }

            is AddEditVehicleEvent.PayloadChanged -> {
                state = state.copy(payload = event.payload)
            }

            is AddEditVehicleEvent.IsCurrentVehicleChanged -> {
                state = state.copy(isCurrentVehicle = event.isCurrentVehicle)
            }

            AddEditVehicleEvent.SaveBtnClick -> {
                if (isAttemptingToSave) {
                    saveVehicle()
                } else {
                    updateVehicle()
                }
            }


        }
    }

    private fun updateVehicle() {
        TODO("Not yet implemented")
    }

    private fun saveVehicle() {
        viewModelScope.launch {
            vehicleUseCases.addVehicle(state.toVehicleRequest()).collect {
                when (it) {
                    ApiResponse.Waiting -> {}
                    ApiResponse.Loading -> {}
                    is ApiResponse.Failure -> {}
                    is ApiResponse.Success -> {
                        messageResponse = it.data.message
                        showDialog = true
                    }
                }
            }
        }
    }


}

