package com.openclassroom.eggtracker.domain

import java.time.LocalDate

data class EggLog (
    val eggLogId: Long? = null,
    val coopId: Long,
    val birdCount: Int,
    val date: LocalDate,
    val eggCount: Int,
    val note: String? = null,
)