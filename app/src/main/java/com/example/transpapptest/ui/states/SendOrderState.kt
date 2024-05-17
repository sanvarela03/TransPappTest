package com.example.transpapptest.ui.states

import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.relations.ProducerAndShoppingCart
import com.example.transpapptest.data.remote.payload.request.AddOrderRequest
import com.example.transpapptest.data.remote.payload.request.ItemRequest

data class SendOrderState(
    var producerAndShoppingCart: ProducerAndShoppingCart? = null,
    var currentProducerId: Long = -1L,
    var addressId: Long = -1L,
    var paymentMethodId: String = "p0",
    val addressList: List<AddressEntity> = emptyList(),
) {
    fun toAddOrderRequest(): AddOrderRequest {
        return AddOrderRequest(
            producerId = producerAndShoppingCart!!.producer.producerId,
            deliveryAddressId = addressId,
            paymentMethod = paymentMethodId,
            products = producerAndShoppingCart!!.shoppingItems.map { it.toItemRequest() },
        )
    }
}
