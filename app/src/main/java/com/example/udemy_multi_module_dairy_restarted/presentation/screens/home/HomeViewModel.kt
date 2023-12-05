package com.example.udemy_multi_module_dairy_restarted.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udemy_multi_module_dairy_restarted.data.repository.Diaries
import com.example.udemy_multi_module_dairy_restarted.data.repository.MongoDB
import com.example.udemy_multi_module_dairy_restarted.model.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var diaries: MutableState<Diaries> = mutableStateOf(RequestState.Idle)
    init {
        fetchAllDiaries()
    }
    private fun fetchAllDiaries() {
        viewModelScope.launch {
            MongoDB.getAllDiaries().collect { result ->
                println("I am called : $result")
                diaries.value = result
            }
        }
    }
}