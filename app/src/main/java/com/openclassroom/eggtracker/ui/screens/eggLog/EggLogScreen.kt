package com.openclassroom.eggtracker.ui.screens.eggLog

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.openclassroom.eggtracker.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EggLogScreen(
    viewModel: EggLogViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coopsEggLog = viewModel.coopsEggLog.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.saveEggLog()
                Toast.makeText(context, R.string.saved_egg_log, Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.save_button)
                )
            }
        }
    ) { _ ->
        LazyColumn(
            Modifier.fillMaxSize().padding(16.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.nav_egg_log),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            items(coopsEggLog.value) { coopEggLog ->
                EggLogItem(
                    coopEggLog,
                    onCountChange = { newCount -> viewModel.onCountChange(coopEggLog.coopId, newCount) },
                    onNoteChange = { newNote -> viewModel.onNoteChange(coopEggLog.coopId, newNote) })
            }
        }
    }

}


@Composable
fun EggLogItem(eggLogState: EggLogState, onCountChange: (String) -> Unit, onNoteChange: (String) -> Unit) {
    Column (modifier = Modifier
        .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = eggLogState.coopName, style = MaterialTheme.typography.titleSmall)
        OutlinedTextField(
            label = { Text(text = stringResource(R.string.egg_count_label)) },
            value = if(eggLogState.eggCount == null) "" else eggLogState.eggCount.toString(),
            onValueChange = onCountChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            label = { Text(text = stringResource(R.string.note_label)) },
            modifier = Modifier.fillMaxWidth(),
            value = eggLogState.note,
            onValueChange = onNoteChange,
        )
        HorizontalDivider(Modifier.padding(bottom = 8.dp))
    }

}
