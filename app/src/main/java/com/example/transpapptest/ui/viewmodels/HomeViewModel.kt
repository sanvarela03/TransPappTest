package com.example.transpapptest.ui.viewmodels

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PendingActions
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.dto.NavigationItem
import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.domain.use_cases.customer.CustomerUseCases
import com.example.transpapptest.ui.events.HomeEvent
import com.example.transpapptest.ui.events.SplashEvent
import com.example.transpapptest.ui.navigation.Screen
import com.example.transpapptest.ui.states.HomeState

import com.example.transpapptest.domain.use_cases.auth.AuthUseCases
import com.example.transpapptest.domain.use_cases.transporter.TransporterUseCases
import com.example.transpapptest.security.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
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

    private val _signOutResponse: MutableState<ApiResponse<MessageResponse>> =
        mutableStateOf(ApiResponse.Loading)
    val signOutResponse = _signOutResponse

    val userId = tokenManager.getUserId()

    var signOutJob: Job? = null
    var getCustomerJob: Job? = null


    fun getTransporter(userId: Long?): Flow<TransporterEntity> {
        return transporterUseCases.getTransporter(userId ?: -1L)
    }

    init {
        loadTransporter()
    }

    suspend fun foo() = authUseCases.getUserId()

    val bottomNavigationItemsList = listOf(
        NavigationItem(
            title = "Inicio",
            icon = Icons.Default.Home,
            description = "Home Screen",
            itemId = Screen.OrderListScreen.route,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationItem(
            title = "Vehiculos",
            icon = Icons.Default.DirectionsCar,
            description = "Mis vehiculos",
            itemId = Screen.VehicleListScreen.route,
            selectedIcon = Icons.Filled.DirectionsCar,
            unselectedIcon = Icons.Outlined.DirectionsCar
        ),
        NavigationItem(
            title = "Pendientes",
            icon = Icons.Default.PendingActions,
            description = "Muestra los pedidos pendientes del productor",
            itemId = Screen.PendingOrdersScreen.route, // TODO
            selectedIcon = Icons.Filled.PendingActions,
            unselectedIcon = Icons.Outlined.PendingActions
        ),
        NavigationItem(
            title = "Notificaciones",
            icon = Icons.Default.Notifications,
            description = "Mis direcciones",
            itemId = Screen.NotificationsScreen.route, // TODO
            selectedIcon = Icons.Filled.Notifications,
            unselectedIcon = Icons.Outlined.Notifications
        ),

        )

    val navigationItemsList = listOf(
        NavigationItem(
            title = "Inicio",
            icon = Icons.Default.Home,
            description = "Home Screen",
            itemId = Screen.OrderListScreen.route
        ),
        NavigationItem(
            title = "Mis pedidos",
            icon = Icons.Default.Person,
            description = "Favorite Screen",
            itemId = Screen.OrderListScreen.route
        ),

        NavigationItem(
            title = "Direcciones",
            icon = Icons.Default.Map,
            description = "Favorite Screen",
            itemId = Screen.AddressListScreen.route
        ),
        NavigationItem(
            title = "Configuracion",
            icon = Icons.Default.Settings,
            description = "Settings Screen",
            itemId = Screen.EditTransporterScreen.route
        ),
    )


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SignOutBtnClicked -> {
                signOut(event.context)
            }

            HomeEvent.Refresh -> {
                loadTransporter(fetchFromRemote = true)
            }
        }
    }

    private fun signOut(context: CoroutineContext) {
        Log.d(this.javaClass.simpleName, "signOutTest")
        signOutJob?.cancel()

        signOutJob = CoroutineScope(context).launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
            transporterUseCases.loadTransporter(fetchFromRemote).collect { apiResponse ->
                when (apiResponse) {
                    ApiResponse.Waiting -> {}
                    ApiResponse.Loading -> {}
                    is ApiResponse.Failure -> {}
                    is ApiResponse.Success -> {
                        Log.d("HomeViewModel", apiResponse.data.message)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        Log.d(this.javaClass.simpleName, "onCleared")
        super.onCleared()
        signOutJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }

        getCustomerJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
    }

}