package com.example.transpapptest.domain.use_cases.shoping_cart

import com.example.transpapptest.domain.repository.ShoppingCartRepository

class DeleteShoppingCartItem(
    private val repository: ShoppingCartRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteShoppingCartItem(id)
    }
}