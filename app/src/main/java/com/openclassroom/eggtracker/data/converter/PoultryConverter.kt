package com.openclassroom.eggtracker.data.converter

import androidx.room.TypeConverter
import com.openclassroom.eggtracker.domain.PoultryType

class PoultryConverter {
    @TypeConverter
    fun fromPoultryType(poultryType: PoultryType): String {
        return poultryType.name
    }

    @TypeConverter
    fun toPoultryType(poultryTypeName: String): PoultryType {
        return PoultryType.valueOf(poultryTypeName)
    }

}