package com.example.udemy_multi_module_dairy_restarted

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.udemy_multi_module_dairy_restarted.navigation.Screen
import com.example.udemy_multi_module_dairy_restarted.navigation.setupNavGraph
import com.example.udemy_multi_module_dairy_restarted.ui.theme.Udemy_Multi_Module_Dairy_RestartedTheme
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.APP_ID
import io.realm.kotlin.mongodb.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Udemy_Multi_Module_Dairy_RestartedTheme {
                val navController = rememberNavController()
                setupNavGraph(
                    startDestination = getStartDestination(), navController = navController
                )
            }
        }
    }

    fun getStartDestination(): String {
        val user = App.create(APP_ID).currentUser
        return if (user != null && user.loggedIn) Screen.Home.route else Screen.Authentication.route
    }
}

