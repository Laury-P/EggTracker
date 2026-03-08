package com.openclassroom.eggtracker.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "egg_log",
    foreignKeys = [
        ForeignKey(
            entity = PoultryGroupDto::class,
            parentColumns = ["coop_id"],
            childColumns = ["coop_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["coop_id"])
    ]
)
data class EggLogDto(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "egg_log_id") val eggLogId: Long? = null,
    @ColumnInfo(name = "coop_id") val coopId: Long,
    // To always have the number of poultry linked to the number of eggs laid, if a new poultry is
    // added to the coop later on the statistics won't be influenced by it
    @ColumnInfo(name = "bird_count") val birdCount: Int,
    val date: LocalDate,
    @ColumnInfo(name = "egg_count") val eggCount: Int,
    val note: String? = null,
)

