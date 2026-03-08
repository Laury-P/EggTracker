package com.openclassroom.eggtracker

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.openclassroom.eggtracker.data.EggTrackerDatabase
import com.openclassroom.eggtracker.data.dao.CoopDao
import com.openclassroom.eggtracker.data.dao.EggLogDao
import com.openclassroom.eggtracker.data.entity.EggLogDto
import com.openclassroom.eggtracker.data.entity.PoultryGroupDto
import com.openclassroom.eggtracker.domain.PoultryType
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class EggLogDaoTest {
    private lateinit var database: EggTrackerDatabase
    private lateinit var eggLogDao: EggLogDao
    private lateinit var coopDao: CoopDao


    @Before
    fun createDb() {
        database = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                EggTrackerDatabase::class.java
            ).allowMainThreadQueries()
            .build()

        eggLogDao = database.eggLogDao()
        coopDao = database.coopDao()
    }

    private suspend fun insertTestCoop(id: Long? = null, name: String = "Test Coop") {
        coopDao.upsertCoop(
            PoultryGroupDto(
                coopId = id,
                coopName = name,
                poultryType = PoultryType.CHICKEN,
                birdCount = 10
            )
        )
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertOrUpdateEggLog() = runBlocking {
        insertTestCoop(id = 1)

        val todayEggLog = EggLogDto(
            coopId = 1,
            birdCount = 10,
            date = LocalDate.now(),
            eggCount = 9,
        )

        eggLogDao.insertOrUpdateEggLog(todayEggLog)

        val retrievedEggLog = eggLogDao.getDayEggLogByCoopId(1, LocalDate.now())
        val expectedEggLog = todayEggLog.copy(eggLogId = 1)

        assertEquals(expectedEggLog,retrievedEggLog )
    }

    @Test
    fun deletedCoop_ShouldDeleteEggLog() = runBlocking {
        insertTestCoop(id = 1)

        val todayEggLog = EggLogDto(
            coopId = 1,
            birdCount = 10,
            date = LocalDate.now(),
            eggCount = 9)

        eggLogDao.insertOrUpdateEggLog(todayEggLog)

        coopDao.deleteCoop(PoultryGroupDto(
            coopId = 1,
            coopName = "Test Coop",
            poultryType = PoultryType.CHICKEN,
            birdCount = 10
        ))

        val retrievedEggLog = eggLogDao.getDayEggLogByCoopId(1, LocalDate.now())

        assertNull(retrievedEggLog)
    }

    @Test
    fun getWeekEggLogByCoopId_ReturnsOnlySevenLastLogs() = runBlocking{
        insertTestCoop(id = 1)

        for( i in 1..10){
            eggLogDao.insertOrUpdateEggLog(
                EggLogDto(
                    date = LocalDate.now().minusDays(i.toLong()),
                    coopId = 1,
                    birdCount = 10,
                    eggCount = i,
                )
            )
        }

        val weekEggLog = eggLogDao.getWeekEggLogByCoopId(1).first()

        assert(weekEggLog.size == 7)
        // Verify that the first log is the most recent
        assertEquals(LocalDate.now().minusDays(1), weekEggLog[0].date)
    }

    @Test
    fun getEggLogsByCoopId_ReturnsOnlyThisCoopLog() = runBlocking {
        insertTestCoop(id = 1)
        insertTestCoop(id = 2)

        val coop1EggLog = EggLogDto(
            coopId = 1,
            birdCount = 10,
            date = LocalDate.now(),
            eggCount = 2,
        )

        val coop2EggLog = EggLogDto(
            coopId = 2,
            birdCount = 10,
            date = LocalDate.now(),
            eggCount = 5,
        )

        eggLogDao.insertOrUpdateEggLog(coop1EggLog)
        eggLogDao.insertOrUpdateEggLog(coop2EggLog)

        val coop1EggLogs = eggLogDao.getEggLogsByCoopId(1).first()

        assert(coop1EggLogs.size == 1)
        assert(coop1EggLogs[0].eggCount == 2)
    }

    @Test
    fun getTotalEggsForMonth_ReturnsCorrectTotal() = runBlocking {
        insertTestCoop(id = 1)

        for( i in 1..10){
            eggLogDao.insertOrUpdateEggLog(
                EggLogDto(
                    date = LocalDate.of(2023, 1, i),
                    coopId = 1,
                    birdCount = 10,
                    eggCount = 5,
                )
            )
        }

        val totalEggs = eggLogDao.getTotalEggsForMonth(1, "2023-01-%").first()
        assert(totalEggs == 50)
    }

    @Test
    fun insertOrUpdateEggLog_ShouldOnlyBeOneLogByDate() = runBlocking {
        insertTestCoop(id = 1)
        val date = LocalDate.now()

        val firstEntry = EggLogDto(coopId = 1, birdCount = 10, date = date, eggCount = 2)
        val secondEntry = EggLogDto(coopId = 1, birdCount = 10, date = date, eggCount = 5)

        eggLogDao.insertOrUpdateEggLog(firstEntry)
        eggLogDao.insertOrUpdateEggLog(secondEntry)

        val allLogs = eggLogDao.getEggLogsByCoopId(1).first()

        // ASSERT : On vérifie l'unicité et le contenu, pas l'ID technique
        assertEquals(1, allLogs.size)
        assertEquals(5, allLogs[0].eggCount)
        assertEquals(date, allLogs[0].date)
    }




}