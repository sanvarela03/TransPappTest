package com.example.transpapptest.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.transpapptest.R
import com.example.transpapptest.ui.components.HomeBottomBar
import com.example.transpapptest.ui.events.HomeEvent
import com.example.transpapptest.ui.navigation.HomeNavigation
import com.example.transpapptest.ui.navigation.Screen
import com.example.transpapptest.ui.viewmodels.HomeViewModel
import com.example.transpapptest.ui.components.AppToolBar
import com.example.transpapptest.ui.components.NavigationDrawerBody
import com.example.transpapptest.ui.components.NavigationDrawerHeader
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    HomeDrawer(
        homeViewModel = hiltViewModel(),
        homeNavController = rememberNavController()
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HomeDrawer(
    homeViewModel: HomeViewModel,
    homeNavController: NavHostController
) {
//    val transporter = homeViewModel.transporter.collectAsState(
//        initial = TransporterEntity(
//            transporterId = -1L,
//            username = "NaN",
//            name = "NaN",
//            lastname = "NaN",
//            email = "NaN",
//            currentAddressId = -1L,
//            currentVehicleId = -1L
//        )
//    ).value

    val transporterId = homeViewModel.userId.collectAsState(initial = -1L).value
    Log.d("HomeViewModelInit", "transporterId = $transporterId")

    val transporter =
        homeViewModel.getTransporter(transporterId).collectAsState(initial = null).value
//    val transporter = homeViewModel.transporter.collectAsState(initial = null).value

    Log.d("HomeViewModelInit", "transporter = $transporter")
    val screens by
    remember {
        mutableStateOf(
            listOf(
                Screen.AddressListScreen.route,
                Screen.OrderListScreen.route,
                Screen.ProducerSearchScreen.route
            )
        )
    }

    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showTopBar = screens.any { it == currentDestination?.route }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = homeViewModel.state.isRefreshing
    )

    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle.currentState.name

    Log.d("HomeScreen", "lifecycleOwner : $lifecycleOwner")


    val local = LocalLifecycleOwner.current


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            if (showTopBar) {
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        homeViewModel.onEvent(HomeEvent.Refresh)
                    }
                ) {
                    ModalDrawerSheet {
                        if (transporter != null) {
                            NavigationDrawerHeader(value = transporter.email)
                        }
                        NavigationDrawerBody(
                            homeViewModel.navigationItemsList,
                            navigateTo = {
                                scope.launch { drawerState.apply { if (isClosed) open() else close() } }

                                homeNavController.navigate(it) {
                                    popUpTo(it) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (showTopBar) {
                    AppToolBar(
                        toolbarTitle = stringResource(id = R.string.home),
                        signOutButtonClicked = {
                            Log.d("HomeScreen", "signOutButtonClicked !!!")
                            homeViewModel.onEvent(
                                HomeEvent.SignOutBtnClicked(
                                    local.lifecycle.coroutineScope.coroutineContext
                                )
                            )
                        },
                        navButtonClicked = {
                            scope.launch { drawerState.apply { if (isClosed) open() else close() } }
                        }
                    )
                }
            },
            bottomBar = {
                HomeBottomBar(
                    navigationItems = homeViewModel.bottomNavigationItemsList,
                    currentDestination = homeNavController.currentBackStackEntryAsState().value?.destination,
                    navigateTo = { homeNavController.navigate(it) }
                )
            }
        ) { contentPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                HomeNavigation(homeNavController = homeNavController)
            }
        }
    }
}