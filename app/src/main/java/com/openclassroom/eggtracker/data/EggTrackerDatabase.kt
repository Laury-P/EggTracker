package com.openclassroom.eggtracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.openclassroom.eggtracker.data.converter.LocalDateConverter
import com.openclassroom.eggtracker.data.converter.PoultryConverter
import com.openclassroom.eggtracker.data.dao.CoopDao
import com.openclassroom.eggtracker.data.dao.EggLogDao
import com.openclassroom.eggtracker.data.entity.EggLogDto
import com.openclassroom.eggtracker.data.entity.PoultryGroupDto
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [
        PoultryGroupDto::class,
        EggLogDto::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    PoultryConverter::class,
    LocalDateConverter::class,
)
abstract class EggTrackerDatabase : RoomDatabase() {
    abstract fun coopDao(): CoopDao
    abstract fun eggLogDao(): EggLogDao


}