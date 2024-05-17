package com.example.transpapptest.ui.events

import com.example.transpapptest.data.local.entities.ShoppingCartEntity

sealed class ProducerDetailEvent {
    data class AddProductToCart(val shoppingCartEntity: ShoppingCartEntity) : ProducerDetailEvent()
    object Refresh : ProducerDetailEvent()
}