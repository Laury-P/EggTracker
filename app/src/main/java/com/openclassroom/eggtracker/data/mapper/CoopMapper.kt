package com.openclassroom.eggtracker.data.mapper

import com.openclassroom.eggtracker.data.entity.PoultryGroupDto
import com.openclassroom.eggtracker.domain.Coop

fun PoultryGroupDto.toDomain(): Coop {
    return Coop(
        id = this.coopId,
        name = this.coopName,
        type = this.poultryType,
        birdCount = this.birdCount
    )
}

fun Coop.toDto(): PoultryGroupDto{
    return PoultryGroupDto(
        coopId = this.id,
        coopName = this.name,
        poultryType = this.type,
        birdCount = this.birdCount
    )
}