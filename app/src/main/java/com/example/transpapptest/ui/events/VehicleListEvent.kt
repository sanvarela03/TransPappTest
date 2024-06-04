package com.example.transpapptest.ui.events

sealed class VehicleListEvent {
    data class UpdateBtnClick(val id: Long) : VehicleListEvent()
    data class DeleteBtnClick(val id: Long) : VehicleListEvent()
    object Refresh : VehicleListEvent()
}