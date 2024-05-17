package com.example.transpapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.ShoppingCartEntity
import com.example.transpapptest.domain.use_cases.producer.ProducerUseCases
import com.example.transpapptest.domain.use_cases.shoping_cart.ShoppingCartUseCases
import com.example.transpapptest.ui.events.ProducerDetailEvent
import com.example.transpapptest.ui.states.ProducerDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProducerDetailViewModel @Inject constructor(
    private val producerUseCases: ProducerUseCases,
    private val shoppingCartUseCases: ShoppingCartUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(ProducerDetailState())

    init {
        val producerId = savedStateHandle.get<Long>("producerId")

        producerId?.let { producerId ->
            if (producerId != -1L) {
                getProducer(producerId)
            }
        }
    }

    fun onEvent(event: ProducerDetailEvent) {
        when (event) {
            is ProducerDetailEvent.AddProductToCart -> {
                addToShoppingCart(event.shoppingCartEntity)
            }

            ProducerDetailEvent.Refresh -> {
                if (state.currentProducerId != -1L) {
                    getProducer(
                        producerId = state.currentProducerId,
                        fetchFromRemote = true
                    )
                }
            }
        }
    }

    private fun addToShoppingCart(shoppingCartEntity: ShoppingCartEntity) {
        viewModelScope.launch {
            shoppingCartUseCases.addShoppingCartItem(shoppingCartEntity)
        }
    }

    private fun getProducer(producerId: Long, fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            producerUseCases.searchProducer(producerId, fetchFromRemote).collect {
                when (it) {
                    ApiResponse.Waiting -> {}
                    ApiResponse.Loading -> {}
                    is ApiResponse.Failure -> {}
                    is ApiResponse.Success -> {
                        state = state.copy(
                            currentProducerId = it.data.producer.producerId,
                            producer = it.data.producer,
                            productsList = it.data.productList
                        )
                    }
                }
            }
        }
    }
}