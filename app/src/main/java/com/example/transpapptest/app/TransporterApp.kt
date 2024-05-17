package com.example.transpapptest.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.transpapptest.config.common.UserAuthState
import com.example.transpapptest.ui.events.SplashEvent
import com.example.transpapptest.ui.navigation.Screen
import com.example.transpapptest.ui.navigation.graphs.Graph
import com.example.transpapptest.ui.navigation.graphs.authNavGraph
import com.example.transpapptest.ui.screens.HomeScreen
import com.example.transpapptest.ui.screens.SplashScreen
import com.example.transpapptest.ui.viewmodels.AuthViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransporterApp(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        authViewModel.onEvent(SplashEvent.CheckAuthentication)
    }
    val navController = rememberNavController()
    val userState = authViewModel.isAuthenticated.collectAsState().value


    NavHost(
        navController = navController,
        startDestination =
        getStartDestination(userState)

    ) {
        authNavGraph(navController)
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
    }
}

@Composable
private fun getStartDestination(userState: UserAuthState) =
    when (userState) {
        UserAuthState.UNKNOWN -> {
            Screen.SplashScreen.route
        }

        UserAuthState.UNAUTHENTICATED -> {
            Graph.AUTHENTICATION
        }

        UserAuthState.AUTHENTICATED -> {
            Screen.HomeScreen.route
        }
    }