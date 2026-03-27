package com.openclassroom.eggtracker.ui.screens.coop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.openclassroom.eggtracker.R
import com.openclassroom.eggtracker.domain.PoultryType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditCoopScreen(viewModel: AddEditCoopViewModel = hiltViewModel(), navController: NavController, coopId: Long) {

    LaunchedEffect(coopId) {
        if (coopId != -1L) viewModel.loadCoop(coopId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    if (coopId == -1L) stringResource(R.string.nav_add_edit_coop)
                    else stringResource(R.string.edit_coop)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                },
                windowInsets = WindowInsets(0,0,0,0),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
            )
        }
    ) { innerPadding ->
        AddEditCoopContent(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel,
            onSaveCoopClick = {
                viewModel.insertOrUpdateCoop()
                navController.popBackStack()
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditCoopContent(modifier: Modifier = Modifier, viewModel: AddEditCoopViewModel, onSaveCoopClick: () -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    val options = PoultryType.entries.map { it }

    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        OutlinedTextField(
            onValueChange = { viewModel.onNamedChanged(it) },
            label = { Text(stringResource(R.string.coop_name_label)) },
            value = viewModel.name,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = stringResource(viewModel.type.labelResId),
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, true)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(stringResource(selectionOption.labelResId)) },
                        onClick = {
                            viewModel.onTypeChanged(selectionOption)
                            expanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            onValueChange = { newValue ->
                viewModel.onBirdCountChanged(newValue.filter { it.isDigit() }) },
            label = { Text(stringResource(R.string.bird_count_label)) },
            value = viewModel.birdCount,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            supportingText = {
                if (viewModel.birdCount == "0") {
                    Text(text = stringResource(R.string.bird_count_null_error))
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                onSaveCoopClick()
            },
            enabled = viewModel.name.isNotBlank() && (viewModel.birdCount.toIntOrNull() ?: 0) > 0
        ) {
            Text(stringResource(R.string.save_button))
        }
    }
}

