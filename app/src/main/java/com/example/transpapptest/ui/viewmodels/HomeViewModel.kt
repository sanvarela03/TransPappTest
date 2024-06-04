package com.example.transpapptest.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.config.common.BOTTOM_NAVIGATION_ITEMS
import com.example.transpapptest.config.common.DRAWER_NAVIGATION_ITEMS
import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.domain.use_cases.customer.CustomerUseCases
import com.example.transpapptest.ui.events.HomeEvent
import com.example.transpapptest.ui.events.SplashEvent
import com.example.transpapptest.ui.states.HomeState

import com.example.transpapptest.domain.use_cases.auth.AuthUseCases
import com.example.transpapptest.domain.use_cases.transporter.TransporterUseCases
import com.example.transpapptest.security.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val customerUseCases: CustomerUseCases,
    private val transporterUseCases: TransporterUseCases,
    private val tokenManager: TokenManager,
    private val authViewModel: AuthViewModel,
    private val signInViewModel: SignInViewModel
) : ViewModel() {

    var state by mutableStateOf(HomeState())
    val userId = tokenManager.getUserId()
    val bottomNavigationItemsList = BOTTOM_NAVIGATION_ITEMS
    val navigationItemsList = DRAWER_NAVIGATION_ITEMS
    private var signOutJob: Job? = null
    private var loadTransporterJob: Job? = null

    init {
        Log.d("awa", "init1")
        loadTransporter()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SignOutBtnClicked -> {
                signOut(event.context)
            }

            is HomeEvent.Refresh -> {
                loadTransporter(fetchFromRemote = true)
            }
        }
    }

    fun getTransporter(userId: Long?): Flow<TransporterEntity> {
        return transporterUseCases.getTransporter(userId ?: -1L)
    }

    private fun signOut(context: CoroutineContext) {
        Log.d(this.javaClass.simpleName, "signOutTest")
        signOutJob?.cancel()
        signOutJob = CoroutineScope(context).launch(Dispatchers.IO) {
            transporterUseCases.clearLocalTransporter()
            authUseCases.signOut().collect {
                Log.d(this.javaClass.simpleName, "authUseCases.signOut().collect{}")
                when (it) {
                    ApiResponse.Waiting -> {}
                    ApiResponse.Loading -> {}
                    is ApiResponse.Failure -> {
                        val data = it.errorMessage
                        Log.d("ApiResponse", "errorMessage = $data")
                    }

                    is ApiResponse.Success -> {
                        it.data.let {
                            Log.d("ApiResponse", "message = $it")
                            tokenManager.deleteAccessToken()
                            authViewModel.onEvent(SplashEvent.CheckAuthentication)
                        }
                    }

                }
            }
        }
    }

    private fun loadTransporter(fetchFromRemote: Boolean = false) {
        loadTransporterJob?.cancel()
        loadTransporterJob = viewModelScope.launch(Dispatchers.IO) {
            val userId = tokenManager.getUserId().first()
            if (userId != null) {
                transporterUseCases.loadTransporter(userId, fetchFromRemote)
                    .collect { apiResponse ->
                        when (apiResponse) {
                            ApiResponse.Loading -> {}

                            ApiResponse.Waiting -> {}

                            is ApiResponse.Failure -> {

                            }

                            is ApiResponse.Success -> {

                                apiResponse.data.let {
                                    withContext(Dispatchers.Main) {
                                        state = state.copy(
                                            transporterInfoResponse = it
                                        )
                                    }
                                }

                                Log.d(
                                    "HomeViewModel",
                                    "getProducer | Success | state.value = ${state} "
                                )
                            }


                        }
                    }
            }
        }
    }

    override fun onCleared() {
        Log.d("HomeUwO", "onCleared")
        super.onCleared()
        signOutJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }

        loadTransporterJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
    }

}