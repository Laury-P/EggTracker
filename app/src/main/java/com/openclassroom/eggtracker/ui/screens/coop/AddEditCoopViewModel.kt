package com.openclassroom.eggtracker.ui.screens.coop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassroom.eggtracker.domain.Coop
import com.openclassroom.eggtracker.domain.PoultryType
import com.openclassroom.eggtracker.domain.repository.CoopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCoopViewModel @Inject constructor(private val coopRepository: CoopRepository) : ViewModel() {
    // TODO Tester le view model
    var name by mutableStateOf("")
        private set

    var type by mutableStateOf(PoultryType.CHICKEN)
        private set

    var birdCount by mutableStateOf("")
        private set

    var id: Long? by mutableStateOf(null)
        private set


    fun onNamedChanged(newValue: String) {
        name = newValue
    }

    fun onTypeChanged(newValue: PoultryType) {
        type = newValue
    }

    fun onBirdCountChanged(newValue: String) {
        birdCount = newValue
    }

    fun loadCoop(coopId: Long)  {
        viewModelScope.launch {
            val coop = coopRepository.getCoopById(coopId).first()
            if (coop != null) {
                id = coop.id
                name = coop.name
                type = coop.type
                birdCount = coop.birdCount.toString()
            }
        }
    }

    fun insertOrUpdateCoop() {
        viewModelScope.launch {
            coopRepository.insertOrUpdateCoop(
                Coop(
                    id = id,
                    name = name,
                    type = type,
                    birdCount = birdCount.toInt()
                )
            )
        }
    }
}