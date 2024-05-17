package com.example.transpapptest.ui.events

sealed class ProducerSearchEvent {
    data class LookForProductsBtnClick(val id: Long) : ProducerSearchEvent()
    object Refresh : ProducerSearchEvent()
}

