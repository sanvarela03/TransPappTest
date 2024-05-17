package com.example.transpapptest.ui.events

import kotlin.coroutines.CoroutineContext

sealed class HomeEvent {
    data class SignOutBtnClicked(val context: CoroutineContext) : HomeEvent()
    object Refresh : HomeEvent()
}