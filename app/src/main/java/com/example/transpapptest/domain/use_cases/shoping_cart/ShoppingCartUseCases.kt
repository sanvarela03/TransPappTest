package com.example.transpapptest.domain.use_cases.shoping_cart

data class ShoppingCartUseCases(
    val addShoppingCartItem: AddShoppingCartItem,
    val getShoppingCartItems: GetShoppingCartItems,
    val deleteShoppingCartItem: DeleteShoppingCartItem,
    val getProducerAndShoppingCart: GetProducerAndShoppingCart
)
