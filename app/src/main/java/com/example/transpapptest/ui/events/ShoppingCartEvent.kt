package com.example.transpapptest.ui.events

sealed class ShoppingCartEvent {
    data class DeleteItem(val id: Long) : ShoppingCartEvent()
}

