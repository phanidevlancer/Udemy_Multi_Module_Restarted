package com.example.udemy_multi_module_dairy_restarted

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.udemy_multi_module_dairy_restarted.ui.theme.Udemy_Multi_Module_Dairy_RestartedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            Udemy_Multi_Module_Dairy_RestartedTheme {
            }
        }
    }
}

