package com.openclassroom.eggtracker

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.openclassroom.eggtracker.data.EggTrackerDatabase
import com.openclassroom.eggtracker.data.dao.CoopDao
import com.openclassroom.eggtracker.data.entity.PoultryGroupDto
import com.openclassroom.eggtracker.domain.PoultryType
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CoopDaoTest {
    private lateinit var database: EggTrackerDatabase
    private lateinit var coopDao: CoopDao

    @Before
    fun createDb() {
        database = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                EggTrackerDatabase::class.java
            ).allowMainThreadQueries()
            .build()

        coopDao = database.coopDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun upsertCoop_WhenNewCoop_ReturnsNewCoop() = runBlocking{
        val coop = PoultryGroupDto(
            coopName = "Test Coop",
            poultryType = PoultryType.CHICKEN,
            birdCount = 10
        )

        coopDao.upsertCoop(coop)

        val addedCoop = coopDao.getCoopById(1).first()
        val expectedCoop = coop.copy(coopId = 1)

        assertEquals(expectedCoop, addedCoop)
    }

    @Test
    fun upsertCoop_WhenExistingCoop_ReturnsUpdatedCoop() = runBlocking{
        val coop = PoultryGroupDto(
            coopName = "Test Coop",
            poultryType = PoultryType.CHICKEN,
            birdCount = 10)

        coopDao.upsertCoop(coop)

        val updatedCoop = coop.copy(
            coopId = 1,
            coopName = "Test Coop",
            poultryType = PoultryType.QUAIL,
            birdCount = 10
        )

        coopDao.upsertCoop(updatedCoop)

        val retrievedCoop = coopDao.getCoopById(1).first()

        assertEquals(updatedCoop, retrievedCoop)
    }

    @Test
    fun deleteCoop_WhenExistingCoop_DeletesCoop() = runBlocking {
        val coop = PoultryGroupDto(
            coopName = "Test Coop",
            poultryType = PoultryType.CHICKEN,
            birdCount = 10
        )

        coopDao.upsertCoop(coop)

        val deletedCoop = coop.copy(coopId = 1)

        coopDao.deleteCoop(deletedCoop)

        val retrievedCoop = coopDao.getCoopById(1).first()

        assertEquals(null, retrievedCoop)
    }

    @Test
    fun getAllCoopsByAlphabeticalOrder() = runBlocking {
        val coop1 = PoultryGroupDto(
            coopName = "Zebulon",
            poultryType = PoultryType.CHICKEN,
            birdCount = 2,)
        val coop2 = PoultryGroupDto(
            coopName = "Anatolie",
            poultryType = PoultryType.CHICKEN,
            birdCount = 3,)

        coopDao.upsertCoop(coop1)
        coopDao.upsertCoop(coop2)

        val allCoops = coopDao.getAllCoops().first()

        assertEquals(2, allCoops.size)
        assertEquals(coop1.coopName, allCoops[1].coopName)
        assertEquals(coop2.coopName, allCoops[0].coopName)
    }

    @Test
    fun getCoopCount() = runBlocking {
        val coop1 = PoultryGroupDto(
            coopName = "Test Coop 1",
            poultryType = PoultryType.CHICKEN,
            birdCount = 2,)
        val coop2 = PoultryGroupDto(
            coopName = "Test Coop 2",
            poultryType = PoultryType.CHICKEN,
            birdCount = 3,)

        coopDao.upsertCoop(coop1)
        coopDao.upsertCoop(coop2)

        val coopCount = coopDao.getCoopCount().first()

        assertEquals(2, coopCount)
    }


}