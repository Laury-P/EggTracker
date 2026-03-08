package com.openclassroom.eggtracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.openclassroom.eggtracker.data.entity.PoultryGroupDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CoopDao {
    @Upsert
    suspend fun upsertCoop(coop: PoultryGroupDto)

    @Delete
    suspend fun deleteCoop(coop: PoultryGroupDto)

    @Query("SELECT * FROM poultry_group WHERE coop_id = :coopId")
     fun getCoopById(coopId: Long): Flow<PoultryGroupDto?>

    @Query("SELECT * FROM poultry_group ORDER BY coop_name")
    fun getAllCoops(): Flow<List<PoultryGroupDto>>

    @Query("SELECT COUNT(*) FROM poultry_group ")
    fun getCoopCount(): Flow<Int>
}
