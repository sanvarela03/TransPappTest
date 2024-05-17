package com.example.transpapptest.ui.states

import com.example.transpapptest.data.local.entities.ShoppingCartEntity

data class ShoppingCartState(
    val items: List<ShoppingCartEntity> = emptyList()
)
