package com.example.transpapptest.domain.use_cases.shoping_cart

import com.example.transpapptest.data.local.entities.relations.ProducerAndShoppingCart
import com.example.transpapptest.domain.repository.ShoppingCartRepository
import kotlinx.coroutines.flow.Flow

class GetProducerAndShoppingCart(
    private val repository: ShoppingCartRepository
) {
    operator fun invoke(): Flow<List<ProducerAndShoppingCart>> {
        return repository.getProducerAndShoppingCart()
    }

    operator fun invoke(producerId: Long): Flow<ProducerAndShoppingCart> {
        return repository.getProducerAndShoppingCart(producerId)
    }
}