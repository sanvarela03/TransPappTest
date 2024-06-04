package com.example.transpapptest.ui.events

sealed class NotificationsEvent {
    data class DeleteBtnClick(val id: Int) : NotificationsEvent()
}