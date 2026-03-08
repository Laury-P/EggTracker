package com.openclassroom.eggtracker.data.converter

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromLocalDate(localDate: LocalDate?): String? {
        return localDate?.toString()
    }

    @TypeConverter
    fun toLocalDate(dateString: String?) : LocalDate? {
        return dateString?.let { LocalDate.parse(it) }
    }

}