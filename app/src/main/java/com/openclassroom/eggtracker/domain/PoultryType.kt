package com.openclassroom.eggtracker.domain

import androidx.annotation.StringRes
import com.openclassroom.eggtracker.R

enum class PoultryType (@StringRes val labelResId: Int) {
    CHICKEN(R.string.poultry_chicken),
    DUCK(R.string.poultry_duck),
    GOOSE(R.string.poultry_goose),
    QUAIL(R.string.poultry_quail),
    OTHER(R.string.poultry_other),
}