package com.openclassroom.eggtracker.ui.screens.eggLog

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassroom.eggtracker.domain.EggLog
import com.openclassroom.eggtracker.domain.repository.CoopRepository
import com.openclassroom.eggtracker.domain.repository.EggRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class EggLogViewModel @Inject constructor(
    private val eggRepository: EggRepository,
    coopRepository: CoopRepository
) : ViewModel() {

    private var userEditsEggCount = mutableStateMapOf<Long, Int>()
    private var userEditsNote = mutableStateMapOf<Long, String>()

    // TODO utiliser un refresh trigger pour rafraichir les données minuit par exemple
    // TODO a tester
    val coopsEggLog: StateFlow<List<EggLogState>> = combine(
        coopRepository.getAllCoops(),
        eggRepository.getEggLogsByDate(LocalDate.now()),
        snapshotFlow { userEditsEggCount.toMap() },
        snapshotFlow { userEditsNote.toMap() },
    ) { coops, eggLogs, eggCountEdits, noteEdits ->
        coops.map { coop ->
            val eggLog = eggLogs.find { it.coopId == coop.id }
            EggLogState(
                coopName = coop.name,
                coopId = coop.id!!,
                eggLogId = eggLog?.eggLogId,
                // Priority to the user edits
                eggCount = eggCountEdits[coop.id] ?: eggLog?.eggCount,
                note = noteEdits[coop.id] ?: eggLog?.note ?: "",
                birdCount = coop.birdCount
            )
        }.sortedWith(
            compareBy<EggLogState> { it.eggLogId != null }
                .thenBy { it.coopName }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun onCountChange(coopId: Long, newCount: String) {
        userEditsEggCount[coopId] = newCount.toIntOrNull() ?: 0
    }

    fun onNoteChange(coopId: Long, newNote: String) {
        userEditsNote[coopId] = newNote
    }

    fun saveEggLog(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val snapshotToSave = coopsEggLog.value


            snapshotToSave.forEach { state ->
                // Save state only if it has been edited or there is either a note or an egg count
                if (state.eggLogId != null || userEditsEggCount.containsKey(state.coopId) || userEditsNote.containsKey(state.coopId)) {
                    val eggLog = EggLog(
                        eggLogId = state.eggLogId,
                        coopId = state.coopId,
                        date = LocalDate.now(),
                        eggCount = state.eggCount?: 0,
                        birdCount = state.birdCount,
                        note = state.note
                    )
                    eggRepository.insertOrUpdateEgg(eggLog)
                }
            }
            userEditsEggCount.clear()
            userEditsNote.clear()
            onSuccess()
        }
    }
}

data class EggLogState(
    val coopName: String = "",
    val coopId: Long = 0,
    val birdCount: Int = 0,
    val eggLogId: Long? = null,
    val eggCount: Int? = null,
    val note: String = "",
)