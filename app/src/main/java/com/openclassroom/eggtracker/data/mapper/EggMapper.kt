package com.openclassroom.eggtracker.data.mapper

import com.openclassroom.eggtracker.data.entity.EggLogDto
import com.openclassroom.eggtracker.domain.EggLog

fun EggLogDto.toDomain(): EggLog {
    return EggLog(
        eggLogId = this.eggLogId,
        coopId = this.coopId,
        birdCount = this.birdCount,
        date = this.date,
        eggCount = this.eggCount,
        note = this.note
    )
}

fun EggLog.toDto(): EggLogDto {
    return EggLogDto(
        eggLogId = this.eggLogId,
        coopId = this.coopId,
        birdCount = this.birdCount,
        date = this.date,
        eggCount = this.eggCount,
        note = this.note
    )
}