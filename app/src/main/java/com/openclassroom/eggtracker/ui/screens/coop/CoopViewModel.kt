package com.openclassroom.eggtracker.ui.screens.coop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassroom.eggtracker.domain.Coop
import com.openclassroom.eggtracker.domain.repository.CoopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoopViewModel @Inject constructor(private val coopRepository: CoopRepository) : ViewModel() {

    // TODO Tester le view model

    val coops = coopRepository.getAllCoops()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun deleteCoop(coop: Coop) {
        viewModelScope.launch {
            coopRepository.deleteCoop(coop)
        }
    }


}







