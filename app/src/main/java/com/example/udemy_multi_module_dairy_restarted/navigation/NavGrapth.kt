package com.example.udemy_multi_module_dairy_restarted.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.udemy_multi_module_dairy_restarted.presentation.screens.auth.AuthenticationScreen
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.WRITE_SCREEN_ARGUMENT_KEY
import com.stevdzasan.onetap.rememberOneTapSignInState

@Composable
fun setupNavGraph(startDestination: String, navController: NavHostController) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        authentication()
        home()
        write()
    }
}

fun NavGraphBuilder.authentication() {
    composable(route = Screen.Authentication.route) {
        val oneTapState = rememberOneTapSignInState()
        AuthenticationScreen(
            loadingState = oneTapState.opened,
            oneTapState = oneTapState,
            onButtonClicked = {
                oneTapState.open()
            }
        )
    }
}

fun NavGraphBuilder.home() {
    composable(route = Screen.Home.route) {

    }
}

fun NavGraphBuilder.write() {
    composable(
        route = Screen.Write.route,
        arguments = listOf(navArgument(name = WRITE_SCREEN_ARGUMENT_KEY) {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        })
    ) {

    }
}