package com.example.transpapptest.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.transpapptest.ui.navigation.graphs.Graph
import com.example.transpapptest.ui.screens.AddEditAddressScreen
import com.example.transpapptest.ui.screens.AddEditCustomerScreen
import com.example.transpapptest.ui.screens.AddEditVehicleScreen
import com.example.transpapptest.ui.screens.AddressListScreen
import com.example.transpapptest.ui.screens.EditTransporterScreen
import com.example.transpapptest.ui.screens.NotificationsScreen
import com.example.transpapptest.ui.screens.OrderListScreen
import com.example.transpapptest.ui.screens.ProducerDetailScreen
import com.example.transpapptest.ui.screens.ProducerSearchScreen
import com.example.transpapptest.ui.screens.SendOrderScreen
import com.example.transpapptest.ui.screens.ShoppingCartScreen
import com.example.transpapptest.ui.screens.VehicleListScreen
import com.example.transpapptest.ui.states.HomeState
import com.example.transpapptest.ui.viewmodels.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavigation(
    homeViewModel: HomeViewModel = hiltViewModel(),
    homeNavController: NavHostController,
) {
    NavHost(
        route = Graph.HOME,
        navController = homeNavController,
        startDestination = Screen.OrderListScreen.route
    ) {
        composable(
            route = Screen.ShoppingCartScreen.route
        ) {
            ShoppingCartScreen(
                navigateTo = {
                    homeNavController.navigate(it)
                }
            )
        }

        composable(
            route = Screen.ProducerDetailScreen.route + "?producerId={producerId}",
            arguments = listOf(
                navArgument(name = "producerId") {
                    type = NavType.LongType
                    defaultValue = -1
                }
            )
        ) {
            ProducerDetailScreen()
        }

        composable(
            route = Screen.SendOrderScreen.route + "?producerId={producerId}",
            arguments = listOf(
                navArgument(name = "producerId") {
                    type = NavType.LongType
                    defaultValue = -1
                }
            )
        ) {
            SendOrderScreen(
                navigateTo = {
                    homeNavController.navigate(it)
                }
            )
        }


        composable(Screen.AddEditCustomerScreen.route) {
            AddEditCustomerScreen()
        }
        composable(Screen.AddressListScreen.route) {
            AddressListScreen(
                addressListViewModel = hiltViewModel(),
                navigateTo = {
                    homeNavController.navigate(it)
                }
            )
        }

        composable(
            route = Screen.AddEditAddressScreen.route + "?addressId={addressId}",
            arguments = listOf(
                navArgument(name = "addressId") {
                    type = NavType.LongType
                    defaultValue = -1
                }
            )
        ) {
            AddEditAddressScreen(
                navigateTo = {
                    homeNavController.navigate(it) {
                        popUpTo(it) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.OrderListScreen.route) {
            OrderListScreen()
        }

        composable(Screen.EditTransporterScreen.route) {
            EditTransporterScreen()
        }
        composable(Screen.VehicleListScreen.route) {
            VehicleListScreen(
                navigateTo = {
                    homeNavController.navigate(it) {
                        popUpTo(it) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.AddEditVehicleScreen.route + "?vehicleId={vehicleId}",
            arguments = listOf(
                navArgument(name = "vehicleId") {
                    type = NavType.LongType
                    defaultValue = -1
                }
            )
        ) {
            AddEditVehicleScreen(
                navigateTo = {
                    homeNavController.navigate(it) {
                        popUpTo(it) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.NotificationsScreen.route) {
            NotificationsScreen()
        }

    }
}