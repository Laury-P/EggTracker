package com.openclassroom.eggtracker.ui.screens.coop

import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.openclassroom.eggtracker.R
import com.openclassroom.eggtracker.domain.Coop
import com.openclassroom.eggtracker.domain.PoultryType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditCoopScreen(viewModel: CoopViewModel = hiltViewModel(), navController: NavController) {
    // TODO Enelevert tout les text en dur
    // TODO espacé proprement le formulaire
    // TODO gestion de modifier un poulailler
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.nav_add_edit_coop)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                })

        }
    ) { innerPadding ->
        AddEditCoopContent(
            modifier = Modifier.padding(innerPadding),
            onSaveCoopClick = { coop ->
                viewModel.insertOrUpdateCoop(coop)
                navController.popBackStack()
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditCoopContent(modifier: Modifier = Modifier, onSaveCoopClick: (Coop) -> Unit) {
    var name by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var type by remember { mutableStateOf(PoultryType.CHICKEN) }
    val options = PoultryType.entries.map { it }
    var birdCount by remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        OutlinedTextField(
            onValueChange = { name = it },
            label = { Text("Nom du poulailler") },
            value = name,
            singleLine = true
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = stringResource(type.labelResId),
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
                            type = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }
        // TODO : regler probleme du 0 quand on rentre un chiffre
        OutlinedTextField(
            onValueChange = { birdCount = it.toIntOrNull() ?: 0 },
            label = { Text("Nombre de volaille") },
            value = birdCount.toString(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        Button(
            onClick = {
                onSaveCoopClick(
                    Coop(
                        name = name,
                        type = type,
                        birdCount = birdCount
                    )
                )
            },
            enabled = name.isNotBlank() && birdCount > 0
        ) {
            Text("Enregistrer")
        }
    }
}

@Preview
@Composable
fun AddEditCoopContentPreview() {
    AddEditCoopContent(onSaveCoopClick = {})
}