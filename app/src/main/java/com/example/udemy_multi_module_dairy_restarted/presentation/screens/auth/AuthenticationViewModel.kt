package com.example.udemy_multi_module_dairy_restarted.presentation.screens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udemy_multi_module_dairy_restarted.utils.Constants.APP_ID
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel : ViewModel() {

    var authenticated = mutableStateOf(false)
        private set
    var loadingState = mutableStateOf(false)
        private set

    fun setLoading(loading: Boolean) {
        loadingState.value = loading
    }

    fun signInWithMongoAtlas(
        tokenId: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    App.create(APP_ID)
                        .login(
                            Credentials.jwt(tokenId)
//                            Credentials.google(tokenId,GoogleAuthType.ID_TOKEN)
                        ).loggedIn
                }
                withContext(Dispatchers.Main) {
                    loadingState.value = false
                    if (result) {
                        onSuccess()
                        delay(600)
                        authenticated.value = true
                    } else {
                        onError(Exception("User Not loggedin."))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    loadingState.value = false
                    onError(Exception(e))
                }
            }
        }
    }
}