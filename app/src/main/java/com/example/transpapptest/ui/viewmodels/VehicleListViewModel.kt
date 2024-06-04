package com.example.transpapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.VehicleEntity
import com.example.transpapptest.domain.use_cases.vehicle.VehicleUseCases
import com.example.transpapptest.ui.events.VehicleListEvent
import com.example.transpapptest.ui.states.VehicleListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val vehicleUseCases: VehicleUseCases
) : ViewModel() {

    var state by mutableStateOf(VehicleListState())

    init {
        getVehicleList()
    }

    private fun getVehicleList(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            vehicleUseCases.getAllVehicles(fetchFromRemote)
                .collect { response: ApiResponse<List<VehicleEntity>> ->
                    when (response) {
                        ApiResponse.Waiting -> {}
                        ApiResponse.Loading -> {}
                        is ApiResponse.Failure -> {}
                        is ApiResponse.Success -> {
                            state = state.copy(vehicleList = response.data)
                        }
                    }
                }
        }
    }


    fun onEvent(event: VehicleListEvent) {
        when (event) {
            is VehicleListEvent.UpdateBtnClick -> {}
            is VehicleListEvent.DeleteBtnClick -> {}
            VehicleListEvent.Refresh -> {
                getVehicleList(fetchFromRemote = true)
            }
        }
    }

}