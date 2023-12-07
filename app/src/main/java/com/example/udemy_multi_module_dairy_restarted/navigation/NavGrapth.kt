package com.example.udemy_multi_module_dairy_restarted.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.udemy_multi_module_dairy_restarted.model.RequestState
import com.example.udemy_multi_module_dairy_restarted.presentation.components.DisplayAlertDialog
import com.example.udemy_multi_module_dairy_restarted.presentation.screens.auth.AuthenticationScreen
import com.example.udemy_multi_module_dairy_restarted.presentation.screens.auth.AuthenticationViewModel
import com.example.udemy_multi_module_dairy_restarted.presentation.screens.home.HomeScreen
import com.example.udemy_multi_module_dairy_restarted.presentation.screens.home.HomeViewModel
import com.example.udemy_multi_module_dairy_restarted.presentation.screens.write.WriteScreen
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.APP_ID
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.WRITE_SCREEN_ARGUMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SetupNavGraph(
    startDestination: String, navController: NavHostController, onDataLoaded: () -> Unit
) {
    NavHost(
        startDestination = startDestination, navController = navController
    ) {
        authentication(
            navigateToHome = {
                println("Iam called")
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }, onDataLoaded = onDataLoaded
        )
        home(navigateToWrite = {
            navController.navigate(Screen.Write.route)
        }, navigateToAuth = {
            navController.popBackStack()
            navController.navigate(Screen.Authentication.route)
        }, onDataLoaded = onDataLoaded
        )
        write(onBackPressed = {
            navController.popBackStack()
        })
    }
}

fun NavGraphBuilder.authentication(navigateToHome: () -> Unit, onDataLoaded: () -> Unit) {
    composable(route = Screen.Authentication.route) {
        val viewModel: AuthenticationViewModel = viewModel()
        val isAuthenticated by viewModel.authenticated
        val loading by viewModel.loadingState
        val oneTapState = rememberOneTapSignInState()
        val messageBarState = rememberMessageBarState()

        LaunchedEffect(key1 = Unit) {
            onDataLoaded()
        }

        AuthenticationScreen(loadingState = loading,
            authenticated = isAuthenticated,
            navigateToHome = navigateToHome,
            oneTapState = oneTapState,
            messageBarState,
            onTokenReceived = { tokenId ->
                viewModel.signInWithMongoAtlas(tokenId = tokenId, onSuccess = {
                    messageBarState.addSuccess("Authentication Successful")
                }, onError = {
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
            })
    }
}

fun NavGraphBuilder.home(
    navigateToWrite: () -> Unit, navigateToAuth: () -> Unit, onDataLoaded: () -> Unit
) {
    composable(route = Screen.Home.route) {
        val viewModel: HomeViewModel = viewModel()
        val diaries by viewModel.diaries
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var openAlert by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = diaries) {
            if (diaries !is RequestState.Loading) {
                onDataLoaded()
            }
        }

        HomeScreen(diaries = diaries, onMenuClicked = {
            scope.launch {
                drawerState.open()
            }
        }, drawerState = drawerState, navigateToWrite = navigateToWrite, onSignOutClick = {
            openAlert = true
        })

        DisplayAlertDialog(title = "Sign Out",
            message = "Are you sure you want to logout?",
            dialogOpened = openAlert,
            onYesClicked = {
                openAlert = false
                scope.launch(Dispatchers.IO) {
                    val user = App.create(APP_ID).currentUser
                    user?.let {
                        it.logOut()
                        withContext(Dispatchers.Main) {
                            drawerState.close()
                            navigateToAuth()
                        }
                    }
                }
            },
            onDialogClosed = {
                openAlert = false
            })
    }
}

@OptIn(ExperimentalPagerApi::class)
fun NavGraphBuilder.write(
    onBackPressed: () -> Unit
) {
    composable(
        route = Screen.Write.route,
        arguments = listOf(navArgument(name = WRITE_SCREEN_ARGUMENT_KEY) {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        }),

        ) {
        val pagerState = rememberPagerState()

        WriteScreen(
            onBackPressed = onBackPressed,
            pagerState = pagerState,
            onDeleteConfirmed = {

            })
    }
}