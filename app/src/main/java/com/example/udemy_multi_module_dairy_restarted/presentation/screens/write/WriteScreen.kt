package com.example.udemy_multi_module_dairy_restarted.presentation.screens.write

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteScreen(onBackPressed : () -> Unit,
                onDeleteConfirmed : () ->Unit) {
    Scaffold(
        topBar = {
            WriteTopBar(onBackPressed = onBackPressed, onDeleteConfirmed = onDeleteConfirmed)
        },
        content = {

        }
    )
}