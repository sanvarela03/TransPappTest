package com.example.transpapptest.ui.events

sealed class SplashEvent {
    object CheckAuthentication : SplashEvent()
}