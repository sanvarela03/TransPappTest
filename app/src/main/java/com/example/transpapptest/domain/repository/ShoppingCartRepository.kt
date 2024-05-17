package com.example.transpapptest.domain.repository

import com.example.transpapptest.data.local.entities.ShoppingCartEntity
import com.example.transpapptest.data.local.entities.relations.ProducerAndShoppingCart
import kotlinx.coroutines.flow.Flow


interface ShoppingCartRepository {
    suspend fun addShoppingCartEntity(shoppingCartEntity: ShoppingCartEntity)
    fun getShoppingCartItems(): Flow<List<ShoppingCartEntity>>
    fun getProducerAndShoppingCart(): Flow<List<ProducerAndShoppingCart>>
    fun getProducerAndShoppingCart(producerId: Long): Flow<ProducerAndShoppingCart>
    suspend fun deleteShoppingCartItem(id: Long)
}