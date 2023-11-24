package com.example.udemy_multi_module_dairy_restarted.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.udemy_multi_module_dairy_restarted.presentation.screens.auth.AuthenticationScreen
import com.example.udemy_multi_module_dairy_restarted.presentation.screens.auth.AuthenticationViewModel
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.WRITE_SCREEN_ARGUMENT_KEY
import com.stevdzasan.messagebar.rememberMessageBarState
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
        val viewModel: AuthenticationViewModel = viewModel()
        val loading by viewModel.loadingState
        val oneTapState = rememberOneTapSignInState()
        val messageBarState = rememberMessageBarState()
        AuthenticationScreen(
            loadingState = loading,
            oneTapState = oneTapState,
            messageBarState,
            onTokenReceived = { tokenId ->
                viewModel.signInWithMongoAtlas(tokenId = tokenId,
                    onSuccess = {
                        if (it) {
                            messageBarState.addSuccess("Authentication Successful")
                        }
                    },
                    onError = {
                        println("Error called from here 1")
                        messageBarState.addError(it)
                    })
            },
            onDialogDismissed = { message ->
                println("Error called from here 2")
                viewModel.setLoading(false)
                messageBarState.addError(Exception(message))
            },
            onButtonClicked = {
                oneTapState.open()
                viewModel.setLoading(true)
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