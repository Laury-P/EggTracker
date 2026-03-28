package com.openclassroom.eggtracker.domain

data class Coop (
    val id: Long? = null,
    val name: String,
    val type: PoultryType,
    val birdCount: Int,
)