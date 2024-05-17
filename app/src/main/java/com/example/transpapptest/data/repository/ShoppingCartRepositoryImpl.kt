package com.example.transpapptest.data.repository

import com.example.transpapptest.data.local.dao.CustomerDao
import com.example.transpapptest.data.local.dao.ShoppingCartDao
import com.example.transpapptest.data.local.entities.ShoppingCartEntity
import com.example.transpapptest.data.local.entities.relations.ProducerAndShoppingCart
import com.example.transpapptest.domain.repository.ShoppingCartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingCartRepositoryImpl @Inject constructor(
    private val dao: CustomerDao,
    private val shoppingCartDao: ShoppingCartDao
) : ShoppingCartRepository {
    override suspend fun addShoppingCartEntity(shoppingCartEntity: ShoppingCartEntity) {
        shoppingCartDao.insertShoppingCartEntity(shoppingCartEntity)
    }

    override fun getShoppingCartItems(): Flow<List<ShoppingCartEntity>> {
        return shoppingCartDao.getShoppingCartEntity()
    }

    override fun getProducerAndShoppingCart(): Flow<List<ProducerAndShoppingCart>> {
        return shoppingCartDao.getProducerAndShoppingCart()
    }

    override fun getProducerAndShoppingCart(producerId: Long): Flow<ProducerAndShoppingCart> {
        return shoppingCartDao.getProducerAndShoppingCart(producerId)
    }

    override suspend fun deleteShoppingCartItem(id: Long) {
        shoppingCartDao.deleteShoppingCartEntity(id)
    }
}