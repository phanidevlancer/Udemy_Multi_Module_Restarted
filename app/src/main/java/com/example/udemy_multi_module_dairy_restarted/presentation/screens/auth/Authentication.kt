package com.example.udemy_multi_module_dairy_restarted.presentation.screens.auth

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.CLIENT_ID
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarState
import com.stevdzasan.onetap.OneTapSignInState
import com.stevdzasan.onetap.OneTapSignInWithGoogle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    loadingState: Boolean,
    oneTapState: OneTapSignInState,
    messageBarState: MessageBarState,
    onButtonClicked: () -> Unit,
    onTokenReceived: (String) -> Unit,
    onDialogDismissed: (String) -> Unit
) {
    Scaffold(content = {
        ContentWithMessageBar(messageBarState = messageBarState) {
            AuthenticationContent(
                loadingState,
                onButtonClicked
            )
        }
    })

    OneTapSignInWithGoogle(
        state = oneTapState,
        clientId = CLIENT_ID,
        onTokenIdReceived = { tokenId ->
            onTokenReceived(tokenId)
        },
        onDialogDismissed = { message ->
            onDialogDismissed(message)
        })
}