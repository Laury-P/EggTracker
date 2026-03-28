package com.openclassroom.eggtracker.data.repository

import com.openclassroom.eggtracker.data.dao.EggLogDao
import com.openclassroom.eggtracker.data.mapper.toDomain
import com.openclassroom.eggtracker.data.mapper.toDto
import com.openclassroom.eggtracker.domain.EggLog
import com.openclassroom.eggtracker.domain.repository.EggRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class LocalEggRepository @Inject constructor(private val eggLogDao: EggLogDao) : EggRepository {

    override suspend fun insertOrUpdateEgg(eggLog: EggLog) {
        eggLogDao.insertOrUpdateEggLog(eggLog.toDto())
    }

    override fun getEggLogsByDate(date: LocalDate): Flow<List<EggLog>> {
       val egglogs = eggLogDao.getEggLogsByDate(date).map { list ->
            list.map {
                it.toDomain()
            }
        }
        return egglogs
    }

}