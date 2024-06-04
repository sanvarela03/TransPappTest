package com.example.transpapptest.config.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PendingActions
import com.example.transpapptest.data.local.dto.NavigationItem
import com.example.transpapptest.ui.navigation.Screen

private val IP = "192.168.2.109"
private val PORT = "8095"
val HOST_URL = "http://$IP:$PORT"


val BOTTOM_NAVIGATION_ITEMS = listOf(
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
        description = "Mis notificaciones",
        itemId = Screen.NotificationsScreen.route, // TODO
        selectedIcon = Icons.Filled.Notifications,
        unselectedIcon = Icons.Outlined.Notifications
    ),
)

val DRAWER_NAVIGATION_ITEMS = listOf(
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