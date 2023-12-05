package com.example.udemy_multi_module_dairy_restarted.model

sealed class RequestState<out T>{
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()

    data class Error(val error: Throwable) : RequestState<Nothing>()
}
