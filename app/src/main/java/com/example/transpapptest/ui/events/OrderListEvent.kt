package com.example.transpapptest.ui.events

sealed class OrderListEvent {
    data class CheckProductsBtnClick(val id: Long) : OrderListEvent()
    object Refresh : OrderListEvent()
}