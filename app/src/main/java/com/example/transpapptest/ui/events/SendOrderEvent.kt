package com.example.transpapptest.ui.events

sealed class SendOrderEvent {
    data class AddPaymentMethod(val paymentMethod: String) : SendOrderEvent()
    data class SelectDeliveryAddressId(val deliveryAddressId: Long) : SendOrderEvent()
    object SendOrder : SendOrderEvent()
}