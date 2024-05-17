package com.example.transpapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.domain.use_cases.address.AddressUseCases
import com.example.transpapptest.domain.use_cases.order.OrderUseCases
import com.example.transpapptest.ui.events.OrderListEvent
import com.example.transpapptest.ui.states.AddressListState
import com.example.transpapptest.ui.states.OrderListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val orderUseCases: OrderUseCases
) : ViewModel() {
    var state by mutableStateOf(OrderListState())

    init {
        getAllOrders()
    }

    fun onEvent(event: OrderListEvent) {
        when (event) {
            is OrderListEvent.CheckProductsBtnClick -> {

            }
            OrderListEvent.Refresh -> {
                getAllOrders(fetchFromRemote = true)
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