package com.example.transpapptest.ui.events

import com.example.transpapptest.data.local.entities.TransporterEntity

sealed class OrderListEvent {
    data class CheckProductsBtnClick(val id: Long) : OrderListEvent()
    object Refresh : OrderListEvent()
    data class UpdateAvailabilityBtnClick(val transporter: TransporterEntity) : OrderListEvent()
}