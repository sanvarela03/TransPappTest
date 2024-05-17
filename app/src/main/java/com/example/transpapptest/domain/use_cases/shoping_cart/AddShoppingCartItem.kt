package com.example.transpapptest.domain.use_cases.shoping_cart

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.ShoppingCartEntity
import com.example.transpapptest.data.remote.payload.request.AddressRequest
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.domain.repository.AddressRepository
import com.example.transpapptest.domain.repository.ShoppingCartRepository
import kotlinx.coroutines.flow.Flow

class AddShoppingCartItem(
    private val repository: ShoppingCartRepository
) {
    suspend operator fun invoke(shoppingCartEntity: ShoppingCartEntity) {
        repository.addShoppingCartEntity(shoppingCartEntity)
    }
}