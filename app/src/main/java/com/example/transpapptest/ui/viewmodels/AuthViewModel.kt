package com.example.transpapptest.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transpapptest.config.common.UserAuthState
import com.example.transpapptest.ui.events.SplashEvent
import com.example.transpapptest.domain.use_cases.auth.AuthUseCases
import com.example.transpapptest.security.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _isAuthenticated = MutableStateFlow<UserAuthState>(UserAuthState.UNKNOWN)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    private val _userId = MutableStateFlow<Long?>(null)
    val userId = _userId.asStateFlow()

    private var authJob: Job? = null
    fun onEvent(event: SplashEvent) {

        Log.d("AuthViewModel", "onEvent")
        when (event) {
            is SplashEvent.CheckAuthentication -> {
                authJob = viewModelScope.launch(Dispatchers.IO) {
                    val result = authUseCases.authenticate()


                    Log.d("AuthViewModel", "userId = ${userId}")

                    Log.d("SplashViewModel", "result = $result")
                    withContext(Dispatchers.Main) {
                        result.collect {
                            if (it) {
                                _isAuthenticated.emit(UserAuthState.AUTHENTICATED)
                            } else {
                                _isAuthenticated.emit(UserAuthState.UNAUTHENTICATED)
                            }
                        }
                    }

                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        authJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
    }
}