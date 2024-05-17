package com.example.transpapptest.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.transpapptest.ui.navigation.Screen
import com.example.transpapptest.ui.screens.SignInScreen
import com.example.transpapptest.ui.screens.SignUpScreen
import com.example.transpapptest.ui.screens.TermsAndConditionsScreen


fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = Screen.SignInScreen.route
    ) {

        composable(
            route = Screen.SignInScreen.route
        ) {
            SignInScreen(navController = navController)
        }
        composable(
            route = Screen.SignUpScreen.route
        ) {
            SignUpScreen(
                navigateTo = {
                    navController.navigate(it)
                }
            )
        }

        composable(
            route = Screen.TermsAndConditionsScreen.route
        ) {
            TermsAndConditionsScreen()
        }
    }
}