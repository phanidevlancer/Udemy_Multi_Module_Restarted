package com.example.udemy_multi_module_dairy_restarted.presentation.screens.auth

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.APP_ID
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.CLIENT_ID
import com.stevdzasan.onetap.OneTapSignInState
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    loadingState: Boolean,
    oneTapState : OneTapSignInState,
    onButtonClicked: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(content = {
        AuthenticationContent(
            loadingState,
            onButtonClicked
        )
    })

    OneTapSignInWithGoogle(
        state = oneTapState ,
        clientId = CLIENT_ID,
        onTokenIdReceived = { tokenId ->
           Toast.makeText(context,"Token Received",Toast.LENGTH_LONG).show()
        },
        onDialogDismissed = { message ->
            Toast.makeText(context,"Message : $message",Toast.LENGTH_LONG).show()
        })
}