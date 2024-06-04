package com.example.transpapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.domain.use_cases.order.OrderUseCases
import com.example.transpapptest.domain.use_cases.transporter.TransporterUseCases
import com.example.transpapptest.security.TokenManager
import com.example.transpapptest.ui.events.OrderListEvent
import com.example.transpapptest.ui.states.OrderListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val orderUseCases: OrderUseCases,
    private val transporterUseCases: TransporterUseCases,
    private val tokenManager: TokenManager
) : ViewModel() {
    var state by mutableStateOf(OrderListState())

    var msgResponse by mutableStateOf("")
    var showConfirmationDialog by mutableStateOf(false)
    var showResponseDialog by mutableStateOf(false)

    val userId = tokenManager.getUserId()

    init {
        getAllOrders()
        getTransporter()
    }

    private fun getTransporter(fetchFromRemote: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            val transporterId = tokenManager.getUserId().first()
            if (transporterId != null) {
                transporterUseCases.loadTransporter(transporterId, fetchFromRemote)
                    .collect {
                        when (it) {
                            ApiResponse.Waiting -> {}
                            ApiResponse.Loading -> {}
                            is ApiResponse.Failure -> {}
                            is ApiResponse.Success -> {
                                withContext(Dispatchers.Main) {
                                    state = state.copy(transporterInfoResponse = it.data)
                                }
                            }
                        }
                    }

            }
        }
    }


    fun onEvent(event: OrderListEvent) {
        when (event) {
            is OrderListEvent.CheckProductsBtnClick -> {

            }

            OrderListEvent.Refresh -> {
                getAllOrders(fetchFromRemote = true)
                getTransporter(fetchFromRemote = true)
            }

            is OrderListEvent.UpdateAvailabilityBtnClick -> {
                updateAvailability(event.transporter)

            }
        }
    }

    fun getTransporter(userId: Long?): Flow<TransporterEntity> {
        return transporterUseCases.getTransporter(userId ?: -1L)
    }

    private fun updateAvailability(transporter: TransporterEntity) {
        viewModelScope.launch {
            transporterUseCases.updateAvailability(!transporter.available).collect {
                when (it) {
                    ApiResponse.Loading -> {}
                    ApiResponse.Waiting -> {}
                    is ApiResponse.Failure -> {}
                    is ApiResponse.Success -> {
                        showResponseDialog = true
                        msgResponse = it.data.message
                        transporter.available = !transporter.available
                        transporterUseCases.updateLocalTransporter(transporter)
                        getTransporter(fetchFromRemote = true)
                    }
                }
            }
        }
    }

    private fun getAllOrders(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            orderUseCases.getAllOrders(fetchFromRemote).collect {
                when (it) {
                    ApiResponse.Waiting -> {}
                    ApiResponse.Loading -> {}
                    is ApiResponse.Failure -> {}
                    is ApiResponse.Success -> {
                        state = state.copy(orderList = it.data)
                    }
                }
            }
        }
    }
}