package com.example.transpapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transpapptest.domain.use_cases.shoping_cart.ShoppingCartUseCases
import com.example.transpapptest.ui.events.ShoppingCartEvent
import com.example.transpapptest.ui.states.ShoppingCartState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val shoppingCartUseCases: ShoppingCartUseCases
) : ViewModel() {

    var state by mutableStateOf(ShoppingCartState())

    var items = shoppingCartUseCases.getShoppingCartItems()
    var producerAndItems = shoppingCartUseCases.getProducerAndShoppingCart()


    init {

    }

    fun onEvent(event: ShoppingCartEvent) {
        when (event) {
            is ShoppingCartEvent.DeleteItem -> {
                deleteItem(event.id)
            }
        }
    }

    private fun deleteItem(id: Long) {
        viewModelScope.launch {
            shoppingCartUseCases.deleteShoppingCartItem(id)
        }
    }

    private fun getItems() {
        viewModelScope.launch {

        }
    }
}