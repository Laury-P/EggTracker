package com.openclassroom.eggtracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openclassroom.eggtracker.data.entity.EggLogDto
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface EggLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateEggLog(eggLog: EggLogDto)

    // Complete historic for a Coop
    @Query("SELECT * FROM egg_log WHERE coop_id = :coopId ORDER BY date DESC")
    fun getEggLogsByCoopId(coopId: Long): Flow<List<EggLogDto>>

    // EggLog for a specific day (ex: to display the current day and modify it)
    @Query("SELECT * FROM egg_log WHERE coop_id = :coopId AND date = :date")
    suspend fun getDayEggLogByCoopId(coopId: Long, date: LocalDate): EggLogDto?

    // EggLog for the last 7 days for a specific Coop
    @Query("SELECT * FROM egg_log WHERE coop_id = :coopId ORDER BY date DESC LIMIT 7")
    fun getWeekEggLogByCoopId(coopId: Long): Flow<List<EggLogDto>>

    // Total Egg for a month for a specific Coop
    @Query("SELECT SUM (egg_count) FROM egg_log WHERE coop_id = :coopId AND date LIKE :monthQuery")
    fun getTotalEggsForMonth(coopId: Long, monthQuery: String): Flow<Int?>


}