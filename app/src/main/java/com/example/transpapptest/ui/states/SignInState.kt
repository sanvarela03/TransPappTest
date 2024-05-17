package com.example.transpapptest.ui.states

data class SignInState(
    var username: String = "",
    var password: String = "",

    var usernameError: Boolean = false,
    var passwordError: Boolean = false
)
