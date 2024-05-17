package com.example.transpapptest.domain.use_cases.shoping_cart

import com.example.transpapptest.data.local.entities.ShoppingCartEntity
import com.example.transpapptest.domain.repository.ShoppingCartRepository
import kotlinx.coroutines.flow.Flow

class GetShoppingCartItems(
    private val repository: ShoppingCartRepository
) {
    operator fun invoke(): Flow<List<ShoppingCartEntity>> {
        return repository.getShoppingCartItems()
    }
}
