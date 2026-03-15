package com.openclassroom.eggtracker.domain.repository

import com.openclassroom.eggtracker.domain.Coop
import kotlinx.coroutines.flow.Flow

interface CoopRepository {
    suspend fun insertOrUpdateCoop(coop: Coop)
    suspend fun deleteCoop(coop: Coop)
    fun getCoopById(coopId: Long): Flow<Coop?>
    fun getAllCoops(): Flow<List<Coop>>
    fun getCoopCount():Flow<Int>
}