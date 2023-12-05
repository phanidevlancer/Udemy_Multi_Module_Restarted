package com.example.udemy_multi_module_dairy_restarted.data.repository

import com.example.udemy_multi_module_dairy_restarted.model.Diary
import com.example.udemy_multi_module_dairy_restarted.model.RequestState
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

typealias Diaries = RequestState<Map<LocalDate,List<Diary>>>
interface MongoRepository {
    fun configureTheRealm()
    fun getAllDiaries() : Flow<Diaries>
}