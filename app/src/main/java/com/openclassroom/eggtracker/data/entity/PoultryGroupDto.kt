package com.openclassroom.eggtracker.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.openclassroom.eggtracker.domain.PoultryType

@Entity(tableName = "poultry_group")
data class PoultryGroupDto(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "coop_id") val coopId: Long? = null,
    @ColumnInfo(name = "coop_name") val coopName: String,
    @ColumnInfo(name = "poultry_type") val poultryType: PoultryType,
    @ColumnInfo(name = "bird_count") val birdCount: Int,
)