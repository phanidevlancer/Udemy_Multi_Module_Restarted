package com.example.udemy_multi_module_dairy_restarted.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.udemy_multi_module_dairy_restarted.presentation.screens.auth.AuthenticationScreen
import com.example.udemy_multi_module_dairy_restarted.presentation.screens.auth.AuthenticationViewModel
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.APP_ID
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.WRITE_SCREEN_ARGUMENT_KEY
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.launch

@Composable
fun setupNavGraph(startDestination: String, navController: NavHostController) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        authentication(navigateToHome = {
            println("Iam called")
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        })
        home()
        write()
    }
}

fun NavGraphBuilder.authentication(navigateToHome : () -> Unit) {
    composable(route = Screen.Authentication.route) {
        val viewModel: AuthenticationViewModel = viewModel()
        val isAuthenticated by viewModel.authenticated
        val loading by viewModel.loadingState
        val oneTapState = rememberOneTapSignInState()
        val messageBarState = rememberMessageBarState()
        AuthenticationScreen(
            loadingState = loading,
            authenticated = isAuthenticated,
            navigateToHome = navigateToHome,
            oneTapState = oneTapState,
            messageBarState,
            onTokenReceived = { tokenId ->
                viewModel.signInWithMongoAtlas(tokenId = tokenId,
                    onSuccess = {
                        messageBarState.addSuccess("Authentication Successful")
                    },
                    onError = {
                        messageBarState.addError(it)
                    })
            },
            onDialogDismissed = { message ->
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
        val scope = rememberCoroutineScope()
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                scope.launch {
                    App.create(APP_ID).currentUser?.logOut()
                }
            }) {
                Text(text = "Logout")
            }
        }
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