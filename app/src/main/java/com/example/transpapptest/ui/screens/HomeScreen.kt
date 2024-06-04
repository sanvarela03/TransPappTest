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
import kotlin.coroutines.coroutineContext

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    HomeDrawer()
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HomeDrawer(
    viewModel: HomeViewModel = hiltViewModel(),
    homeNavController: NavHostController = rememberNavController()
) {

//    val transporterId = viewModel.userId.collectAsState(initial = -1L).value
//    Log.d("HomeViewModelInit", "transporterId = $transporterId")
//
//    val transporter = viewModel.getTransporter(transporterId).collectAsState(initial = null).value

    val state = viewModel.state.transporterInfoResponse

    Log.d("HomeViewModelInit", "transporter = $state")
    val screens by
    remember {
        mutableStateOf(
            listOf(
                Screen.AddressListScreen.route,
                Screen.OrderListScreen.route,
                Screen.ProducerSearchScreen.route,
                Screen.VehicleListScreen.route,
                Screen.NotificationsScreen.route
            )
        )
    }

    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showTopBar = screens.any { it == currentDestination?.route }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
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
                        viewModel.onEvent(HomeEvent.Refresh)
                    }
                ) {
                    ModalDrawerSheet {
                        if (state != null) {
                            NavigationDrawerHeader(value = state.email)
                        }
                        NavigationDrawerBody(
                            viewModel.navigationItemsList,
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
                            viewModel.onEvent(
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
                    navigationItems = viewModel.bottomNavigationItemsList,
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