package com.openclassroom.eggtracker.data.repository

import com.openclassroom.eggtracker.data.dao.CoopDao
import com.openclassroom.eggtracker.data.mapper.toDomain
import com.openclassroom.eggtracker.data.mapper.toDto
import com.openclassroom.eggtracker.domain.Coop
import com.openclassroom.eggtracker.domain.repository.CoopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalCoopRepository @Inject constructor(private val coopDao: CoopDao) : CoopRepository {

    // TODO Tester le repository

    override suspend fun insertOrUpdateCoop(coop: Coop) {
        coopDao.upsertCoop(coop.toDto())
        //TODO gerer les erreurs
    }

    override suspend fun deleteCoop(coop: Coop) {
        coopDao.deleteCoop(coop.toDto())
        //TODO gerer les erreurs
    }

    override fun getCoopById(coopId: Long): Flow<Coop?>{
        val coop = coopDao.getCoopById(coopId).map { it?.toDomain() }
        return coop
    }

    override fun getAllCoops(): Flow<List<Coop>> {
        val coops = coopDao.getAllCoops().map { list ->
            list.map {
                it.toDomain()
            }
        }
        return coops
    }

    override fun getCoopCount(): Flow<Int>{
        return coopDao.getCoopCount()
    }

}