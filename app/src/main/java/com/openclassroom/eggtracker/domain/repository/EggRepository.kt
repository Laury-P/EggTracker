package com.openclassroom.eggtracker.domain.repository


import com.openclassroom.eggtracker.domain.EggLog
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface EggRepository {
    suspend fun insertOrUpdateEgg(eggLog: EggLog)
    fun getEggLogsByDate(date: LocalDate): Flow<List<EggLog>>

}